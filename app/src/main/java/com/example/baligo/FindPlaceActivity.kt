import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.baligo.R
import com.example.baligo.ml.BaliRecommendationModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class FindPlaceActivity : AppCompatActivity() {
    private lateinit var locationSpinner: Spinner
    private lateinit var recreationTypeSpinner: Spinner
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_place)

        locationSpinner = findViewById(R.id.locationSpinner)
        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        searchButton = findViewById(R.id.cariBtn)

        val locations = arrayOf(
            "Kota Denpasar",
            "Kabupaten Buleleng",
            "Kabupaten Badung",
            "Kabupaten Tabanan"
        )
        val recreationTypes = arrayOf(
            "Photo Spot",
            "Beach",
            "Restaurant"
        )

        val locationAdapter = ArrayAdapter(this, R.layout.spinner_item, locations)
        locationAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        locationSpinner.adapter = locationAdapter

        val recreationAdapter = ArrayAdapter(this, R.layout.spinner_item, recreationTypes)
        recreationAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        recreationTypeSpinner.adapter = recreationAdapter

        searchButton.setOnClickListener {
            val selectedLocation = locationSpinner.selectedItem.toString()
            val selectedRecreationType = recreationTypeSpinner.selectedItem.toString()
            performModelInference(selectedLocation, selectedRecreationType)
        }
    }

    private fun performModelInference(location: String, recreationType: String) {
        val model = BaliRecommendationModel.newInstance(this)

        // Convert strings to float tensors (example; adjust as needed)
        val locationFloat = location.hashCode().toFloat()
        val recreationTypeFloat = recreationType.hashCode().toFloat()

        // Prepare input for the model
        val byteBuffer = ByteBuffer.allocateDirect(8) // 4 bytes * 2 floats
        byteBuffer.order(java.nio.ByteOrder.nativeOrder())
        byteBuffer.putFloat(locationFloat)
        byteBuffer.putFloat(recreationTypeFloat)
        byteBuffer.rewind()

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 1), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer.slice(0, 4))

        val inputFeature1 = TensorBuffer.createFixedSize(intArrayOf(1, 1), DataType.FLOAT32)
        inputFeature1.loadBuffer(byteBuffer.slice(4, 8))

        // Run model inference and get result
        val outputs = model.process(inputFeature0, inputFeature1)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        // Close model
        model.close()

        // Convert result to Recommendation object (modify as needed)
        val recommendation = Recommendation(
            name = "Example Name",
            link = "http://example.com",
            category = "Example Category",
            photoUrl = "http://example.com/photo.jpg",
            review = "Example Review",
            rating = 4.5f
        )

        // Start ResultActivity with recommendation data
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("recommendation", recommendation)
        }
        startActivity(intent)
    }
}

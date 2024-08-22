package com.example.baligo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class FindPlaceActivity : AppCompatActivity() {
    private lateinit var locationSpinner: Spinner
    private lateinit var recreationTypeSpinner: Spinner
    private lateinit var interpreter: Interpreter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_place)

        // Initialize TensorFlow Lite interpreter
        interpreter = Interpreter(loadModelFile())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationSpinner = findViewById(R.id.locationSpinner)
        val locationItems = arrayOf(
            "Kota Denpasar",
            "Kabupaten Buleleng",
            "Kabupaten Badung",
            "Kabupaten Tabanan"
        )
        val locationAdapter = ArrayAdapter(this, R.layout.spinner_item, locationItems)
        locationAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        locationSpinner.adapter = locationAdapter

        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        val recreationItems = arrayOf(
            "Photo Spot",
            "Swimming",
            "Restaurant"
        )
        val recreationAdapter = ArrayAdapter(this, R.layout.spinner_item, recreationItems)
        recreationAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        recreationTypeSpinner.adapter = recreationAdapter

        val searchButton: Button = findViewById(R.id.cariBtn)
        searchButton.setOnClickListener {
            val location = locationSpinner.selectedItem.toString()
            val recreationType = recreationTypeSpinner.selectedItem.toString()

            // Generate recommendations
            val recommendations = getRecommendations(location, recreationType)

            // Pass recommendations to ResultActivity
            val intent = Intent(this, ResultActivity::class.java).apply {
                putParcelableArrayListExtra("recommendations", ArrayList(recommendations))
            }
            startActivity(intent)
        }
    }

    private fun loadModelFile(): ByteBuffer {
        val assetManager = assets
        val fileDescriptor = assetManager.openFd("model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun getRecommendations(location: String, recreationType: String): List<Recommendation> {
        // Implement model inference logic here
        // Return dummy recommendations for now
        return listOf(
            Recommendation(R.drawable.denpasar, "Tegal Wangi Beach", 4.5f, "Tempat ini bagus untuk dikunjungi dan menginap"),
            Recommendation(R.drawable.denpasar, "Seminyak", 4.0f, "Tempat ini bagus untuk dikunjungi dan menginap"),
            Recommendation(R.drawable.denpasar, "Nusa Penida", 5.0f, "Tempat ini bagus untuk dikunjungi dan menginap")
        )
    }
}

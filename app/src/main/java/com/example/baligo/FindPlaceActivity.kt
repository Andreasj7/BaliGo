package com.example.baligo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
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

        locationSpinner = findViewById(R.id.locationSpinner)
        val locationItems = arrayOf("Kota Denpasar", "Kabupaten Buleleng", "Kabupaten Badung", "Kabupaten Tabanan")
        val locationAdapter = ArrayAdapter(this, R.layout.spinner_item, locationItems)
        locationSpinner.adapter = locationAdapter

        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        val recreationItems = arrayOf("Photo Spot", "Swimming", "Restaurant")
        val recreationAdapter = ArrayAdapter(this, R.layout.spinner_item, recreationItems)
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
        // Preprocess input data for the model
        val input = preprocessInput(location, recreationType)

        // Define the output buffer, assuming the model returns a float array of size 56
        val output = Array(1) { FloatArray(56) } // Model output buffer

        // Run the model inference
        interpreter.run(input, output)

        // Post-process the output to generate recommendations
        return postprocessOutput(output[0])
    }

    private fun preprocessInput(location: String, recreationType: String): ByteBuffer {
        // Prepare the input buffer with 3 float values
        val inputBuffer = ByteBuffer.allocateDirect(3 * 4).order(ByteOrder.nativeOrder())
        inputBuffer.putFloat(convertLocationToFloat(location))
        inputBuffer.putFloat(convertRecreationTypeToFloat(recreationType))
        inputBuffer.putFloat(0.0f) // Placeholder for any additional feature (if any)
        return inputBuffer
    }

    private fun postprocessOutput(output: FloatArray): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()

        // Use a map to link the model's output indices to the actual destination names
        val destinationMap = mapOf(
            0 to "Alas Harum Tourism", 1 to "Badung Beach", 2 to "Bali Bird Park",
            3 to "Bangkian Djaran Waterfall", 4 to "Batukaru Mountain", 5 to "Batur Mountain",
            6 to "Bias Tugel Beach", 7 to "Blod Berawah Beach", 8 to "Bukit Cinta Viewpoint",
            9 to "Bunut Bolong", 10 to "Busungbiu Rice Terace", 11 to "Caruban Peak",
            12 to "Cemara Beach", 13 to "Cendikusuma Beach", 14 to "Gedong Kartya Museum",
            15 to "Geger Beach", 16 to "Gitgit Waterfall", 17 to "Goa Rang Reng Waterfall",
            18 to "Grojongan Waterfall", 19 to "Jakasatru Waterfall", 20 to "Jemeluk Beach",
            21 to "Karma Kandara Beach", 22 to "Keramas Beach", 23 to "Krisna Oleh-oleh Bali Nusakambangan",
            24 to "Ku De Ta Beach", 25 to "Lahangan Sweet", 26 to "Leke Leke Waterfall",
            27 to "Lovina Beach", 28 to "May Day Gili", 29 to "Medewi Beach",
            30 to "Mountain Abang", 31 to "Mundi Temple Peak", 32 to "Museum Blanco Renaissance",
            33 to "Natys Restaurants Seminyak", 34 to "Nungnung Waterfall", 35 to "Pantunan Valley",
            36 to "Pura Majapahit", 37 to "Rice Terrace Ambengan", 38 to "Savana Tianyar",
            39 to "Sea Turtle Conservation and Education Center", 40 to "Semarajaya Museum",
            41 to "Soka Beach", 42 to "Sukasada Garden", 43 to "Tegal Wangi Beach",
            44 to "Teman Joger Luwus", 45 to "Terasering Soka", 46 to "The Abandoned Airplane",
            47 to "The Amora Bali", 48 to "The Sila's Agrotourism", 49 to "Tibumana Waterfall",
            50 to "Tukad Loloan Sanur", 51 to "Tukad Unda Dam", 52 to "Ubud Monkey Forest",
            53 to "Wanagiri Peak", 54 to "Wisata Bakas Village", 55 to "Yeh Embang Kangin Waterfall"
        )

        // Iterate through the output array and generate recommendations
        for (i in output.indices) {
            val destinationName = destinationMap[i]
            if (destinationName != null) {
                recommendations.add(Recommendation(R.drawable.denpasar, destinationName, output[i], "Generated description"))
            }
        }

        return recommendations
    }

    private fun convertLocationToFloat(location: String): Float {
        return when (location) {
            "Kota Denpasar" -> 0.0f
            "Kabupaten Buleleng" -> 1.0f
            "Kabupaten Badung" -> 2.0f
            "Kabupaten Tabanan" -> 3.0f
            else -> 0.0f
        }
    }

    private fun convertRecreationTypeToFloat(recreationType: String): Float {
        return when (recreationType) {
            "Photo Spot" -> 0.0f
            "Swimming" -> 1.0f
            "Restaurant" -> 2.0f
            else -> 0.0f
        }
    }
}

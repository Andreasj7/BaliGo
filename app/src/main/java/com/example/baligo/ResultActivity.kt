package com.example.baligo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baligo.adapters.RecommendationAdapter
import com.example.baligo.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ResultActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecommendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = findViewById(R.id.recyclerView)
        adapter = RecommendationAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Ambil kategori yang dipilih dari intent
        val selectedCategory = intent.getIntExtra("SELECTED_CATEGORY", 0)

        // Jalankan model TensorFlow Lite dan tampilkan hasil
        runModel(selectedCategory)
    }

    private fun runModel(selectedCategory: Int) {
        try {
            Log.d("TensorFlowLite", "Starting model inference")

            // Load model TensorFlow Lite
            val model = Model.newInstance(this)
            Log.d("TensorFlowLite", "Model loaded successfully")

            // Buat input untuk model
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 2), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder())
            byteBuffer.putFloat(selectedCategory.toFloat())  // Input kategori
            byteBuffer.putFloat(4.5f)  // Input rating (contoh data, sesuaikan dengan data sebenarnya)
            inputFeature0.loadBuffer(byteBuffer)
            Log.d("TensorFlowLite", "Input data loaded")

            // Jalankan inferensi model dan dapatkan hasil
            val outputs = model.process(inputFeature0)
            Log.d("TensorFlowLite", "Model inference completed")

            // Ambil hasil inferensi
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
            val resultArray = outputFeature0.floatArray

            // Buat daftar rekomendasi berdasarkan hasil model (sesuaikan dengan hasil modelmu)
            val recommendations = listOf(
                Recommendation("Place Name 1", resultArray[0], "https://maps.google.com/maps?q=Place1&output=embed", "Great place!"),
                Recommendation("Place Name 2", resultArray[1], "https://maps.google.com/maps?q=Place2&output=embed", "Amazing experience!")
                // Tambahkan lebih banyak rekomendasi berdasarkan hasil model
            )

            // Set data ke adapter
            adapter.setRecommendations(recommendations)

            // Tutup model untuk membersihkan resource
            model.close()

        } catch (e: Exception) {
            Log.e("TensorFlowLite", "Error running model: ${e.message}")
            e.printStackTrace()
        }
    }
}

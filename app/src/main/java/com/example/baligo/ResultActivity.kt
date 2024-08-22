package com.example.baligo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baligo.adapters.RecommendationAdapter

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dummy data untuk rekomendasi
        val recommendations = listOf(
            Recommendation(R.drawable.denpasar, "Tegal Wangi Beach", 4.5f, "Tempat ini bagus untuk dikunjungi dan menginap"),
            Recommendation(R.drawable.denpasar, "Seminyak", 4.0f, "Tempat ini bagus untuk dikunjungi dan menginap"),
            Recommendation(R.drawable.denpasar, "Nusa Penida", 5.0f, "Tempat ini bagus untuk dikunjungi dan menginap")
        )

        recyclerView.adapter = RecommendationAdapter(recommendations)
    }
}

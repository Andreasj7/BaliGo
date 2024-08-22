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
        setContentView(R.layout.activity_result) // Ensure this layout file exists

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve recommendations from intent
        val recommendations: List<Recommendation> = intent.getParcelableArrayListExtra("recommendations") ?: emptyList()

        recyclerView.adapter = RecommendationAdapter(recommendations)
    }
}

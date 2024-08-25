package com.example.baligo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baligo.adapters.RecommendationAdapter

class ResultActivity : AppCompatActivity() {

    private lateinit var recommendationRecyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        recommendationRecyclerView = findViewById(R.id.nameTextView)
        recommendationRecyclerView.layoutManager = LinearLayoutManager(this)

        // Ambil data dari intent
        val recommendations = intent.getParcelableArrayListExtra<Recommendation>("recommendations")

        // Setup adapter untuk RecyclerView
        recommendationRecyclerView.adapter = RecommendationAdapter(recommendations ?: emptyList())
    }
}

package com.example.baligo

import FindPlaceActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baligo.adapters.ShowcaseAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var showcaseRV: RecyclerView
    private lateinit var findPlaceBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.haloTv)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showcaseRV = findViewById(R.id.showcaseRV)
        findPlaceBtn = findViewById(R.id.findPlaceBtn)
        showcaseRV.adapter = ShowcaseAdapter()
        showcaseRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        findPlaceBtn.setOnClickListener {
            // Handle button click here
            val intent = Intent(this, FindPlaceActivity::class.java)
            startActivity(intent)
        }

    }
}
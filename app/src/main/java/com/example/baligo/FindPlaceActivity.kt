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

class FindPlaceActivity : AppCompatActivity() {
    private lateinit var locationSpinner: Spinner
    private lateinit var cariBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_place)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        locationSpinner= findViewById(R.id.categoryTypeSpinner)
        var items = arrayOf(
            "Beach",
            "Attraction Places",
            "Restaunrant",
            "Museum",
            "Waterfall"
        )
        cariBtn = findViewById(R.id.cariBtn)

        var adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        locationSpinner.adapter = adapter

        cariBtn.setOnClickListener {
            val selectedItem = locationSpinner.selectedItem.toString() // Ambil item yang dipilih dari Spinner
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("selectedItem", selectedItem) // Kirim data Spinner ke ResultActivity
            startActivity(intent)
        }


    }

}
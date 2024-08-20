package com.example.baligo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FindPlaceActivity : AppCompatActivity() {
    private lateinit var locationSpinner: Spinner
    private lateinit var recreationTypeSpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_place)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        locationSpinner= findViewById(R.id.locationSpinner)
        var items = arrayOf(
            "Kab. Badung",
            "Kab. Buleleng",
            "Kab. Gianyar",
            "Kab. Jembrana",
            "Kab. Karangasem",
            "Kab. Klungkung",
            "Kab. Tabanan",
            "Kota Denpasar"
        )

        var adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        locationSpinner.adapter = adapter

        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        items = arrayOf(
            "Photo Spot",
            "Water Sports",
            "Swimming",
            "Restaurant"
        )
        adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        recreationTypeSpinner.adapter = adapter

        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        val items2 = arrayOf("Item 1", "Item 2", "Item 3")
        val adapter2 = ArrayAdapter(this, R.layout.spinner_item, items2)
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item)
        recreationTypeSpinner.adapter = adapter2

    }


}
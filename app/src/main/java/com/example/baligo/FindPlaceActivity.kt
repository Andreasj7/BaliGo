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
    private lateinit var recreationTypeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_place)

        // Mengatur padding untuk sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi Spinner lokasi
        locationSpinner = findViewById(R.id.locationSpinner)
        var items = arrayOf(
            "Kota Denpasar",
            "Kabupaten Buleleng",
            "Kabupaten Badung",
            "Kabupaten Tabanan"
        )
        var adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        locationSpinner.adapter = adapter

        // Inisialisasi Spinner jenis rekreasi
        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        items = arrayOf(
            "Photo Spot",
            "Swimming",
            "Restaurant"
        )
        adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        recreationTypeSpinner.adapter = adapter

        // Menangani klik tombol Cari
        val btnCari: Button = findViewById(R.id.cariBtn)
        btnCari.setOnClickListener {
            // Ambil data dari Spinner
            val selectedLocation = locationSpinner.selectedItem.toString()
            val selectedRecreationType = recreationTypeSpinner.selectedItem.toString()

            // Intent ke ResultActivity
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("selectedLocation", selectedLocation)
                putExtra("selectedRecreationType", selectedRecreationType)
            }
            startActivity(intent)
        }
    }
}
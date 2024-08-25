package com.example.baligo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.widget.Spinner

class FindPlaceActivity : AppCompatActivity() {

    private lateinit var recreationTypeSpinner: Spinner
    private lateinit var cariBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_place)

        recreationTypeSpinner = findViewById(R.id.recreationTypeSpinner)
        cariBtn = findViewById(R.id.cariBtn)

        // Setup Spinner
        val recreationTypes = listOf("Beach", "Mountain", "Park")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recreationTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        recreationTypeSpinner.adapter = adapter

        cariBtn.setOnClickListener {
            val selectedType = recreationTypeSpinner.selectedItem.toString()

            // Contoh data rekomendasi
            val recommendations = listOf(
                Recommendation("Place 1", "Photo URL", "Google Maps URL", 4.5f, "Review URL"),
                Recommendation("Place 2", "Photo URL", "Google Maps URL", 4.0f, "Review URL")
            )

            // Kirim data ke ResultActivity
            val intent = Intent(this, ResultActivity::class.java)
            intent.putParcelableArrayListExtra("recommendations", ArrayList(recommendations))
            startActivity(intent)
        }
    }
}

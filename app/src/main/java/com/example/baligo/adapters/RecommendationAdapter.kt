package com.example.baligo.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baligo.R
import com.example.baligo.Recommendation

class RecommendationAdapter(private val recommendations: List<Recommendation>) :
    RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recommendation: Recommendation) {
            // Pastikan ID ini sesuai dengan yang ada di item_recommendation.xml
            itemView.findViewById<TextView>(R.id.nameTextView).text = recommendation.name
            itemView.findViewById<TextView>(R.id.ratingTextView).text = recommendation.rating.toString()
            itemView.findViewById<TextView>(R.id.reviewTextView).text = recommendation.reviewUrl

            // Untuk ImageView, Anda bisa menggunakan library seperti Glide atau Picasso untuk memuat gambar
            // Contoh menggunakan Glide (tambahkan dependensi Glide jika belum ada)
            // Glide.with(itemView.context).load(recommendation.photoUrl).into(itemView.findViewById<ImageView>(R.id.photoImageView))

            // Set link Google Maps
            val mapLinkTextView = itemView.findViewById<TextView>(R.id.mapLinkTextView)
            mapLinkTextView.text = recommendation.googleMapsUrl
            mapLinkTextView.setOnClickListener {
                // Handle Google Maps link click
                // Misalnya membuka link di browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recommendation.googleMapsUrl))
                itemView.context.startActivity(intent)
            }

            itemView.setOnClickListener {
                // Handle item click jika diperlukan
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendations[position])
    }

    override fun getItemCount(): Int {
        return recommendations.size
    }
}

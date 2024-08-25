package com.example.baligo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baligo.R
import com.example.baligo.Recommendation

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    private var recommendations: List<Recommendation> = mutableListOf()

    fun setRecommendations(recommendations: List<Recommendation>) {
        this.recommendations = recommendations
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recommendation = recommendations[position]
        holder.bind(recommendation)
    }

    override fun getItemCount(): Int {
        return recommendations.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        private val mapImageView: ImageView = itemView.findViewById(R.id.mapImageView)
        private val reviewTextView: TextView = itemView.findViewById(R.id.reviewTextView)

        fun bind(recommendation: Recommendation) {
            nameTextView.text = recommendation.name
            ratingTextView.text = "Rating: ${recommendation.rating}"
            reviewTextView.text = recommendation.review

            // Memuat gambar dari URL menggunakan Glide
            Glide.with(itemView.context)
                .load(recommendation.googleMapsImageUrl)
                .into(mapImageView)
        }
    }
}


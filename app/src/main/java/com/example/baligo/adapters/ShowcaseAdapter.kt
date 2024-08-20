package com.example.baligo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baligo.R

class ShowcaseAdapter : RecyclerView.Adapter<ShowcaseAdapter.ShowcaseViewHolder>() {

    // This list represents your data set. You can replace it with your actual data.
    private val data = listOf("Item 1", "Item 2", "Item 3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.showcase_item, parent, false)
        return ShowcaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        // Here you bind your data with the view. For now, it does nothing.
    }

    override fun getItemCount() = data.size

    class ShowcaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Here you can initialize your views for each item
    }
}
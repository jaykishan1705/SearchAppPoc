package com.example.searchapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.databinding.RowActivitiesBinding
import com.example.searchapp.repository.model.ActivitiesItem

class ActivityItemAdapter(private val activities: List<ActivitiesItem?>?) : RecyclerView.Adapter<ActivityItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowActivitiesBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = activities?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(activities?.get(position))
    }

    inner class ViewHolder(private val binding: RowActivitiesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ActivitiesItem?) {
            binding.model = item
            binding.executePendingBindings()
        }
    }
}
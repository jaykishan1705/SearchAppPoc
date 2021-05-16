package com.example.searchapp.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.databinding.RowUnitsBinding
import com.example.searchapp.repository.model.UnitsItem

class UnitsItemAdapter(private val blocks: List<UnitsItem?>?) : RecyclerView.Adapter<UnitsItemAdapter.ViewHolder>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowUnitsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = blocks?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(blocks?.get(position))
    }

    inner class ViewHolder(private val binding: RowUnitsBinding) : RecyclerView.ViewHolder(binding.root) {

        private var activityItemAdapter: ActivityItemAdapter? = null

        fun bind(item: UnitsItem?) {
            binding.model = item
            activityItemAdapter = ActivityItemAdapter(item?.activities)
            binding.rvActivities.adapter = activityItemAdapter
            binding.executePendingBindings()
        }
    }
}
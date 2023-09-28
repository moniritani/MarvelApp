package com.monir.marvelapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monir.marvelapp.data.model.Series
import com.monir.marvelapp.databinding.ItemSeriesBinding
import com.monir.marvelapp.extensions.load

class SeriesAdapter : ListAdapter<Series, SeriesAdapter.ItemViewHolder>(DiffCallback()) , ListSubmittingAdapter<Series> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Series) {
            with(binding) {
                imgItem.load(item.thumbnail)
                tvTitle.text = item.title
                tvDescription.text = item.description
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }

}


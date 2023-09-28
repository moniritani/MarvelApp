package com.monir.marvelapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monir.marvelapp.data.model.Story
import com.monir.marvelapp.databinding.ItemStoryBinding
import com.monir.marvelapp.extensions.loadCircular

class StoriesAdapter : ListAdapter<Story, StoriesAdapter.ItemViewHolder>(DiffCallback()) , ListSubmittingAdapter<Story> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story) {
            with(binding) {
                tvTitle.text = item.title
                tvDescription.text = item.description
                imgItem.loadCircular(item.thumbnail?.imagePath)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }

}


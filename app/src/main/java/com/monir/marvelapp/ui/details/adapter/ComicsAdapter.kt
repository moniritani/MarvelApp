package com.monir.marvelapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monir.marvelapp.data.model.Comic
import com.monir.marvelapp.databinding.ItemComicBinding
import com.monir.marvelapp.extensions.load

class ComicsAdapter : ListAdapter<Comic, ComicsAdapter.ComicViewHolder>(DiffCallback()) , ListSubmittingAdapter<Comic> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ComicViewHolder(private val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Comic) {
            with(binding) {
                imgItem.load(item.thumbnail)
                tvTitle.text = item.title
                tvDescription.text = item.description
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }

}


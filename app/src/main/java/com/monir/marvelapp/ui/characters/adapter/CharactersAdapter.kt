package com.monir.marvelapp.ui.characters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monir.marvelapp.R
import com.monir.marvelapp.data.model.Character
import com.monir.marvelapp.databinding.ItemCharacterBinding
import com.monir.marvelapp.databinding.ItemLoadingBinding
import com.monir.marvelapp.extensions.loadCircular

class CharactersAdapter(
    private val navigator: CharactersNavigator
) : ListAdapter<CharacterListItem, RecyclerView.ViewHolder>(CharacterDiffCallback()) {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CharacterViewHolder(binding)
        } else {
            val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is CharacterListItem.CharacterItem -> (holder as CharacterViewHolder).bind(item.character)
            is CharacterListItem.LoadingItem -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is CharacterListItem.LoadingItem) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }
    fun showLoadingItem() {
        val newList = currentList.toMutableList()
        if (newList.lastOrNull() !is CharacterListItem.LoadingItem) {
            newList.add(CharacterListItem.LoadingItem)
            submitList(newList)
        }
    }

    fun hideLoadingItem() {
        submitList(currentList.filterNot { it is CharacterListItem.LoadingItem })
    }

    inner class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)
    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            with(binding) {

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        if (item is CharacterListItem.CharacterItem)
                            navigator.onClick(item.character)
                    }
                }

                with(root.context){
                    txtName.text = character.name ?: getString(R.string.not_available)
                    txtDescription.text = character.description ?: getString(R.string.not_available)
                    tvComicsCount.text = buildItemCount(this,R.string.title_comics_list,character.comics?.available)
                    tvStoriesCount.text = buildItemCount(this,R.string.title_stories_list,character.stories?.available)
                    tvEventsCount.text = buildItemCount(this,R.string.title_events_list,character.events?.available)
                    tvSeriesCount.text = buildItemCount(this,R.string.title_series_list,character.series?.available)
                }
                imgThumbnail.loadCircular(character.thumbnail?.imagePath)
            }
        }

        private fun buildItemCount(context: Context,@StringRes titleRes : Int, count : Int?) : String{
            with(context){
                return getString(R.string.items_count_label,getString(titleRes),count ?: 0)
            }
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterListItem>() {
        override fun areItemsTheSame(oldItem: CharacterListItem, newItem: CharacterListItem): Boolean {
            return when {
                oldItem is CharacterListItem.CharacterItem && newItem is CharacterListItem.CharacterItem -> oldItem.character.id == newItem.character.id
                oldItem is CharacterListItem.LoadingItem && newItem is CharacterListItem.LoadingItem -> true
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: CharacterListItem, newItem: CharacterListItem): Boolean {
            return when {
                oldItem is CharacterListItem.CharacterItem && newItem is CharacterListItem.CharacterItem -> oldItem.character == newItem.character
                oldItem is CharacterListItem.LoadingItem && newItem is CharacterListItem.LoadingItem -> true
                else -> false
            }
        }
    }


}

package com.monir.marvelapp.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monir.marvelapp.base.BaseFragment
import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.databinding.FragmentCharactersBinding
import com.monir.marvelapp.ui.characters.adapter.CharacterListItem
import com.monir.marvelapp.ui.characters.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharactersAdapter {
            it.id?.let {characterID ->
                goTo(CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(characterID,it.name,it.thumbnail))
            }
        }
        binding.rvCharacters.adapter = adapter

        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                // approach to start the loading before reaching the last item
                if (totalItemCount < lastVisibleItemPosition + 3)
                    viewModel.loadMoreCharacters()
            }
        })


        // Initialize with empty list and a loading item
        adapter.submitList(listOf(CharacterListItem.LoadingItem))

        // Observe LiveData from ViewModel
        viewModel.charactersResource.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is BaseResource.Loading -> if(adapter.currentList.isNotEmpty()) adapter.showLoadingItem()
                is BaseResource.Success -> {
                    adapter.hideLoadingItem()
                    val newList = mutableListOf<CharacterListItem>()
                    newList.addAll(adapter.currentList.filterIsInstance<CharacterListItem.CharacterItem>())
                    newList.addAll(resource.data!!.data!!.map { CharacterListItem.CharacterItem(it) })
                    adapter.submitList(newList)
                }
                is BaseResource.Error -> {
                    adapter.hideLoadingItem()
                }
            }
        }
    }
}
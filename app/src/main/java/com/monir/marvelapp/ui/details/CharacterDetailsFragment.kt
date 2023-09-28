package com.monir.marvelapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.monir.marvelapp.R
import com.monir.marvelapp.base.BaseFragment
import com.monir.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.monir.marvelapp.extensions.load
import com.monir.marvelapp.ui.details.adapter.ComicsAdapter
import com.monir.marvelapp.ui.details.adapter.EventsAdapter
import com.monir.marvelapp.ui.details.adapter.SeriesAdapter
import com.monir.marvelapp.ui.details.adapter.StoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate) {

    private lateinit var comicsAdapter : ComicsAdapter
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var adapterEvents : EventsAdapter
    private lateinit var adapterSeries : SeriesAdapter

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.getCharacterDetails(args.characterID)
        observeViewModel()
    }

    private fun setupView(){
        initAdapters()
        binding.imgCharacter.load(args.characterThumbnail)
        binding.tvCharacterName.text = args.characterName
        binding.tvCharacterId.text = getString(R.string.character_id_title, args.characterID)
    }

    private fun initAdapters(){
        comicsAdapter = ComicsAdapter()
        binding.sectionComics.setAdapter(comicsAdapter)

        storiesAdapter = StoriesAdapter()
        binding.sectionStories.setAdapter(storiesAdapter)

        adapterEvents = EventsAdapter()
        binding.sectionEvents.setAdapter(adapterEvents)

        adapterSeries = SeriesAdapter()
        binding.sectionSeries.setAdapter(adapterSeries)
    }

    private fun observeViewModel() {
        viewModel.comicsResource.observe(viewLifecycleOwner) { resource ->
            binding.sectionComics.manageStates(resource, comicsAdapter)
        }

        viewModel.eventsResource.observe(viewLifecycleOwner) { resource ->
            binding.sectionEvents.manageStates(resource, adapterEvents)
        }

        viewModel.storiesResource.observe(viewLifecycleOwner) { resource ->
            binding.sectionStories.manageStates(resource, storiesAdapter)
        }

        viewModel.seriesResource.observe(viewLifecycleOwner) { resource ->
            binding.sectionSeries.manageStates(resource, adapterSeries)
        }
    }
}
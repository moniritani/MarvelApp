package com.monir.marvelapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.monir.marvelapp.R
import com.monir.marvelapp.base.BaseFragment
import com.monir.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.monir.marvelapp.extensions.loadCircular
import com.monir.marvelapp.ui.customwidgets.DetailsSectionView
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
        binding.btnClose.setOnClickListener {
            popFragment()
        }
        binding.tvCharacterName.text = args.characterName
        binding.tvCharacterId.text = getString(R.string.character_id_title, args.characterID)
        binding.imgCharacter.loadCircular(args.characterThumbnail?.imagePath)
    }

    private fun initAdapters(){
        comicsAdapter = ComicsAdapter()
        binding.sectionComics.setAdapter(comicsAdapter)
        binding.sectionComics.retryCallback = DetailsSectionView.RetryCallback {
            viewModel.getComics(args.characterID)
        }

        storiesAdapter = StoriesAdapter()
        binding.sectionStories.setAdapter(storiesAdapter)
        binding.sectionStories.retryCallback = DetailsSectionView.RetryCallback {
            viewModel.getStories(args.characterID)
        }

        adapterEvents = EventsAdapter()
        binding.sectionEvents.setAdapter(adapterEvents)
        binding.sectionEvents.retryCallback = DetailsSectionView.RetryCallback {
            viewModel.getEvents(args.characterID)
        }

        adapterSeries = SeriesAdapter()
        binding.sectionSeries.setAdapter(adapterSeries)
        binding.sectionSeries.retryCallback = DetailsSectionView.RetryCallback {
            viewModel.getSeries(args.characterID)
        }
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
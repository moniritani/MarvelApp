package com.monir.marvelapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.monir.marvelapp.base.BaseFragment
import com.monir.marvelapp.databinding.FragmentCharacterDetailsBinding

class DetailsFragment : BaseFragment<FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate) {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = "Character with ID: ${args.characterID}"
        binding.btnBack.setOnClickListener {
            popFragment()
        }
    }
}
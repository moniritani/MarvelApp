package com.monir.marvelapp.ui.characters

import android.os.Bundle
import android.view.View
import com.monir.marvelapp.base.BaseFragment
import com.monir.marvelapp.databinding.FragmentCharactersBinding

class CharactersFragment : BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShowCharacter.setOnClickListener {
            goTo(CharactersFragmentDirections.actionFirstFragmentToSecondFragment(1))
        }
    }

}
package com.github.wnuk.myhero.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.wnuk.myhero.R

class CharactersListFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersListFragment()
    }

    private lateinit var viewModel: CharactersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.characters_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharactersListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

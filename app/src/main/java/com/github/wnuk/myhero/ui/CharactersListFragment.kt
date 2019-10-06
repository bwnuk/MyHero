package com.github.wnuk.myhero.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.databinding.CharactersListFragmentBinding
import com.github.wnuk.myhero.infrastracture.ListCharacterAdapter
import kotlinx.android.synthetic.main.characters_list_fragment.*

class CharactersListFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersListFragment()
    }

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var binding: CharactersListFragmentBinding
    private lateinit var adapter: ListCharacterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.characters_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharactersListViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.loadData()
        adapter = ListCharacterAdapter(viewModel.listOfCharacters)
        characters_list_fragment__list.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDestroy()
    }
}

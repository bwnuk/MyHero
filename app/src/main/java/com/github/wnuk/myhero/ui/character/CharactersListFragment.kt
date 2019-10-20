package com.github.wnuk.myhero.ui.character

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.databinding.CharactersListFragmentBinding
import com.github.wnuk.myhero.infrastracture.adapters.ListCharacterAdapter
import com.github.wnuk.myhero.model.character.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.characters_list_fragment.*

class CharactersListFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersListFragment()
    }

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var binding: CharactersListFragmentBinding
    private lateinit var adapter: ListCharacterAdapter
    private var myCompositeDisposable: CompositeDisposable? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        binding =
            DataBindingUtil.inflate(inflater, R.layout.characters_list_fragment, container, false)
         layoutManager = LinearLayoutManager(inflater.context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharactersListViewModel::class.java)
        characters_list_fragment__list.layoutManager = layoutManager

        myCompositeDisposable = CompositeDisposable()
        binding.viewmodel = viewModel

        myCompositeDisposable?.add(viewModel.charactersObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                handleResponse(result.map { Character(it) })
            }, { error ->
                error.printStackTrace()
            })
        )
    }

    private fun handleResponse(result: List<Character>) {
        Log.d("CharacterList", "Response size: ${result.size} ")
        viewModel.listOfCharacters = result
        adapter = ListCharacterAdapter(viewModel.listOfCharacters)
        characters_list_fragment__list.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myCompositeDisposable?.dispose()
        myCompositeDisposable?.clear()
    }
}

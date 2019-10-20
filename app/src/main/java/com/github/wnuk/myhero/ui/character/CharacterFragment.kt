package com.github.wnuk.myhero.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.databinding.CharacterFragmentBinding
import com.github.wnuk.myhero.databinding.CharactersListFragmentBinding
import com.github.wnuk.myhero.infrastracture.adapters.ListCharacterAdapter
import com.github.wnuk.myhero.model.character.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.characters_list_fragment.*

class CharacterFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterFragment()
    }

    private lateinit var itemViewModel: CharacterViewModel
    private lateinit var binding: CharacterFragmentBinding
    private var myCompositeDisposable: CompositeDisposable? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.character_fragment, container, false)
        layoutManager = LinearLayoutManager(inflater.context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("CharacterFragment", "Argument: ${arguments!!.getString("idCharacter")}")
        var characterId: String = arguments!!.getString("idCharacter")
        itemViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)

        myCompositeDisposable = CompositeDisposable()
        binding.character = itemViewModel.binder

        myCompositeDisposable?.add(itemViewModel.loadCharacter(characterId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                handleResponse(Character(result))
            }, { error ->
                error.printStackTrace()
            })
        )
    }

    private fun handleResponse(result: Character) {
        Log.d("CharacterFragment", "Character choosen: ${result.name} ")
        itemViewModel.setUpCharacter(character = result)
    }
}

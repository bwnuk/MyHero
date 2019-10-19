package com.github.wnuk.myhero.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.model.character.Character
import com.github.wnuk.myhero.model.character.CharacterEntity
import com.github.wnuk.myhero.model.character.CharacterResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private var myCompositeDisposable: CompositeDisposable? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        myCompositeDisposable = CompositeDisposable()

        myCompositeDisposable?.add(viewModel.loadData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                handleResponse(result)
            }, { error ->
                error.printStackTrace()
            })
        )

        viewModel.deleteAll()

        card_view_characters.setOnClickListener { view ->
            view.findNavController().navigate(R.id.character_action)
        }
    }

    private fun handleResponse(result: CharacterResult) {
        Log.d("Result CharacterPages", "Response size: ${result.info.pages} Next request: ${result.info.next}")

        result.results.forEach{viewModel.insert(CharacterEntity(it))}

        myCompositeDisposable?.add(viewModel.charactersss
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                viewModel.respone(result.map { Character(it) })
            }, { error ->
                error.printStackTrace()
            })
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myCompositeDisposable?.dispose()
        myCompositeDisposable?.clear()
    }
}

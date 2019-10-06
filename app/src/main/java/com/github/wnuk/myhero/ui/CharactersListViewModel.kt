package com.github.wnuk.myhero.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.wnuk.myhero.infrastracture.ApiInterface
import com.github.wnuk.myhero.infrastracture.dto.Character
import com.github.wnuk.myhero.infrastracture.dto.CharacterDTO
import com.github.wnuk.myhero.infrastracture.services.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CharactersListViewModel : ViewModel() {
    var name: String = "AA"
    var listOfCharacters: List<Character> = emptyList()
    private var myCompositeDisposable: CompositeDisposable? = null

    fun loadData(){
        myCompositeDisposable = CompositeDisposable()
        val requestInterface = ApiClient.getClient().create(ApiInterface::class.java)

        myCompositeDisposable?.add(requestInterface.getAlCharacters()
            .observeOn(AndroidSchedulers.mainThread())

            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result ->
                handleResponse(result)
            }, { error ->
                error.printStackTrace()
            }))
    }

    private fun handleResponse(result: CharacterDTO) {
        Log.d("Result", "Response size: ${result.info.count} ")
        listOfCharacters = result.results
    }

    fun onDestroy(){
        myCompositeDisposable?.dispose()
    }
}

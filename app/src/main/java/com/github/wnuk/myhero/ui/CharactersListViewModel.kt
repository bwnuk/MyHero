package com.github.wnuk.myhero.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.wnuk.myhero.infrastracture.ApiInterface
import com.github.wnuk.myhero.infrastracture.dto.CharacterDTO
import com.github.wnuk.myhero.infrastracture.services.ApiClient
import com.github.wnuk.myhero.model.Character
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
                Log.d("Result", "There are ${result.results[0].name} Java developers in Lagos ${result.info.next} ")
            }, { error ->
                error.printStackTrace()
            }))
    }

    private fun handleResponse(list: List<CharacterDTO>) {
    }

    fun onDestroy(){
        myCompositeDisposable?.dispose()
    }
}

package com.github.wnuk.myhero.ui.character

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.github.wnuk.myhero.infrastracture.db.CharacterDb
import com.github.wnuk.myhero.infrastracture.repository.CharacterRepository
import com.github.wnuk.myhero.infrastracture.services.ApiInterface
import com.github.wnuk.myhero.model.character.Character
import com.github.wnuk.myhero.model.character.CharacterResult
import com.github.wnuk.myhero.infrastracture.services.ApiClient
import com.github.wnuk.myhero.model.character.CharacterEntity
import io.reactivex.Observable


class CharactersListViewModel(application: Application) :  AndroidViewModel(application) {
    var name: String = "AA"
    private val repository: CharacterRepository
    var listOfCharacters: List<Character> = emptyList()
    val charactersObservable : Observable<List<CharacterEntity>>

    init {
        val characterDao = CharacterDb.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        charactersObservable = repository.allCharacter
    }

    fun loadData() : Observable<CharacterResult>{
        val requestInterface = ApiClient.getClient().create(ApiInterface::class.java)
        return requestInterface.getAlCharacters()
    }
}

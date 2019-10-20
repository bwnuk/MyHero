package com.github.wnuk.myhero.ui.character

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.wnuk.myhero.infrastracture.db.CharacterDb
import com.github.wnuk.myhero.infrastracture.repository.CharacterRepository
import com.github.wnuk.myhero.infrastracture.services.ApiClient
import com.github.wnuk.myhero.infrastracture.services.ApiInterface
import com.github.wnuk.myhero.model.character.Character
import com.github.wnuk.myhero.model.character.CharacterEntity
import com.github.wnuk.myhero.model.character.CharacterResult
import io.reactivex.Observable


class CharactersListViewModel(application: Application) :  AndroidViewModel(application) {
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

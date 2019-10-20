package com.github.wnuk.myhero.ui.character

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.github.wnuk.myhero.infrastracture.db.CharacterDb
import com.github.wnuk.myhero.infrastracture.repository.CharacterRepository
import com.github.wnuk.myhero.infrastracture.services.ApiClient
import com.github.wnuk.myhero.infrastracture.services.ApiInterface
import com.github.wnuk.myhero.model.character.Character
import com.github.wnuk.myhero.model.character.CharacterEntity
import com.github.wnuk.myhero.model.character.CharacterResult
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class CharactersListViewModel(application: Application) :  AndroidViewModel(application) {
    var listOfCharacters: List<Character> = emptyList()
    val charactersObservable : Observable<List<CharacterEntity>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val repository: CharacterRepository
    private val scope = CoroutineScope(coroutineContext)


    init {
        val characterDao = CharacterDb.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        charactersObservable = repository.allCharacter
    }

    fun loadData(page: Int) : Observable<CharacterResult>{
        val requestInterface = ApiClient.getClient().create(ApiInterface::class.java)
        return requestInterface.getCharactersPage(page)
    }

    fun insert(characterEntity: CharacterEntity) = scope.launch(Dispatchers.IO) {
        repository.insert(characterEntity)
    }

    fun respone(list: List<Character>){
        listOfCharacters = list
        Log.d("MainViewModel", "List size: ${listOfCharacters.size} ")
    }

}

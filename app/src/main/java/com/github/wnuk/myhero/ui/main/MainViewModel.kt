package com.github.wnuk.myhero.ui.main

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.findNavController
import com.github.wnuk.myhero.R
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

class MainViewModel(application: Application) :  AndroidViewModel(application) {
    var listOfCharacters: List<Character> = emptyList()
    val dbCharacters : Observable<List<CharacterEntity>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val repository: CharacterRepository
    private val scope = CoroutineScope(coroutineContext)

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val characterDao = CharacterDb.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        dbCharacters = repository.allCharacter
    }

    fun onCharacterClick(view: View){
        view.findNavController().navigate(R.id.character_action)
    }

    fun loadData() : Observable<CharacterResult> {
        val requestInterface = ApiClient.getClient().create(ApiInterface::class.java)
        return requestInterface.getAlCharacters()
    }

    fun insert(characterEntity: CharacterEntity) = scope.launch(Dispatchers.IO) {
        repository.insert(characterEntity)
    }

    fun deleteAll() = scope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

    fun respone(list: List<Character>){
        listOfCharacters = list
        Log.d("MainViewModel", "List size: ${listOfCharacters.size} ")
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}

package com.github.wnuk.myhero.ui.character

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.github.wnuk.myhero.infrastracture.db.CharacterDb
import com.github.wnuk.myhero.infrastracture.repository.CharacterRepository
import com.github.wnuk.myhero.model.character.Character
import com.github.wnuk.myhero.model.character.CharacterEntity
import com.github.wnuk.myhero.ui.character.bindings.CharacterBinder
import io.reactivex.Observable

class CharacterViewModel(application: Application) :  AndroidViewModel(application) {
    private val repository: CharacterRepository
    var character: Character? = null
    val binder = CharacterBinder()

    init {
        val characterDao = CharacterDb.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)

    }

    public fun loadCharacter(id: String): Observable<CharacterEntity>{
        return repository.getCharacter(id)
    }

    public fun setUpCharacter(character: Character){
        this.character = character
        binder.name = character.name
        binder.gender = character.gender
        binder.location = character.location
        binder.origin = character.origin
        binder.status = character.status
        binder.species = character.species
        binder.type = character.type
    }
}
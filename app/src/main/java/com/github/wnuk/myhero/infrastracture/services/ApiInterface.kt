package com.github.wnuk.myhero.infrastracture.services

import com.github.wnuk.myhero.model.character.CharacterResult
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("character")
    fun getAlCharacters(): Observable<CharacterResult>
}
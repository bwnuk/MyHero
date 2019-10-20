package com.github.wnuk.myhero.infrastracture.services

import com.github.wnuk.myhero.model.character.CharacterResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("character")
    fun getAlCharacters(): Observable<CharacterResult>
    @GET("character")
    fun getCharactersPage(@Query("page")page: Int): Observable<CharacterResult>
}
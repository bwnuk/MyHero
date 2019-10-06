package com.github.wnuk.myhero.infrastracture

import com.github.wnuk.myhero.infrastracture.dto.CharacterDTO
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("character?page=1")
    fun getAlCharacters(): Observable<CharacterDTO>
}
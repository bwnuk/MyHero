package com.github.wnuk.myhero.infrastracture

import com.github.wnuk.myhero.model.Character
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("/character")
    fun getAlCharacters(): Observable<List<Character>>
}
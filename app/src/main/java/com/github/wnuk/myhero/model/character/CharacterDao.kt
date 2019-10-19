package com.github.wnuk.myhero.model.character

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface CharacterDao{
    @Query("SELECT * FROM characters")
    fun getAll(): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Observable<List<CharacterEntity>>

    @Insert
    fun insertAll(vararg characters: CharacterEntity)

    @Insert
    fun insert(vararg characters: CharacterEntity)

    @Delete
    fun delete(user: CharacterEntity)

    @Query("DELETE FROM characters")
    fun deleteAll()
}
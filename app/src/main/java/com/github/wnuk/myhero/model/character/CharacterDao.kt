package com.github.wnuk.myhero.model.character

import androidx.room.*
import io.reactivex.Observable

@Dao
interface CharacterDao{
    @Query("SELECT * FROM characters")
    fun getAll(): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: String): Observable<CharacterEntity>

    @Insert
    fun insertAll(vararg characters: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg characters: CharacterEntity)

    @Delete
    fun delete(user: CharacterEntity)

    @Query("DELETE FROM characters")
    fun deleteAll()
}
package com.github.wnuk.myhero.model.character

import androidx.room.ColumnInfo

import androidx.room.Entity

import androidx.room.PrimaryKey
import com.github.wnuk.myhero.model.episode.Episode
import com.github.wnuk.myhero.model.location.Location

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    var status: String,
    val species: String,
    var type: String,
    var gender: String,
    var origin: String,
    var location: String,
    var image: String,
    val created: String
) {
    constructor(dto: CharacterDto) : this(
        dto.id,
        dto.name,
        dto.status,
        dto.species,
        dto.type,
        dto.gender,
        dto.origin.name,
        dto.location.name,
        dto.image,
        dto.created
    )
}
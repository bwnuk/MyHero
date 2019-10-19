package com.github.wnuk.myhero.model.character

import com.github.wnuk.myhero.model.episode.Episode
import com.github.wnuk.myhero.model.location.Location

data class Character(
    val id: String,
    val name: String,
    var status: String,
    val species: String,
    var type: String,
    var gender: String,
    var origin: String,
    var location: String,
    var image: String,
    var episode: List<String>,
    val created: String
){
    constructor(dto: CharacterEntity) : this(
        dto.id,
        dto.name,
        dto.status,
        dto.species,
        dto.type,
        dto.gender,
        dto.origin,
        dto.location,
        dto.image,
        emptyList<String>(),
        dto.created
    )
}


package com.github.wnuk.myhero.model

data class Character(
    val id: Int,
    val name: String,
    var status: String,
    val species: String,
    var type: String,
    var gender: String,
    var origin: Location,
    var location: Location,
    var image: String,
    var episode: List<Episode>,
    val created: String
)


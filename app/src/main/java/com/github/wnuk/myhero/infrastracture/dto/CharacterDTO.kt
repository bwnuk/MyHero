package com.github.wnuk.myhero.infrastracture.dto


data class CharacterDTO(var info: InfoCharacter,
                           var results: List<Character>)

data class InfoCharacter(val count: Int,
                         var pages: Int,
                         var next: String,
                         var prev: String)

data class Character(
    val id: Int,
    val name: String,
    var status: String,
    val species: String,
    var type: String,
    var gender: String,
    val origin: CharacterLocation,
    var location: CharacterLocation,
    var image: String,
    var episode: List<String>,
    val created: String
)

data class CharacterLocation(
    var name: String,
    var url: String
)

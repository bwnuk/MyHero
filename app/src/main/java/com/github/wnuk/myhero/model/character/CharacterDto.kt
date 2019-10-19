package com.github.wnuk.myhero.model.character


data class CharacterResult(var info: InfoCharacter,
                        var results: List<CharacterDto>)

data class InfoCharacter(val count: Int,
                         var pages: Int,
                         var next: String,
                         var prev: String)

data class CharacterDto(
    val id: String,
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

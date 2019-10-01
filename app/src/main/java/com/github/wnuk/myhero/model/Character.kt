package com.github.wnuk.myhero.model

data class Character(val id: Int, val name: String, var status: String, val species: String, var type: String, var gender: String, val origin: String, var image: String, var episode: List<String>, val created: String)
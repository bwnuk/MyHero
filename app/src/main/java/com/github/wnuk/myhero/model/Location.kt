package com.github.wnuk.myhero.model

data class Location(val id: Int, val name: String, val type: String, val dimensions: String, var residents: List<Character>)
package com.github.wnuk.myhero.model

import java.util.*

data class Episode(val id: Int, val name: String, val airDate: String, val episode: String, var characters: List<Character>, val created: String)
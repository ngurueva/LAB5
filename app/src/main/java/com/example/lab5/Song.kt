package com.example.lab5

import java.io.Serializable

data class Song(val title: String, val artist: String, val album: String, val isFavorite: Boolean) : Serializable  {
    override fun toString(): String {
        return "$title - $artist"
    }
}
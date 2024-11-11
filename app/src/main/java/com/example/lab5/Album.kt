package com.example.lab5

import java.io.Serializable

data class Album(val title: String, val artist: String) : Serializable  {
    override fun toString(): String {
        return "$title - $artist"
    }
}

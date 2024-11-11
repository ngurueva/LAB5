package com.example.lab5

import java.io.Serializable

data class Artist(val name: String) : Serializable  {
    override fun toString(): String {
        return "$name"
    }
}

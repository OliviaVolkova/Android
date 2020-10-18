package com.example.list

import java.util.*

data class Cat(
    val name: String,
    val description: String,
    val pic: Int
) {
    val id: Int = this.generateId()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cat

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (pic != other.pic) return false

        return true
    }

    private fun generateId(): Int {
        var result = 17
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + pic
        return result
    }
}
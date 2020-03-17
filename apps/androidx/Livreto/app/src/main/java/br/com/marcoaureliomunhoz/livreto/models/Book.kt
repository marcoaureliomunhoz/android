package br.com.marcoaureliomunhoz.livreto.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Book (
    @PrimaryKey()
    val id: Int,
    val name: String
) : Serializable {

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        return id == (other as Book).id
    }

}
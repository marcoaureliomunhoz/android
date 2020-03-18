package br.com.marcoaureliomunhoz.livreto.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Chapter (
    @PrimaryKey()
    val id: Int,
    val name: String,
    val number: Int,
    val description: String,
    val bookId: Int
) : Serializable {

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        return id == (other as Chapter).id
    }

}
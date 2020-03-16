package br.com.marcoaureliomunhoz.biblio.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.marcoaureliomunhoz.biblio.helpers.base.DateHelper
import java.io.Serializable

@Entity
data class Editora (

    @PrimaryKey
    var _id: String = "",
    var nome: String = "",
    var telefone: String = ""

) : Serializable {

    fun definirId(complemento: Int = 0) {
        val data = DateHelper.stringOfCurrentDate()
        _id = (data + " " + (complemento + 1))
            .replace("/", "")
            .replace(":", "")
            .replace(" ", "_")
    }

    fun alterar(nome: String, telefone: String) {
        this.nome = nome
        this.telefone = telefone
    }

    fun validacao(): String? {
        if (nome.isEmpty()) return "Nome não informado."

        return null
    }

    override fun toString(): String {
        return nome
    }

    override fun equals(other: Any?): Boolean {
        return _id == (other as Editora)._id
    }
}
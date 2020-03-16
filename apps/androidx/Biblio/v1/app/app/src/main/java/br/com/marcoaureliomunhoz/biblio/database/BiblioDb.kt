package br.com.marcoaureliomunhoz.biblio.database

import android.util.Log
import br.com.marcoaureliomunhoz.biblio.models.Editora

class BiblioDb {

    init {
        Log.i("biblio_db", this.toString())
    }

    val editoras = mutableListOf<Editora>()

    /*companion object {
        val editoras = mutableListOf<Editora>()
    }*/

    companion object {
        private lateinit var db: BiblioDb

        fun getInstance(): BiblioDb {
            if (::db.isInitialized) return db

            db = BiblioDb()

            return db
        }
    }

}
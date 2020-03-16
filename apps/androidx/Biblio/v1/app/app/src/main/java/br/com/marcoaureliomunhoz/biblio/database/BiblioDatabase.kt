package br.com.marcoaureliomunhoz.biblio.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.marcoaureliomunhoz.biblio.database.dao.EditoraDAO
import br.com.marcoaureliomunhoz.biblio.models.Editora

private const val DATABASE_NAME = "biblioapp.db"

@Database(entities = [Editora::class], version = 1, exportSchema = false)
abstract class BiblioDatabase : RoomDatabase() {

    abstract val editoraDAO: EditoraDAO

    companion object {
        private lateinit var db: BiblioDatabase

        fun getInstance(context: Context): BiblioDatabase {
            if (::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                BiblioDatabase::class.java,
                DATABASE_NAME
            ).build()

            return db
        }
    }

}
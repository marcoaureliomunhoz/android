package br.com.marcoaureliomunhoz.livreto.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.marcoaureliomunhoz.livreto.databases.dao.BookDao
import br.com.marcoaureliomunhoz.livreto.databases.dao.ChapterDao
import br.com.marcoaureliomunhoz.livreto.models.Book
import br.com.marcoaureliomunhoz.livreto.models.Chapter

private const val DATABASE_NAME = "livreto.db"

@Database(entities = [ Book::class, Chapter::class ], version = 1, exportSchema = false)
abstract class LivretoDb : RoomDatabase() {

    abstract val bookDao: BookDao
    abstract val chapterDao: ChapterDao

    companion object {
        private lateinit var db: LivretoDb

        fun getInstance(context: Context): LivretoDb {
            if (::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                LivretoDb::class.java,
                DATABASE_NAME
            ).addCallback(CALLBACK)
            .build()

            return db
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                db.execSQL("insert into book (id, name) values (1, 'Android 4ªEd')")
                db.execSQL("insert into chapter (id, name, number, description, bookId) values (1, 'Activity', 1, 'Activity bla bla bla', 1)")
                db.execSQL("insert into chapter (id, name, number, description, bookId) values (2, 'Fragment', 2, 'Fragment bla bla bla', 1)")


                db.execSQL("insert into book (id, name) values (2, 'Android 5ªEd')")
                db.execSQL("insert into chapter (id, name, number, description, bookId) values (3, 'Activity Revisão', 1, 'Activity Revisão bla bla bla', 2)")
                db.execSQL("insert into chapter (id, name, number, description, bookId) values (4, 'Fragment Revisão', 2, 'Fragment Revisão bla bla bla', 2)")
                db.execSQL("insert into chapter (id, name, number, description, bookId) values (5, 'Room', 3, 'Room bla bla bla', 2)")
            }
        }
    }

}
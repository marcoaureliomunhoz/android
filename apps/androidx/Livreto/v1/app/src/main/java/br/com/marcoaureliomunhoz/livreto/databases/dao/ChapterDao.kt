package br.com.marcoaureliomunhoz.livreto.databases.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import br.com.marcoaureliomunhoz.livreto.models.Chapter

@Dao
interface ChapterDao {

    @Query("select * from chapter where bookId = :bookId")
    fun list(bookId: Int): LiveData<List<Chapter>>

    @Insert(onConflict = REPLACE)
    fun insert(book: Chapter)

    @Update
    fun update(book: Chapter)

    @Delete
    fun delete(book: Chapter)

}
package br.com.marcoaureliomunhoz.livreto.databases.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import br.com.marcoaureliomunhoz.livreto.models.Book

@Dao
interface BookDao {

    @Query("select * from book")
    fun list(): LiveData<List<Book>>

    @Insert(onConflict = REPLACE)
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

}
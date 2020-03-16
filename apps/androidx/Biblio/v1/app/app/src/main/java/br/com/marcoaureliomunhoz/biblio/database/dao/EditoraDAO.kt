package br.com.marcoaureliomunhoz.biblio.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import br.com.marcoaureliomunhoz.biblio.models.Editora

@Dao
interface EditoraDAO {

    @Query("select * from editora")
    fun listar(): LiveData<List<Editora>>

    @Insert(onConflict = REPLACE)
    fun inserir(editora: Editora)

    @Update
    fun alterar(editora: Editora)

    @Delete
    fun excluir(editora: Editora)

}
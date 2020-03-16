package br.com.marcoaureliomunhoz.biblio.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.marcoaureliomunhoz.biblio.asynctask.BaseAsyncTask
import br.com.marcoaureliomunhoz.biblio.database.BiblioDb
import br.com.marcoaureliomunhoz.biblio.database.dao.EditoraDAO
import br.com.marcoaureliomunhoz.biblio.helpers.base.DateHelper
import br.com.marcoaureliomunhoz.biblio.models.Editora

public class EditoraRepository (
    private val dao: EditoraDAO
)  {

    private val mediator = MediatorLiveData<Resource<List<Editora>?>>()

    fun listar() : LiveData<Resource<List<Editora>?>> {
        mediator.addSource(roomLiveData()) {editoras ->
            mediator.value = Resource(editoras)
        }

        return mediator
    }

    fun salvar(editora: Editora) : LiveData<Resource<String?>> {
        val liveDataTemp = MutableLiveData<Resource<String?>>()

        val validacao = editora.validacao()

        if (validacao.isNullOrEmpty()) {
            if (editora._id.isEmpty()) {
                editora.definirId()
                inserirSqlite(editora, comSucesso = {
                    liveDataTemp.value = Resource(editora._id, validacao)
                })
            } else {
                alterarSqlite(editora, comSucesso = {
                    liveDataTemp.value = Resource(editora._id, validacao)
                })
            }
        } else {
            liveDataTemp.value = Resource(null, validacao)
        }

        return liveDataTemp
    }

    fun excluir(editora: Editora) {
        excluirSqLite(editora)
    }

    private fun inserirSqlite(
        editora: Editora,
        comSucesso: () -> Unit
    ) {
        BaseAsyncTask(
            aoExecutar = { dao.inserir(editora) },
            aoFinalizar = { comSucesso() }
        ).execute()
    }

    private fun alterarSqlite(
        editora: Editora,
        comSucesso: () -> Unit
    ) {
        BaseAsyncTask(
            aoExecutar = { dao.alterar(editora) },
            aoFinalizar = { comSucesso() }
        ).execute()
    }

    private fun excluirSqLite(
        editora: Editora,
        comSucesso: () -> Unit = {}
    ) {
        BaseAsyncTask(
            aoExecutar = { dao.excluir(editora) },
            aoFinalizar = { comSucesso() }
        ).execute()
    }

    private fun roomLiveData() : LiveData<List<Editora>> {
        return dao.listar()
    }

}
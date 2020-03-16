package br.com.marcoaureliomunhoz.biblio.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.marcoaureliomunhoz.biblio.models.Editora
import br.com.marcoaureliomunhoz.biblio.repositories.EditoraRepository
import br.com.marcoaureliomunhoz.biblio.repositories.Resource

public class EditoraActivityViewModel(
    private val repository: EditoraRepository //property
) : ViewModel() {

    //as tomadas de decisão referentes ao model da activity deve ficar aqui no ViewModel
    //ou seja, a responsabilidade do ViewModel é cuidar dos dados dinâmicos da Activity

    fun <T> listar() : LiveData<Resource<T?>> {
        return repository.listar() as LiveData<Resource<T?>>
    }

    fun <T> salvar(editora: Editora) : LiveData<Resource<T?>> {
        return repository.salvar(editora) as LiveData<Resource<T?>>
    }

    fun excluir(editora: Editora) {
        repository.excluir(editora)
    }

}

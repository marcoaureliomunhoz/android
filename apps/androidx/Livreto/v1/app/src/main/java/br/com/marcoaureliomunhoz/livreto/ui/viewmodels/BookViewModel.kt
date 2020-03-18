package br.com.marcoaureliomunhoz.livreto.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.marcoaureliomunhoz.livreto.repositories.BookRepository

public class BookViewModel(
    private val repository: BookRepository
) : ViewModel() {

    fun <T> list() : LiveData<T?> {
        return repository.list() as LiveData<T?>
    }

}
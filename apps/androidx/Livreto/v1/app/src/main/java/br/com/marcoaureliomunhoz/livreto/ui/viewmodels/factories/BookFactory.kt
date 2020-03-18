package br.com.marcoaureliomunhoz.livreto.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.marcoaureliomunhoz.livreto.repositories.BookRepository
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.BookViewModel

class BookFactory (
    private val repository: BookRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookViewModel(repository) as T
    }

}
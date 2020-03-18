package br.com.marcoaureliomunhoz.livreto.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.marcoaureliomunhoz.livreto.repositories.ChapterRepository

public class ChapterViewModel(
    private val repository: ChapterRepository
) : ViewModel() {

    fun <T> list(bookId: Int) : LiveData<T?> {
        return repository.list(bookId) as LiveData<T?>
    }

}
package br.com.marcoaureliomunhoz.livreto.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.marcoaureliomunhoz.livreto.repositories.ChapterRepository
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.ChapterViewModel

class ChapterFactory (
    private val repository: ChapterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChapterViewModel(repository) as T
    }

}
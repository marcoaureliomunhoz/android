package br.com.marcoaureliomunhoz.biblio.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.marcoaureliomunhoz.biblio.repositories.EditoraRepository
import br.com.marcoaureliomunhoz.biblio.ui.viewmodels.EditoraActivityViewModel

class EditoraViewModelFactory(
    private val repository: EditoraRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditoraActivityViewModel(repository) as T
    }

}
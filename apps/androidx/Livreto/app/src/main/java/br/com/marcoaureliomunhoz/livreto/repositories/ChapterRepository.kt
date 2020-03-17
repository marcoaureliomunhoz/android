package br.com.marcoaureliomunhoz.livreto.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.marcoaureliomunhoz.livreto.databases.dao.ChapterDao
import br.com.marcoaureliomunhoz.livreto.models.Chapter

public class ChapterRepository (
    private val dao: ChapterDao
)  {

    private val mediator = MediatorLiveData<List<Chapter>?>()

    fun list(bookId: Int) : LiveData<List<Chapter>?> {
        mediator.addSource(roomLiveData(bookId)) { chapters ->
            mediator.value = chapters
        }

        return mediator
    }

    private fun roomLiveData(bookId: Int) : LiveData<List<Chapter>> {
        return dao.list(bookId)
    }

}
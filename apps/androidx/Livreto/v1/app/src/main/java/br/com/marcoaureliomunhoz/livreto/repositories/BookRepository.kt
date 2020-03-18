package br.com.marcoaureliomunhoz.livreto.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.marcoaureliomunhoz.livreto.databases.dao.BookDao
import br.com.marcoaureliomunhoz.livreto.models.Book

public class BookRepository (
    private val dao: BookDao
)  {

    private val mediator = MediatorLiveData<List<Book>?>()

    fun list() : LiveData<List<Book>?> {
        mediator.addSource(roomLiveData()) { books ->
            mediator.value = books
        }

        return mediator
    }

    private fun roomLiveData() : LiveData<List<Book>> {
        return dao.list()
    }

}
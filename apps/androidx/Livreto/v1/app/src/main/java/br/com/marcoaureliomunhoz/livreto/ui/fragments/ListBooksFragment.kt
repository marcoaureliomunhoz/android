package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.databases.LivretoDb
import br.com.marcoaureliomunhoz.livreto.models.Book
import br.com.marcoaureliomunhoz.livreto.repositories.BookRepository
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.BookViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.factories.BookFactory
import kotlinx.android.synthetic.main.fragment_list_books.*

class ListBooksFragment : Fragment() {

    private val adapter by lazy {
        ArrayAdapter<Book>(context as Context, android.R.layout.simple_list_item_1)
    }

    private val viewModel by lazy {
        val repository = BookRepository(LivretoDb.getInstance(context as Context).bookDao)
        val factory = BookFactory(repository)
        ViewModelProvider(this, factory).get(BookViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listBooks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.books)

        listViewBooks.adapter = adapter
        listViewBooks.setOnItemClickListener { parent, view, position, id ->
            val book = adapter.getItem(position) as Book
            val directions = ListBooksFragmentDirections.actionListBooksFragmentToListChaptersFragment(book)
            findNavController().navigate(directions)
        }
    }

    private fun listBooks() {
        viewModel.list<MutableList<Book>>().observe(this, Observer { list ->
            list?.let { updateAdapter(list) }
        })
    }

    private fun updateAdapter(list: List<Book>) {
        adapter.clear()
        adapter.addAll(list)
    }

}

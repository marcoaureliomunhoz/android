package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.databases.LivretoDb
import br.com.marcoaureliomunhoz.livreto.models.Book
import br.com.marcoaureliomunhoz.livreto.models.Chapter
import br.com.marcoaureliomunhoz.livreto.repositories.ChapterRepository
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.ChapterViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.factories.ChapterFactory
import kotlinx.android.synthetic.main.fragment_list_chapters.*

class ListChaptersFragment : Fragment() {

    private val adapter by lazy {
        ArrayAdapter<Chapter>(context as Context, android.R.layout.simple_list_item_1)
    }

    private val viewModel by lazy {
        val repository = ChapterRepository(LivretoDb.getInstance(context as Context).chapterDao)
        val factory = ChapterFactory(repository)
        ViewModelProvider(this, factory).get(ChapterViewModel::class.java)
    }

    private val safeArguments : ListChaptersFragmentArgs by navArgs()
    private val model : Book by lazy { safeArguments.model }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listChapters()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_chapters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = model.name

        listViewChapters.adapter = adapter
        listViewChapters.setOnItemClickListener { parent, view, position, id ->
            val chapter = adapter.getItem(position) as Chapter
            val direction = ListChaptersFragmentDirections.actionListChaptersFragmentToDescriptionFragment(chapter)
            findNavController().navigate(direction)
        }
    }

    private fun listChapters() {
        viewModel.list<MutableList<Chapter>>(model.id).observe(this, Observer { list ->
            list?.let { updateAdapter(list) }
        })
    }

    private fun updateAdapter(list: List<Chapter>) {
        adapter.clear()
        adapter.addAll(list)
    }

}

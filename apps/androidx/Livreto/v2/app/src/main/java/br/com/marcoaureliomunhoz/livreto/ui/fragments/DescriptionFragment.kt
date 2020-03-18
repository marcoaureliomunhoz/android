package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.models.Chapter
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.StateAppViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.VisualComponent
import kotlinx.android.synthetic.main.fragment_description.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DescriptionFragment : BaseFragment() {

    private val safeArguments : DescriptionFragmentArgs by navArgs()
    private val model : Chapter by lazy { safeArguments.model }
    private val stateAppViewModel : StateAppViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateAppViewModel.hasAppVisualComponent = VisualComponent(true)

        activity?.title = model.name

        textViewDescriptionChapter.text = model.description
        buttonBackHome.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_descriptionFragment_to_listBooksFragment)
        }
    }

}

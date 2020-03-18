package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.StateAppViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.VisualComponent
import kotlinx.android.synthetic.main.fragment_user_form.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserFormFragment : Fragment() {

    private val stateAppViewModel : StateAppViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateAppViewModel.hasAppVisualComponent = VisualComponent(true)

        activity?.title = getString(R.string.new_user)

        buttonSaveNewUser.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}

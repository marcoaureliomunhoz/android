package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.LoginViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.StateAppViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.VisualComponent
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private val loginViewModel : LoginViewModel by viewModel()
    private val stateAppViewModel : StateAppViewModel by sharedViewModel()
    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateAppViewModel.hasAppVisualComponent = VisualComponent()

        buttonConfirmLogin.setOnClickListener {
            loginViewModel.login()
            goToListBooks()
        }

        buttonNewUser.setOnClickListener {
            goToNewUser()
        }
    }

    private fun goToNewUser() {
        val directions = LoginFragmentDirections.actionLoginFragmentToUserFormFragment()
        controller.navigate(directions)
    }

    private fun goToListBooks() {
        val directions = LoginFragmentDirections.actionLoginFragmentToListBooksFragment()
        controller.navigate(directions)
    }

}

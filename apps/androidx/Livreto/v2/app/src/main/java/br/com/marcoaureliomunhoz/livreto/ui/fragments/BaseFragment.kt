package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.marcoaureliomunhoz.livreto.NavGraphDirections
import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {

    private val loginViewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        if (!loginViewModel.isLogged()) {
            goToLogin()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.menu_item_logout) {
            loginViewModel.logout()
            goToLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLogin() {
        val directions = NavGraphDirections.actionGlobalLoginFragment()
        findNavController().navigate(directions)
    }
}
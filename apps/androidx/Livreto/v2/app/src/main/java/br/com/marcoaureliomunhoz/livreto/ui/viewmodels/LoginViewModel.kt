package br.com.marcoaureliomunhoz.livreto.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.marcoaureliomunhoz.livreto.repositories.LoginRepository

public class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    fun login() {
        return repository.login()
    }

    fun logout() {
        return repository.logout()
    }

    fun isLogged() : Boolean {
        return repository.isLogged()
    }

}
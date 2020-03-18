package br.com.marcoaureliomunhoz.livreto.repositories

import android.content.SharedPreferences
import androidx.core.content.edit

public class LoginRepository (private val preferences: SharedPreferences)  {

    fun login() {
        val edit = preferences.edit {
            putBoolean("login", true)
        }
    }

    fun logout() {
        val edit = preferences.edit {
            putBoolean("login", false)
        }
    }

    fun isLogged() = preferences.getBoolean("login", false)

}
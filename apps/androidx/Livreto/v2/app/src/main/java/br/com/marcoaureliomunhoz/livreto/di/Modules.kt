package br.com.marcoaureliomunhoz.livreto.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import br.com.marcoaureliomunhoz.livreto.repositories.LoginRepository
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.LoginViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.StateAppViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val appModule = module {

}

val repositoryModule = module {

    single<LoginRepository> { LoginRepository(get()) }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(androidApplication()) }

}

val viewModelModule = module {

    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<StateAppViewModel> { StateAppViewModel() }

}
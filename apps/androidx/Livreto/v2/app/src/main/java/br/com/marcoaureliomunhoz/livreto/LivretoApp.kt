package br.com.marcoaureliomunhoz.livreto

import android.app.Application
import br.com.marcoaureliomunhoz.livreto.di.appModule
import br.com.marcoaureliomunhoz.livreto.di.repositoryModule
import br.com.marcoaureliomunhoz.livreto.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LivretoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LivretoApp)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}
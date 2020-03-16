package br.com.marcoaureliomunhoz.biblio.asynctask

import android.os.AsyncTask

class BaseAsyncTask<T>(
    private val aoExecutar: () -> T,
    private val aoFinalizar: (resultado: T) -> Unit
) : AsyncTask<Void, Void, T>() {

    override fun doInBackground(vararg params: Void?) = aoExecutar()

    override fun onPostExecute(resultado: T) {
        super.onPostExecute(resultado)
        aoFinalizar(resultado)
    }

}
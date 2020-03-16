package br.com.marcoaureliomunhoz.biblio.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.marcoaureliomunhoz.biblio.R
import br.com.marcoaureliomunhoz.biblio.database.BiblioDatabase
import br.com.marcoaureliomunhoz.biblio.models.Editora
import br.com.marcoaureliomunhoz.biblio.repositories.EditoraRepository
import br.com.marcoaureliomunhoz.biblio.ui.viewmodels.EditoraActivityViewModel
import br.com.marcoaureliomunhoz.biblio.ui.viewmodels.factories.EditoraViewModelFactory
import kotlinx.android.synthetic.main.activity_form_editora.*

class FormEditoraActivity : AppCompatActivity() {

    private lateinit var model : Editora

    //vale registrar que os dados presentes nos componentes/views da Activity são salvos pelo
    //android ao girar o dispositivo por exemplo
    //portanto, esse tipo de dado não precisamos nos preocupar, o próprio android cuida

    private val viewModel by lazy {
        val repository = EditoraRepository(BiblioDatabase.getInstance(this).editoraDAO)
        val factory = EditoraViewModelFactory(repository)
        ViewModelProvider(this, factory).get(EditoraActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_editora)

        title = getString(R.string.formulario)

        val extraModel = ListaEditorasActivity.EXTRA_MODEL
        if (this.intent.hasExtra(extraModel)) {
            model = this.intent.getSerializableExtra(extraModel) as Editora
            editTextNomeEditora.setText(model.nome)
            editTextTelefoneEditora.setText(model.telefone)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_form_editora_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        salvarEditora()
        return super.onOptionsItemSelected(item)
    }

    private fun salvarEditora() {
        val nome = editTextNomeEditora.text.toString()
        val telefone = editTextTelefoneEditora.text.toString()

        if (this::model.isInitialized) {
            model.alterar(nome, telefone)
        } else {
            model = Editora(nome = nome, telefone = telefone)
        }

        viewModel.salvar<String>(model).observe(this, Observer { resource ->
            resource.data?.let { finish() }
            resource.messages?.let { messages -> Toast.makeText(this, messages, Toast.LENGTH_LONG).show() }
        })
    }
}

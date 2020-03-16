package br.com.marcoaureliomunhoz.biblio.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marcoaureliomunhoz.biblio.R
import br.com.marcoaureliomunhoz.biblio.adapters.ListaEditorasRecyclerAdapter
import br.com.marcoaureliomunhoz.biblio.database.BiblioDatabase
import br.com.marcoaureliomunhoz.biblio.models.Editora
import br.com.marcoaureliomunhoz.biblio.repositories.EditoraRepository
import br.com.marcoaureliomunhoz.biblio.ui.viewmodels.EditoraActivityViewModel
import br.com.marcoaureliomunhoz.biblio.ui.viewmodels.factories.EditoraViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_lista_editoras.*

public class ListaEditorasActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MODEL = "model"
    }

    private val context = this;
    private val adapter by lazy { ListaEditorasRecyclerAdapter(context) }

    private val viewModel by lazy {
        //se tiver vínculo com o android (Context) tem enviar para o viewModel via factory
        val repository = EditoraRepository(BiblioDatabase.getInstance(this).editoraDAO)
        val factory = EditoraViewModelFactory(repository)
        ViewModelProvider(this, factory).get(EditoraActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_editoras)

        title = getString(R.string.app_name);

        Log.i("viewmodel", viewModel.toString());

        val divisor = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerViewEditoras.layoutManager = LinearLayoutManager(context)
        recyclerViewEditoras.addItemDecoration(divisor)
        recyclerViewEditoras.adapter = adapter
        adapter.aoClicarItem = this::abrirFormEdicao
        adapter.aoClicarLongoItem = this::removerItem

        obterEditoras();
    }

    private fun abrirFormEdicao(editora: Editora) {
        val intent = Intent(this, FormEditoraActivity::class.java)
        intent.putExtra(EXTRA_MODEL, editora)
        startActivity(intent)
    }

    private fun removerItem(editora: Editora) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.atencao))
        builder.setMessage(resources.getString(R.string.message_text_confirmacao_exclusao, editora.nome))
        builder.setPositiveButton(getString(R.string.sim)) { _, _ ->
            viewModel.excluir(editora)
        }
        builder.setNegativeButton(getString(R.string.nao)) { _, _ -> }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_lista_editoras_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(context, FormEditoraActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    private fun obterEditoras() {
        viewModel.listar<MutableList<Editora>>().observe(this, Observer { resource ->
            var gson = Gson()
            var json = gson.toJson(resource)
            Log.i("resource-json: ", json)

            resource.data?.let { dados -> adapter.atualizar(dados) }
            resource.messages?.let { messages -> Toast.makeText(this, messages, Toast.LENGTH_LONG).show() }
        })
    }
}

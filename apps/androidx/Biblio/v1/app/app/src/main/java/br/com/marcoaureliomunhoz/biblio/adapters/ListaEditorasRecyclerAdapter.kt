package br.com.marcoaureliomunhoz.biblio.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marcoaureliomunhoz.biblio.R
import br.com.marcoaureliomunhoz.biblio.models.Editora
import kotlinx.android.synthetic.main.item_recycler_view_lista_editoras.view.*

class ListaEditorasRecyclerAdapter(
    private val context: Context,
    private val editoras: MutableList<Editora> = mutableListOf(),
    var aoClicarItem: (editora: Editora) -> Unit = {},
    var aoClicarLongoItem: (editora: Editora) -> Unit = {}
) : RecyclerView.Adapter<ListaEditorasRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(
                R.layout.item_recycler_view_lista_editoras,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun getItemCount() = editoras.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val editora = editoras[position]
        holder.vincular(editora)
    }

    fun atualizar(editoras: List<Editora>) {
        this.editoras.clear()
        this.editoras.addAll(editoras)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener
    {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        private lateinit var editora: Editora

        override fun onClick(v: View?) {
            if (::editora.isInitialized) {
                aoClicarItem(editora)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            if (::editora.isInitialized) {
                aoClicarLongoItem(editora)
            }
            return true;
        }

        fun vincular(editora: Editora) {
            this.editora = editora
            itemView.textNomeDeRecyclerViewListaEditoras.text = editora.nome
        }

    }

}
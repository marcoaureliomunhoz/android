package br.com.marcoaureliomunhoz.ceep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaNotaAdapter extends RecyclerView.Adapter<ListaNotaAdapter.NotaViewHolder> {

    private final List<Nota> notas;
    private final Context context;

    public ListaNotaAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    public void update(List<Nota> notas) {
        this.notas.clear();
        this.notas.addAll(notas);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListaNotaAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.nota_item, parent, false);

        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNotaAdapter.NotaViewHolder holder, int position) {
        holder.setNota(notas.get(position));
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitulo;
        private final TextView txtDescricao;

        NotaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
        }

        void setNota(Nota nota) {
            txtTitulo.setText(nota.getTitulo());
            txtDescricao.setText(nota.getDescricao());
        }
    }
}

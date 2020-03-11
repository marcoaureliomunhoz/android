package br.com.marcoaureliomunhoz.ceep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ListaNotaAdapter extends RecyclerView.Adapter<ListaNotaAdapter.NotaViewHolder> {

    private final List<Nota> notas;
    private final Context context;
    private OnItemRecyclerNotasClickListener onItemRecyclerClickListener;

    public ListaNotaAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    public void update(List<Nota> notas) {
        this.notas.clear();
        this.notas.addAll(notas);

        this.notifyDataSetChanged();
    }

    public void updateRange(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(notas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(notas, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void swap(int initialPosition, int finalPosition) {
        Collections.swap(notas, initialPosition, finalPosition);
        notifyItemMoved(initialPosition, finalPosition);
    }

    public void remove(int posicao) {
        notas.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public void setOnItemRecyclerClickListener(OnItemRecyclerNotasClickListener onItemRecyclerClickListener) {
        this.onItemRecyclerClickListener = onItemRecyclerClickListener;
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
        private Nota nota;

        NotaViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemRecyclerClickListener.onItemClick(nota, getAdapterPosition());
                }
            });
        }

        void setNota(Nota nota) {
            this.nota = nota;
            txtTitulo.setText(nota.getTitulo());
            txtDescricao.setText(nota.getDescricao());
        }
    }
}

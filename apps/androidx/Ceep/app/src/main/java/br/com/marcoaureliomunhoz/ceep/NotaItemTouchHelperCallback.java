package br.com.marcoaureliomunhoz.ceep;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ListaNotaAdapter adapter;

    public NotaItemTouchHelperCallback(ListaNotaAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags =  ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int dragFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder sourceViewHolder,
                          @NonNull RecyclerView.ViewHolder targetViewHolder) {
        int positionSource = sourceViewHolder.getAdapterPosition();
        int positionTarget = targetViewHolder.getAdapterPosition();

        /*CeepApplication.dao.mudarOrdem(positionSource, positionTarget);
        adapter.updateRange(positionSource, positionTarget);*/

        CeepApplication.dao.trocarPosicao(positionSource, positionTarget);
        adapter.swap(positionSource, positionTarget);

        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        CeepApplication.dao.remover(position);
        adapter.remove(position);
    }
}

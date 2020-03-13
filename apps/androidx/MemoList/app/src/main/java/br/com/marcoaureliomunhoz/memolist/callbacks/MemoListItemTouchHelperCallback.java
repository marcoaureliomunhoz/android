package br.com.marcoaureliomunhoz.memolist.callbacks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.marcoaureliomunhoz.memolist.MemoListApplication;
import br.com.marcoaureliomunhoz.memolist.adapters.MemoListRecyclerAdapter;
import br.com.marcoaureliomunhoz.memolist.models.Record;
import br.com.marcoaureliomunhoz.memolist.services.RemoveRepositoryAsyncTask;

public class MemoListItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private MemoListRecyclerAdapter adapter;

    public MemoListItemTouchHelperCallback(MemoListRecyclerAdapter adapter) {
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

        //Record source = adapter.getByPosition(positionSource);
        //Record target = adapter.getByPosition(positionTarget);

        //MemoListApplication.repository.swap(source, target);
        adapter.swap(positionSource, positionTarget);

        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        Record record = adapter.getByPosition(position);

        new RemoveRepositoryAsyncTask<Record>(
                MemoListApplication.repository,
                result -> adapter.remove(position)
        ).execute(record);
    }

}

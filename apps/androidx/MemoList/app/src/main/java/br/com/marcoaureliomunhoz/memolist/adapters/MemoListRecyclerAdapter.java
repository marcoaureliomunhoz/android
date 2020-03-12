package br.com.marcoaureliomunhoz.memolist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import br.com.marcoaureliomunhoz.memolist.R;
import br.com.marcoaureliomunhoz.memolist.listeners.OnItemMemoListRecyclerClickListener;
import br.com.marcoaureliomunhoz.memolist.models.Record;

public class MemoListRecyclerAdapter extends RecyclerView.Adapter<MemoListRecyclerAdapter.MemoListViewHolder> {

    private final List<Record> records;
    private final Context context;
    private OnItemMemoListRecyclerClickListener onItemRecyclerClickListener;

    public MemoListRecyclerAdapter(Context context, List<Record> records) {
        this.context = context;
        this.records = records;
    }

    public void swap(int initialPosition, int finalPosition) {
        Collections.swap(records, initialPosition, finalPosition);
        notifyItemMoved(initialPosition, finalPosition);
    }

    public void insert(Record record) {
        records.add(record);
        notifyItemInserted(records.size()-1);
    }

    public void update(Record record) {
        int position = records.indexOf(record);
        records.set(position, record);
        notifyItemChanged(position);
    }

    public void remove(Record record) {
        remove(records.indexOf(record));
    }

    public Record remove(int position) {
        Record record = records.get(position);
        records.remove(position);
        notifyItemRemoved(position);
        return record;
    }

    public Record getByPosition(int position) {
        return records.get(position);
    }

    public void setOnItemRecyclerClickListener(OnItemMemoListRecyclerClickListener onItemRecyclerClickListener) {
        this.onItemRecyclerClickListener = onItemRecyclerClickListener;
    }

    @NonNull
    @Override
    public MemoListRecyclerAdapter.MemoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.memo_list_item, parent, false);

        return new MemoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoListRecyclerAdapter.MemoListViewHolder holder, int position) {
        holder.setRecord(records.get(position));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void refresh(List<Record> records) {
        this.records.clear();
        this.records.addAll(records);
        this.notifyDataSetChanged();
    }

    class MemoListViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final TextView txtDescription;
        private Record record;

        MemoListViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitleListItem);
            txtDescription = itemView.findViewById(R.id.txtDescriptionListItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemRecyclerClickListener.onItemClick(record, getAdapterPosition());
                }
            });
        }

        void setRecord(Record record) {
            this.record = record;
            txtTitle.setText(record.getTitle() + " (" + record.getLastUpdateFormat() + ")");
            txtDescription.setText(record.getDescription());
        }
    }
}

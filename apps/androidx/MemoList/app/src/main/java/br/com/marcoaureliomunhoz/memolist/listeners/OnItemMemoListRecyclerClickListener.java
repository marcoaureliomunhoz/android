package br.com.marcoaureliomunhoz.memolist.listeners;

import br.com.marcoaureliomunhoz.memolist.models.Record;

public interface OnItemMemoListRecyclerClickListener {

    void onItemClick(Record record, int position);
}

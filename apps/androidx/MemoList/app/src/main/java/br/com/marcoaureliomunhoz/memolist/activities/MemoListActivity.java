package br.com.marcoaureliomunhoz.memolist.activities;
import br.com.marcoaureliomunhoz.memolist.MemoListApplication;
import br.com.marcoaureliomunhoz.memolist.R;
import br.com.marcoaureliomunhoz.memolist.adapters.MemoListRecyclerAdapter;
import br.com.marcoaureliomunhoz.memolist.callbacks.MemoListItemTouchHelperCallback;
import br.com.marcoaureliomunhoz.memolist.listeners.OnItemMemoListRecyclerClickListener;
import br.com.marcoaureliomunhoz.memolist.models.Record;
import br.com.marcoaureliomunhoz.memolist.repositories.RepositoryTemplate;
import br.com.marcoaureliomunhoz.memolist.services.CloneRepositoryAsyncTask;
import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MemoListActivity extends AppCompatActivity {

    public static final int REQUEST_INSERT = 1;
    public static final int REQUEST_UPDATE = 2;
    public static final String EXTRA_RESULT_MODEL_NAME = "result_model";

    private final RepositoryTemplate<Record> repository = MemoListApplication.repository;
    private MemoListRecyclerAdapter adapter = null;
    private int listRefreshCount = 0;
    @BindView(R.id.listRecords) RecyclerView listRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        ButterKnife.bind(this);

        setTitle(R.string.memo_list_actitivy_title);
    }

    @Override
    protected void onResume() {
        super.onResume();

        prepareMemoListRecyclerView();

        if (listRefreshCount == 0)
            refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memo_list_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_record) {
            openMemoForm(null);
        } else if (item.getItemId() == R.id.menu_item_refresh_list) {
            refreshList();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList() {
        new CloneRepositoryAsyncTask<Record>(
                repository,
                list -> adapter.refresh(list)
        ).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Record record = null;
        if (resultCode == Activity.RESULT_OK) {
            record = (Record) data.getSerializableExtra(MemoListActivity.EXTRA_RESULT_MODEL_NAME);
        }

        boolean resultOk = (resultCode == Activity.RESULT_OK) && (record != null);

        if (requestCode == MemoListActivity.REQUEST_INSERT) {
            if (resultOk) adapter.insert(record);
        } else if (requestCode == MemoListActivity.REQUEST_UPDATE) {
            if (resultOk) adapter.update(record);
        }
    }

    private void prepareMemoListRecyclerView() {
        if (adapter == null) {
            prepareLayoutForMemoListRecyclerView();
            prepareAdapterForMemoListRecyclerView();
            prepareListenersForMemoListRecyclerView();
            prepareTouchForMemoListRecyclerView();
        }
    }

    private void prepareLayoutForMemoListRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listRecords.setLayoutManager(layoutManager);
    }

    private void prepareListenersForMemoListRecyclerView() {
        adapter.setOnItemRecyclerClickListener(new OnItemMemoListRecyclerClickListener() {
            @Override
            public void onItemClick(Record record, int position) {
                openMemoForm(record);
            }
        });
    }

    private void prepareAdapterForMemoListRecyclerView() {
        adapter = new MemoListRecyclerAdapter(this, null);
        listRecords.setAdapter(adapter);
    }

    private void prepareTouchForMemoListRecyclerView() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MemoListItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(listRecords);
    }

    private void openMemoForm(Record record) {
        Intent intent = new Intent(this, MemoFormActivity.class);
        intent.putExtra(MemoFormActivity.EXTRA_MODEL_NAME, record);
        int requestCode = record == null ? MemoListActivity.REQUEST_INSERT : MemoListActivity.REQUEST_UPDATE;
        startActivityForResult(intent, requestCode);
    }

}

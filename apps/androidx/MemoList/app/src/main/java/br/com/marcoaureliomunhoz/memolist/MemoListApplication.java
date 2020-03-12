package br.com.marcoaureliomunhoz.memolist;

import android.app.Application;

import br.com.marcoaureliomunhoz.memolist.database.MemoListDatabase;
import br.com.marcoaureliomunhoz.memolist.models.Record;
import br.com.marcoaureliomunhoz.memolist.repositories.RepositoryTemplate;

public class MemoListApplication extends Application {

    public static RepositoryTemplate<Record> repository;

    @Override
    public void onCreate() {
        super.onCreate();

        //repository = new MemoryRecordsRepository();
        repository = MemoListDatabase.Factory.SqliteRecordsRepository(this);
    }
}

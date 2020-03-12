package br.com.marcoaureliomunhoz.memolist.database.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration_2To3 {

    public static Migration create() {
        return new Migration(2, 3) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("alter table record add column lastUpdate integer");
            }
        };
    }

}

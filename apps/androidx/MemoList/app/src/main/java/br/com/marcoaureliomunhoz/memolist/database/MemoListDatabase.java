package br.com.marcoaureliomunhoz.memolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.marcoaureliomunhoz.memolist.database.converters.ConverterCalendar;
import br.com.marcoaureliomunhoz.memolist.database.migrations.Migration_1To2;
import br.com.marcoaureliomunhoz.memolist.database.migrations.Migration_2To3;
import br.com.marcoaureliomunhoz.memolist.models.Record;
import br.com.marcoaureliomunhoz.memolist.repositories.SqliteRecordsRepository;

@Database(entities = {Record.class}, version = 3, exportSchema = false)
@TypeConverters({ConverterCalendar.class})
public abstract class MemoListDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "memolist.db";

    public abstract SqliteRecordsRepository getRecordsRepository();

    public static class Factory {
        public static SqliteRecordsRepository SqliteRecordsRepository(Context context) {
            return Room.databaseBuilder(context, MemoListDatabase.class, MemoListDatabase.DATABASE_NAME)
                    //.allowMainThreadQueries()
                    .addMigrations(
                        Migration_1To2.create(),
                        Migration_2To3.create()
                    )
                    .build()
                    .getRecordsRepository();
        }
    }

}

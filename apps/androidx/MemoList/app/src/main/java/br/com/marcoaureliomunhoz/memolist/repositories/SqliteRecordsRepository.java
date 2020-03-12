package br.com.marcoaureliomunhoz.memolist.repositories;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.marcoaureliomunhoz.memolist.models.Record;

@Dao
public abstract class SqliteRecordsRepository implements RepositoryTemplate<Record> {

    @Override
    public int save(Record record) {
        if (record.getId() > 0) {
            update(record);
        } else {
            long id = insert(record);
            record.setId((int)id);
        }
        return record.getId();
    }

    @Override
    public void removeById(int id) { remove(id); }

    @Override
    public void removeByPosition(int position) { removeById(position); }

    @Override
    public List<Record> clone() { return list(); }

    @Override
    public Record cloneById(int id) { return getById(id); }

    @Override
    public Record cloneByPosition(int position) {
        return cloneById(position);
    }

    @Override
    public void swap(Record source, Record target) {
    }

    @Override
    public Record getById(int id) { return get(id); }

    @Override
    public Record getByPosition(int position) { return getById(position); }

    // room methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(Record record);

    @Update
    abstract void update(Record record);

    @Query("delete from record where id = :id")
    abstract void remove(int id);

    @Query("select * from record")
    abstract List<Record> list();

    @Query("select * from record where id = :id")
    abstract Record get(int id);
}

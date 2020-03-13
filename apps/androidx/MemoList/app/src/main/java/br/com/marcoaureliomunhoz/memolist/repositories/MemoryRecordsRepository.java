package br.com.marcoaureliomunhoz.memolist.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.marcoaureliomunhoz.memolist.models.Record;

public class MemoryRecordsRepository implements RepositoryTemplate<Record> {

    public final List<Record> records = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int save(Record record) {
        int position = -1;

        if (record.isValid()) {
            if (record.getId() > 0) {
                position = records.indexOf(record);
                records.set(position, record);
            } else {
                record.setId(nextId);
                records.add(record);
                nextId++;
                position = records.size() - 1;
            }
        }

        return position;
    }

    @Override
    public void removeById(int id) {
        Record model = Record.Creator.EmptyModel();
        model.setId(id);
        removeByPosition(records.indexOf(model));
    }

    @Override
    public void removeByPosition(int position) {
        if (position >= 0) records.remove(position);
    }

    @Override
    public List<Record> clone() {
        return new ArrayList<>(records);
    }

    @Override
    public Record cloneById(int id) {
        Record model = Record.Creator.EmptyModel();
        model.setId(id);
        return cloneByPosition(records.indexOf(model));
    }

    @Override
    public Record cloneByPosition(int position) {
        return Collections.singletonList(this.records.get(position)).get(0);
    }

    @Override
    public void swap(Record source, Record target) {
        int initialPosition = records.indexOf(source);
        int finalPosition = records.indexOf(target);
        Collections.swap(records, initialPosition, finalPosition);
    }

    @Override
    public Record getById(int id) {
        Record model = Record.Creator.EmptyModel();
        model.setId(id);
        return getByPosition(records.indexOf(model));
    }

    @Override
    public Record getByPosition(int position) {
        return records.get(position);
    }

    public void seed() {
        for (int i = 1; i <= 20; i++) {
            save(new Record(
                    "Record "+i,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                            "Fusce pellentesque lobortis blandit. " +
                            "Suspendisse potenti. Vestibulum sollicitudin vulputate iaculis. " +
                            "Nullam suscipit.",
                    ""
            ));
        }
    }

}

package br.com.marcoaureliomunhoz.memolist.services;

import br.com.marcoaureliomunhoz.memolist.models.Record;

public class RecordSwapper {

    public static void swap(Record source, Record target) {
        Record targetCopy = new Record(target.getTitle(), target.getDescription());

        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        source.setTitle(targetCopy.getTitle());
        source.setDescription(targetCopy.getDescription());
    }
}

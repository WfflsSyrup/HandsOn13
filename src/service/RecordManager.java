package service;

import exception.DuplicateRecordException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Record;

public class RecordManager {
    private final List<Record> records = new ArrayList<>();

    public void add(Record record) throws DuplicateRecordException {
        if (findById(record.getId()).isPresent()) {
            throw new DuplicateRecordException("Identifier " + record.getId() + " already exists.");
        }
        records.add(record);
    }

    public Optional<Record> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        for (Record record : records) {
            if (record.getId().equalsIgnoreCase(id.trim())) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }
}
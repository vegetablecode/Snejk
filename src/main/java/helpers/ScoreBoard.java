package helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {

    private List<Record> records = new ArrayList<>();

    public void addRecord(Record record) {
        records.add(record);
    }

    public ObservableList<String> getAllRecords() {

        records.sort(Collections.reverseOrder());
        ObservableList<String> items = FXCollections.observableArrayList ();
        int i = 1;
        for (Record record: records) {
            items.add(i + ": " + record.getName() + ": " + record.getScore() + " points");
            i++;
        }
        return items;
    }

}
package br.com.marcoaureliomunhoz.memolist.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Record extends Model implements Serializable {

    private String title = "";
    private String description = "";
    private String responsible = "";
    private Calendar lastUpdate = Calendar.getInstance();

    public Record() { }

    @Ignore
    public Record(String title, String description, String responsible) {
        this.title = title;
        this.description = description;
        this.responsible = responsible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(lastUpdate.getTime());
    }

    @NonNull
    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public boolean isValid() {
        requirements.clear();
        if (getTitle().isEmpty()) {
            requirements.add("The title is necessary.");
        }
        return requirements.isEmpty();
    }

    public static class Creator {

        public static Record EmptyModel() {
            return new Record("", "", "");
        }

    }
}
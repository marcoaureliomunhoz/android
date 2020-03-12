package br.com.marcoaureliomunhoz.memolist.models;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Model implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Ignore
    protected final List<String> requirements = new ArrayList<>();
    public List<String> getRequirements() {
        return new ArrayList<>(requirements);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Record && ((Model) obj).getId() == this.getId();
    }

    @Ignore
    public abstract boolean isValid();
}

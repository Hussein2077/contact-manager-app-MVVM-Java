package com.example.contactmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contacts {
    private String name;
    private String email;
    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    private String id;

    public Contacts(String name, String number) {
        this.name = name;
        this.email = number;
    }

    public Contacts() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return email;
    }

    public void setNumber(String number) {
        this.email = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

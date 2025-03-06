package com.example.contactmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insert(Contacts contact);

    @Delete
    void delete(Contacts contact);

    @Query("SELECT * FROM contact_table")
    LiveData<List<Contacts>> getAllContacts();
}
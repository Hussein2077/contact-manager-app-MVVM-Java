package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.contactmanager.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Initialize with empty strings to prevent null pointer exceptions
            contacts = new Contacts("", "");

            binding = DataBindingUtil.setContentView(
                    this,
                    R.layout.activity_add_new_contact
            );

            // Initialize ViewModel
            myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

            // Initialize click handler
            handler = new AddNewContactClickHandler(
                    contacts,
                    this,
                    myViewModel
            );

            // Set the data binding variables
            binding.setContact(contacts);
            binding.setClickHandler(handler);

            // Force immediate binding
            binding.executePendingBindings();

        } catch (Exception e) {
            Log.e("ContactManager", "Error in AddNewContactActivity onCreate", e);
            Toast.makeText(this, "Error initializing: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
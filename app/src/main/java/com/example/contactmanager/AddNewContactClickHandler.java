package com.example.contactmanager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {

    Contacts contact;
    Context context;
    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contact,
                                     Context context,
                                     MyViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view) {
        try {
            String name = contact.getName();
            String email = contact.getEmail();

            // Validate inputs
            if (name == null || name.trim().isEmpty() ||
                    email == null || email.trim().isEmpty()) {
                Toast.makeText(context, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d("ContactManager", "Creating new contact: " + name);

            // Create new contact object
            Contacts newContact = new Contacts(name.trim(), email.trim());

            // Add to database
            myViewModel.addNewContact(newContact);

            Log.d("ContactManager", "Contact added successfully");

            // Simply finish the activity
            if (context instanceof AddNewContactActivity) {
                ((AddNewContactActivity) context).finish();
            }

        } catch (Exception e) {
            Log.e("ContactManager", "Error adding contact", e);
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
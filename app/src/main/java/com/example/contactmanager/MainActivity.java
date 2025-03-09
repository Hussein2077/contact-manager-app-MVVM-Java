package com.example.contactmanager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    private MyAdapter myAdapter;

    MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data Binding
        // Binding
        com.example.contactmanager.databinding.ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityClickHandlers handlers = new MainActivityClickHandlers(this);

        mainBinding.setClickHandler(handlers);

        // RecyclerView
        RecyclerView recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        // Database:
        // Data Source
        ContactDatabase contactDatabase = ContactDatabase.getInstance(this);

        // View Model:
        viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);


        // Loading the Data from ROOM DB
        viewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onChanged(List<Contacts> contacts) {

                        contactsArrayList.clear();

                        contactsArrayList.addAll(contacts);

                        myAdapter.notifyDataSetChanged();

                    }
                });


        // Adapter
        myAdapter = new MyAdapter(contactsArrayList);

        // Linking the RecyclerView with the Adapter
        recyclerView.setAdapter(myAdapter);

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // If you swipe the item to the left
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);


    }


}
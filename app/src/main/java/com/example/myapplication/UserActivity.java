package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.UserAdapter;
import com.example.myapplication.Class.user;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<user> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize User list and add dummy data
        userList = new ArrayList<>();
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));
        userList.add(new user("John Doe", "john.doe@example.com","123"));
        userList.add(new user("Jane Smith", "jane.smith@example.com","123"));




        // Add more users as needed

        // Initialize Adapter and set it to RecyclerView
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
}

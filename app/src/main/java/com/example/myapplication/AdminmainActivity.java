package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminmainActivity extends AppCompatActivity {
    private TextView sanPhamTextView;
    private TextView nguoidungTextView;
    private TextView hoadonTextView;
    private TextView thongkeTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmain);

        sanPhamTextView = findViewById(R.id.product);
        nguoidungTextView = findViewById(R.id.user);
        hoadonTextView = findViewById(R.id.order);
        thongkeTextView = findViewById(R.id.chart);

        sanPhamTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminmainActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        nguoidungTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminmainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        hoadonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminmainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        thongkeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminmainActivity.this, ChartActivity.class);
                startActivity(intent);
            }
        });
    }
}

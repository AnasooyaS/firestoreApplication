package com.example.firestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity4 extends AppCompatActivity {
    EditText fname;
    Button delete;
    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        fname = findViewById(R.id.editTextTextPersonName5);
        delete = findViewById(R.id.button5);
        fb = FirebaseFirestore.getInstance();
    }
}
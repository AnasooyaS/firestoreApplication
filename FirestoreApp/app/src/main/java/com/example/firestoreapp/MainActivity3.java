package com.example.firestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    EditText fname;
    EditText nname;
    Button update,delete;
    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fname = findViewById(R.id.editTextTextPersonName);
        nname = findViewById(R.id.editTextTextPersonName4);
        update = findViewById(R.id.button4);
        delete = findViewById(R.id.button6);
        fb=FirebaseFirestore.getInstance();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = fname.getText().toString();
                String newName = nname.getText().toString();
                fname.setText("");
                nname.setText("");
                UpdateData(firstName,newName);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = fname.getText().toString();
                fname.setText("");
                DeleteData(firstName);

            }
        });

    }

    private void UpdateData (String firstName,String newName){
        Map<String,Object> userdetails = new HashMap<>();
        userdetails.put("First Name", newName);
        fb.collection("user").whereEqualTo("First Name",firstName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()&&!task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot= task.getResult().getDocuments().get(0);
                    String documentId = documentSnapshot.getId();
                    fb.collection("user").document(documentId).update(userdetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity3.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity3.this, "Failure in Update", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {

                    Toast.makeText(MainActivity3.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void DeleteData(String FirstName){
        fb.collection("user").whereEqualTo("First Name",FirstName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()&&!task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot= task.getResult().getDocuments().get(0);
                    String documentId = documentSnapshot.getId();
                    fb.collection("user").document(documentId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity3.this, "Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity3.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(MainActivity3.this, "Failure", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
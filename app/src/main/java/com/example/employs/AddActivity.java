package com.example.employs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText firstName, lastName, email, phone;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        phone = findViewById(R.id.phone);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper myDB = new DataBaseHelper(AddActivity.this);
                myDB.addEmployee(firstName.getText().toString().trim(),
                        lastName.getText().toString().trim(),
                        email.getText().toString().trim(),
                        Integer.valueOf(phone.getText().toString().trim()));
            }
        });
    }
}
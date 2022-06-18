package com.example.employs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText firstNameUp, lastNameUp, emailUp, phoneUp;
    Button editButton, deleteButton;
    String id, firstName, lastName, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        firstNameUp = findViewById(R.id.firstName2);
        lastNameUp = findViewById(R.id.lastName2);
        emailUp = findViewById(R.id.emailAddress2);
        phoneUp = findViewById(R.id.phone2);
        editButton = findViewById(R.id.upDButton);
        deleteButton = findViewById(R.id.dltButton);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(firstName + " " + lastName);
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper myDB = new DataBaseHelper(UpdateActivity.this);
                firstName = firstNameUp.getText().toString().trim();
                lastName = lastNameUp.getText().toString().trim();
                email = emailUp.getText().toString().trim();
                phone = phoneUp.getText().toString().trim();
                myDB.updateData(id, firstName, lastName, email, phone);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper myDB = new DataBaseHelper(UpdateActivity.this);
                myDB.deleteEmployee(id);
                finish();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("firstName") && getIntent().hasExtra("lastName") && getIntent().hasExtra("email") && getIntent().hasExtra("phone")){
            id = getIntent().getStringExtra("id");
            firstName = getIntent().getStringExtra("firstName");
            lastName = getIntent().getStringExtra("lastName");
            email = getIntent().getStringExtra("email");
            phone = getIntent().getStringExtra("phone");

            firstNameUp.setText(firstName);
            lastNameUp.setText(lastName);
            emailUp.setText(email);
            phoneUp.setText(phone);
            Log.d("stev", firstName+" "+lastName+" "+email+" "+phone);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}
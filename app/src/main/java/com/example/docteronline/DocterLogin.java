package com.example.docteronline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DocterLogin extends AppCompatActivity {
    EditText email,pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docter_login);
        email=findViewById(R.id.emaildocter);
        pass=findViewById(R.id.pasworddocter);
        login=findViewById(R.id.logindocter);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("docter") && pass.getText().toString().trim().equals("docter")){
                    startActivity(new Intent(getApplicationContext(),DocterPage.class));
                    finish();
                }
                else{
                    Toast.makeText(DocterLogin.this, "You are not a Docter!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.docteronline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText email,pass;
    Button login,register,docterlogin;
    FirebaseAuth mAuth;
    private  final String EMAIL_PATTERN = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

    private  final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public  boolean isValidEmail(String Email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(Email).matches();
    }

    public  boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.emaillogin);
        pass=findViewById(R.id.paswordlogin);
        login=findViewById(R.id.loginlogin);
        register=findViewById(R.id.registerlogin);
        docterlogin=findViewById(R.id.docterloginbtn);

        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        docterlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DocterLogin.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String Password=pass.getText().toString();
                if(!isValidEmail(Email)){
                    Toast.makeText(MainActivity.this, "Enter Valid Email!!", Toast.LENGTH_SHORT).show();
                    email.setFocusable(true);
                    return;
                }
//                if(!isValidPassword(Password)){
//                    Toast.makeText(MainActivity.this, "Enter Valid Password!!", Toast.LENGTH_SHORT).show();
//                    pass.setFocusable(true);
//                    return;
//                }


                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(),BookDocterAp.class));
                           }
                           else {
                               Toast.makeText(MainActivity.this, "You Are is not valid User", Toast.LENGTH_SHORT).show();
                           }
                       }

                   });


            }
        });

    }
}
package com.example.docteronline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocterPage extends AppCompatActivity {
    Button appointmentsBtn,bookedAppointments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docter_page);
        appointmentsBtn=findViewById(R.id.btnAllAppointments);
        bookedAppointments=findViewById(R.id.btnBookedAppointments);
        appointmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AllAppointments.class));
            }
        });
        bookedAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BookedAppointment.class));
            }
        });
    }
}
package com.example.docteronline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookDocterAp extends AppCompatActivity {
    private EditText etPatientName, etPatientMobileNumber, etPatientEmail, etAppointmentDate, etAppointmentTime;
    private Button btnBookAppointment;
    private DatabaseReference appointmentRef;
    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Remove any whitespace or special characters from the phone number
        String formattedNumber = phoneNumber.replaceAll("[\\s\\-()]", "");

        // Validate the phone number using a regular expression
        String phonePattern = "^[+]?[0-9]{10,13}$";
        return formattedNumber.matches(phonePattern);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_docter_ap);

        etPatientName = findViewById(R.id.etPatientName);
        etPatientMobileNumber = findViewById(R.id.etPatientMobileNumber);
        etPatientEmail = findViewById(R.id.etPatientEmail);
        etAppointmentDate = findViewById(R.id.etAppointmentDate);
        etAppointmentTime = findViewById(R.id.etAppointmentTime);
        btnBookAppointment = findViewById(R.id.btnBookAppointment);
        appointmentRef = FirebaseDatabase.getInstance().getReference("appointments");

        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientEmail=etPatientEmail.getText().toString();
                String patientName=etPatientName.getText().toString();
                String patientMobileNumber=etPatientMobileNumber.getText().toString();
                String appointmentDate=etAppointmentDate.getText().toString();
                String appointmentTime=etAppointmentTime.getText().toString();
                // ...

                // Create a unique key using the patient email
                String key = patientEmail.replace(".", ",");

                // Create a PatientDetails object
                Appointment patientDetails = new Appointment(patientName, patientMobileNumber, patientEmail, appointmentDate, appointmentTime);

                // Save the patient details to Firebase Realtime Database
                appointmentRef.child(key).setValue(patientDetails)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Appointment booked successfully", Toast.LENGTH_SHORT).show();
                                etPatientName.setText("");
                                etPatientMobileNumber.setText("");
                                etPatientEmail.setText("");
                                etAppointmentDate.setText("");
                                etAppointmentTime.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed to book appointment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
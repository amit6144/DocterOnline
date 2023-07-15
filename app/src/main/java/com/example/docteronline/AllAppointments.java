package com.example.docteronline;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllAppointments extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Appointment> appointmentList;
    private AppointmentAdapter appointmentAdapter;
    private DatabaseReference appointmentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments);
        try {

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            appointmentList = new ArrayList<>();
            appointmentAdapter = new AppointmentAdapter(appointmentList);
            recyclerView.setAdapter(appointmentAdapter);

            appointmentRef = FirebaseDatabase.getInstance().getReference("appointments");
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        appointmentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              try{
                  appointmentList.clear();

                  for (DataSnapshot appointmentSnapshot : dataSnapshot.getChildren()) {
                      Appointment appointment = appointmentSnapshot.getValue(Appointment.class);
                      if (appointment != null) {
                          appointmentList.add(appointment);
                      }
                  }

                  appointmentAdapter.notifyDataSetChanged();
              }
              catch (Exception e){
                  Toast.makeText(AllAppointments.this, e.getMessage(), Toast.LENGTH_SHORT).show();
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
        appointmentAdapter.setOnItemClickListener(new AppointmentAdapter.OnItemClickListener() {
            @Override
            public void onBookAppointmentClick(int position) {
                final Appointment selectedAppointment = appointmentList.get(position);

                // Create a unique key using the patient email
                String key = selectedAppointment.getPatientEmail().replace(".", ",");

                // Save the selected appointment to "booked_appointments" node
                DatabaseReference bookedAppointmentsRef = FirebaseDatabase.getInstance().getReference("booked_appointments");
                bookedAppointmentsRef.child(key).setValue(selectedAppointment)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Appointment booked successfully", Toast.LENGTH_SHORT).show();
                                String recipientEmail = selectedAppointment.getPatientEmail();
                                String subject = "Confirm Your Appointment";
                                String body = "Your Appointment has been booked with your docter";



                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                intent.setData(Uri.parse("mailto:" + recipientEmail));
                                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                                intent.putExtra(Intent.EXTRA_TEXT, body);

                                try {
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(getApplicationContext(), "No email clients found.", Toast.LENGTH_SHORT).show();
                                }
                                // Remove the selected appointment from "appointments" node
                                appointmentRef.child(key).removeValue();

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

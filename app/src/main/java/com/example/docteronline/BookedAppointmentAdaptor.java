package com.example.docteronline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookedAppointmentAdaptor extends RecyclerView.Adapter<BookedAppointmentAdaptor.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public BookedAppointmentAdaptor(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookedcard, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        holder.tvPatientName.setText(appointment.getPatientName());
        holder.tvAppointmentDateTime.setText(appointment.getAppointmentDateTime());
        holder.tvPatientMobileNumber.setText(appointment.getPatientMobileNumber());
        holder.tvPatientEmail.setText(appointment.getPatientEmail());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvPatientName;
        TextView tvAppointmentDateTime;
        TextView tvPatientMobileNumber;
        TextView tvPatientEmail;

        AppointmentViewHolder(View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            tvAppointmentDateTime = itemView.findViewById(R.id.tvAppointmentDateTime);
            tvPatientMobileNumber = itemView.findViewById(R.id.tvPatientMobileNumber);
            tvPatientEmail = itemView.findViewById(R.id.tvPatientEmail);
        }
    }
}

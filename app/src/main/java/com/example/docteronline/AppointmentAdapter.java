package com.example.docteronline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private OnItemClickListener onItemClickListener;

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public interface OnItemClickListener {
        void onBookAppointmentClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitycard, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        final Appointment appointment = appointmentList.get(position);

        holder.tvPatientName.setText(appointment.getPatientName());
        holder.tvAppointmentDateTime.setText(appointment.getAppointmentDateTime());
        holder.tvPatientMobileNumber.setText(appointment.getPatientMobileNumber());
        holder.tvPatientEmail.setText(appointment.getPatientEmail());

        holder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onBookAppointmentClick(position);
                    }
                }
            }
        });
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
        Button btnBook;

        AppointmentViewHolder(View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            tvAppointmentDateTime = itemView.findViewById(R.id.tvAppointmentDateTime);
            tvPatientMobileNumber = itemView.findViewById(R.id.tvPatientMobileNumber);
            tvPatientEmail = itemView.findViewById(R.id.tvPatientEmail);
            btnBook = itemView.findViewById(R.id.btnBook);
        }
    }
}

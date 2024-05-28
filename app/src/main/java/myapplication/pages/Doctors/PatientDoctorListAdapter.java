package myapplication.pages.Doctors;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import database.DoctorsDb;
import myapplication.pages.R;
import java.util.List;

public class PatientDoctorListAdapter extends RecyclerView.Adapter<PatientDoctorListAdapter.PatientDoctorViewHolder> {

    private List<DoctorsDb.Doctor> doctors;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DoctorsDb.Doctor doctor);
    }

    public PatientDoctorListAdapter(List<DoctorsDb.Doctor> doctors, OnItemClickListener listener) {
        this.doctors = doctors;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatientDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new PatientDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientDoctorViewHolder holder, int position) {
        DoctorsDb.Doctor doctor = doctors.get(position);
        //holder.nameTextView.setText(doctor.getName());
        //holder.specializationTextView.setText(doctor.getSpecialization());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(doctor));
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDoctors(List<DoctorsDb.Doctor> doctors) {
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    static class PatientDoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView specializationTextView;

        public PatientDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            specializationTextView = itemView.findViewById(R.id.specializationTextView);
        }
    }
}

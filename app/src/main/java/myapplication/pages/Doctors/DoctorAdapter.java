package myapplication.pages.Doctors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import database.DoctorsDb.Doctor;
import myapplication.pages.R;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<Doctor> doctors;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Doctor doctor);
    }

    public DoctorAdapter(List<Doctor> doctors, OnItemClickListener listener) {
        this.doctors = doctors;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.nameTextView.setText(doctor.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(doctor));
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}

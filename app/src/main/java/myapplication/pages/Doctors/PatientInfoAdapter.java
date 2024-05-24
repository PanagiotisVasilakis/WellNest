package myapplication.pages.Doctors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import database.PatientInfoDb.Patient;
import myapplication.pages.R;
import java.util.List;

public class PatientInfoAdapter extends RecyclerView.Adapter<PatientInfoAdapter.PatientInfoViewHolder> {

    private List<Patient> patientInfos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Patient patientInfo);
    }

    public PatientInfoAdapter(List<Patient> patientInfos, OnItemClickListener listener) {
        this.patientInfos = patientInfos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatientInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient_info, parent, false);
        return new PatientInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientInfoViewHolder holder, int position) {
        Patient patientInfo = patientInfos.get(position);
        holder.nameTextView.setText(patientInfo.getName());
        holder.ageTextView.setText(String.valueOf(patientInfo.getAge()));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(patientInfo));
    }

    @Override
    public int getItemCount() {
        return patientInfos.size();
    }

    public void setPatientInfos(List<Patient> patientInfos) {
        this.patientInfos = patientInfos;
        notifyDataSetChanged();
    }

    static class PatientInfoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView ageTextView;

        public PatientInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
        }
    }
}

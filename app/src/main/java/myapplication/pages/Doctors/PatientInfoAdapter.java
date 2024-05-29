package myapplication.pages.Doctors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import database.PatientInfoDb.PatientInfo;
import myapplication.pages.R;

public class PatientInfoAdapter extends RecyclerView.Adapter<PatientInfoAdapter.PatientInfoViewHolder> {

    private List<PatientInfo> patientInfos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PatientInfo patientInfo);
    }

    public PatientInfoAdapter(List<PatientInfo> patientInfos, OnItemClickListener listener) {
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
        PatientInfo patientInfo = patientInfos.get(position);
        holder.nameTextView.setText(patientInfo.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(patientInfo));
    }

    @Override
    public int getItemCount() {
        return patientInfos.size();
    }

    public void setPatientInfos(List<PatientInfo> patientInfos) {
        this.patientInfos = patientInfos;
        notifyDataSetChanged();
    }

    static class PatientInfoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public PatientInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}

package myapplication.pages.CheckUps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import myapplication.pages.R;

public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationAdapter.ExaminationViewHolder> {
    private final List<Examination> examinationList;

    public ExaminationAdapter(List<Examination> examinationList) {
        this.examinationList = examinationList;
    }

    @NonNull
    @Override
    public ExaminationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_examination, parent, false);
        return new ExaminationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExaminationViewHolder holder, int position) {
        Examination examination = examinationList.get(position);
        holder.textView.setText(examination.getName());

        // For demo purposes, we show a checkmark for all items
        holder.checkmark.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return examinationList.size();
    }

    static class ExaminationViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView checkmark;

        public ExaminationViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            checkmark = itemView.findViewById(R.id.checkmark);
        }
    }
}

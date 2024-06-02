package myapplication.pages.CheckUps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import myapplication.pages.R;

public class CheckupAdapter extends RecyclerView.Adapter<CheckupAdapter.CheckupViewHolder> {
    private final List<Checkup> checkupList;

    public CheckupAdapter(List<Checkup> checkupList) {
        this.checkupList = checkupList;
    }

    @NonNull
    @Override
    public CheckupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkup, parent, false);
        return new CheckupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckupViewHolder holder, int position) {
        Checkup checkup = checkupList.get(position);
        holder.textView.setText(checkup.getName());
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("checkupId", checkup.getId());
            Navigation.findNavController(v).navigate(R.id.action_checkupFragment_to_examinationFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return checkupList.size();
    }

    static class CheckupViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public CheckupViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}

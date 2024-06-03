package myapplication.pages.DailyState;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import myapplication.pages.R;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> {

    private String[] suggestions = new String[]{
            "Dietary suggestion - Eat more fruits and vegetables.",
            "Exercise suggestion - Do 30 minutes of cardio.",
            "Health suggestion - Drink at least 8 cups of water."
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.suggestionText.setText(suggestions[position]);
    }

    @Override
    public int getItemCount() {
        return suggestions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView suggestionText;

        public ViewHolder(View itemView) {
            super(itemView);
            suggestionText = itemView.findViewById(R.id.suggestion_text);
        }
    }
}

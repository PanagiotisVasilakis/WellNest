package myapplication.pages.CheckUps;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import database.CheckUpDb;
import myapplication.pages.R;

public class CheckupFragment extends Fragment {
    private RecyclerView recyclerView;
    private CheckupAdapter checkupAdapter;
    private CheckUpDb databaseHelper;

    public CheckupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkup, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new CheckUpDb(getContext());

        // Fetch checkups from the database in a background thread
        new Thread(() -> {
            List<Checkup> checkupList = databaseHelper.getCheckups();
            Log.d("CheckupFragment", "Fetched Checkup List: " + checkupList.size());
            new Handler(Looper.getMainLooper()).post(() -> {
                checkupAdapter = new CheckupAdapter(checkupList);
                recyclerView.setAdapter(checkupAdapter);
            });
        }).start();

        return view;
    }
}

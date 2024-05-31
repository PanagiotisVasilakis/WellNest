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

public class ExaminationFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExaminationAdapter examinationAdapter;
    private CheckUpDb databaseHelper;
    private int checkupId;

    public ExaminationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examination, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new CheckUpDb(getContext());

        // Get checkupId from arguments
        if (getArguments() != null) {
            checkupId = getArguments().getInt("checkupId");
        }

        // Fetch examinations from the database in a background thread
        new Thread(() -> {
            List<Examination> examinationList = databaseHelper.getExaminations(checkupId);
            Log.d("ExaminationFragment", "Fetched Examination List: " + examinationList.size());
            new Handler(Looper.getMainLooper()).post(() -> {
                examinationAdapter = new ExaminationAdapter(examinationList);
                recyclerView.setAdapter(examinationAdapter);
            });
        }).start();

        return view;
    }
}

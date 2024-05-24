package myapplication.pages.Doctors;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import database.PatientInfoDb;
import myapplication.pages.R;
import java.util.List;

public class DoctorListFragment extends Fragment {

    private PatientInfoDb dbHelper;
    private PatientInfoAdapter patientInfoAdapter;

    public DoctorListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);

        dbHelper = new PatientInfoDb(getActivity());
        List<PatientInfoDb.PatientInfo> patientInfos = dbHelper.getAllPatientInfo();
        patientInfoAdapter = new PatientInfoAdapter(patientInfos, patientInfo -> {
            // Implement click listener if needed
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(patientInfoAdapter);

        return view;
    }
}

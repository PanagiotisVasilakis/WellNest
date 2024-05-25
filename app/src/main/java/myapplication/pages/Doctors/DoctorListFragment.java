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
import java.util.ArrayList;
import java.util.List;
import database.DoctorsDb;
import database.DoctorsDb.Doctor;
import myapplication.pages.R;

public class DoctorListFragment extends Fragment {

    private DoctorsDb dbHelper;
    private DoctorAdapter doctorAdapter;
    private long currentPatientId;

    public DoctorListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);

        dbHelper = new DoctorsDb(getActivity());
        doctorAdapter = new DoctorAdapter(new ArrayList<>(), this::showDoctorProfile);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doctorAdapter);

        if (getArguments() != null) {
            currentPatientId = getArguments().getLong("currentPatientId", -1);
        }

        loadDoctorsForPatient();

        return view;
    }

    private void loadDoctorsForPatient() {
        if (currentPatientId != -1) {
            List<Doctor> doctors = dbHelper.getDoctorsForPatient(currentPatientId);
            doctorAdapter.setDoctors(doctors);
        }
    }

    private void showDoctorProfile(Doctor doctor) {
        // Implement showing doctor profile logic here
    }
}

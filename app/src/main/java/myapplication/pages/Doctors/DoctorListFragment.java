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
import database.PatientInfoDb.Doctor;
import myapplication.pages.R;
import java.util.List;

public class DoctorListFragment extends Fragment {

    private PatientInfoDb dbHelper;
    private PatientDoctorListAdapter doctorAdapter;
    private long currentPatientId = 1;  // Assume a patient ID for demonstration purposes

    public DoctorListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);

        dbHelper = new PatientInfoDb(getActivity());
        List<Doctor> doctors = dbHelper.getDoctorsForPatient(currentPatientId);
        doctorAdapter = new PatientDoctorListAdapter(doctors, doctor -> {
            // Handle doctor item click if needed
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doctorAdapter);

        return view;
    }
}

package myapplication.pages.Doctors;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
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
        doctorAdapter = new DoctorAdapter(new ArrayList<>(), this::showAddDoctor);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doctorAdapter);

        if (getArguments() != null) {
            currentPatientId = getArguments().getLong("currentPatientId", -1);
        }

        getPatientDoctors();

        return view;
    }

    private void getPatientDoctors() {
        List<Doctor> doctors = dbHelper.getAllDoctorsFromPatientDoctors();
        doctorAdapter.setDoctors(doctors);
    }

    private void showAddDoctor(Doctor doctor) {
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.doctor_list, null);

        TextView tvDoctorName = popupView.findViewById(R.id.tvDoctorName);
        TextView tvDoctorLocation = popupView.findViewById(R.id.tvDoctorLocation);
        TextView tvDoctorAvailability = popupView.findViewById(R.id.tvDoctorAvailability);
        TextView tvDoctorContact = popupView.findViewById(R.id.tvDoctorContact);
        TextView tvDoctorEmail = popupView.findViewById(R.id.tvDoctorEmail);
        TextView tvDoctorSpecialization = popupView.findViewById(R.id.tvDoctorSpecialization);

        tvDoctorName.setText(doctor.getName());
        tvDoctorLocation.setText(doctor.getLocation());
        tvDoctorAvailability.setText(doctor.getAvailability());
        tvDoctorContact.setText(doctor.getContact());
        tvDoctorEmail.setText(doctor.getEmail());
        tvDoctorSpecialization.setText(doctor.getSpecialization());
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
    }
}

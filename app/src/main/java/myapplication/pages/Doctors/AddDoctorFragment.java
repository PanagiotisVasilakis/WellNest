package myapplication.pages.Doctors;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import database.DoctorsDb;
import database.DoctorsDb.Doctor;
import database.PatientInfoDb;
import myapplication.pages.R;
import java.util.ArrayList;
import java.util.List;

public class AddDoctorFragment extends Fragment {

    DoctorProfile doctorProfile = new DoctorProfile();
    private static DoctorsDb dbHelper;
    private PatientInfoDb patientInfoDb;
    private DoctorAdapter doctorAdapter;
    private List<DoctorsDb.Doctor> doctorList;

    public AddDoctorFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_doctor, container, false);

        dbHelper = new DoctorsDb(getActivity());
        doctorList = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(doctorList, this::showDoctorProfile);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doctorAdapter);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getDoctors(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getDoctors(newText);
                return false;
            }
        });

        renewPageLoadAllDoctors();

        return view;
    }

    public void getDoctors(String query) {
        List<DoctorsDb.Doctor> doctors = dbHelper.getAllDoctors();
        List<DoctorsDb.Doctor> filteredDoctors = new ArrayList<>();

        for (DoctorsDb.Doctor doctor : doctors) {
            if (doctor.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredDoctors.add(doctor);
            }
        }

        doctorList.clear();
        doctorList.addAll(filteredDoctors);
        doctorAdapter.setDoctors(filteredDoctors);
    }

    private void renewPageLoadAllDoctors() {
        List<DoctorsDb.Doctor> doctors = dbHelper.getAllDoctors();
        doctorList.clear();
        doctorList.addAll(doctors);
        Log.d("AddDoctorFragment", "Number of doctors loaded: " + doctors.size());
        for (DoctorsDb.Doctor doctor : doctors) {
            Log.d("AddDoctorFragment", "Doctor: " + doctor.getName());
        }
        doctorAdapter.setDoctors(doctors);
    }

    public void showDoctorProfile(Doctor doctor) {
        View popupView = (View) doctorProfile.getDoctorInfo(doctor);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
        doctorProfile.addDoctor(popupView, popupWindow, doctor);
    }

    // Doctor Profile class
    public class DoctorProfile {

        public Object getDoctorInfo(Doctor doctor) {
            View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_doctor_details, null);

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
            return popupView;
        }

        public void addDoctor(View popupView, PopupWindow popupWindow, Doctor doctor) {
            Button btnAddDoctor = popupView.findViewById(R.id.btnAddDoctor);
            btnAddDoctor.setOnClickListener(v -> {
                // Assuming user info is hard-coded for demonstration purposes
                String patientName = "John Doe";
                int patientAge = 30;
                patientInfoDb.addPatientInfo(patientName, patientAge, doctor.getName(), doctor.getSpecialization());
                popupWindow.dismiss();
                Toast.makeText(getActivity(), "Doctor added to patient info", Toast.LENGTH_SHORT).show();
            });
        }
    }

    public static void insertSampleData(SQLiteDatabase db) {
        Doctor doctor1 = new Doctor();
        doctor1.setName("Dr. Alice Smith");
        doctor1.setLocation("123 Elm St");
        doctor1.setAvailability("9 AM - 5 PM");
        doctor1.setContact("123-456-7890");
        doctor1.setEmail("alice.smith@example.com");
        doctor1.setSpecialization("General");
        DoctorsDb.doctorAddition(db, doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Dr. Bob Johnson");
        doctor2.setLocation("456 Oak St");
        doctor2.setAvailability("10 AM - 6 PM");
        doctor2.setContact("987-654-3210");
        doctor2.setEmail("bob.johnson@example.com");
        doctor2.setSpecialization("Pediatric");
        DoctorsDb.doctorAddition(db, doctor2);
    }
}

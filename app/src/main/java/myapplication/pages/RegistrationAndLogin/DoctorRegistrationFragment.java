package myapplication.pages.RegistrationAndLogin;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import myapplication.pages.R;
import database.DoctorsDb;

public class DoctorRegistrationFragment extends Fragment {

    private EditText etName, etEmail, etSpecialization, etPhone;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_registration, container, false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etSpecialization = view.findViewById(R.id.etSpecialization);
        etPhone = view.findViewById(R.id.etPhone);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String specialization = etSpecialization.getText().toString();
            String phone = etPhone.getText().toString();

            DoctorsDb.Doctor doctor = new DoctorsDb.Doctor();
            doctor.setName(name);
            doctor.setEmail(email);
            doctor.setSpecialization(specialization);
            doctor.setContact(phone);

            DoctorsDb doctorsDb = new DoctorsDb(getActivity());
            SQLiteDatabase db = doctorsDb.getWritableDatabase();
            doctorsDb.doctorAddition(db, doctor);
            db.close();

            Toast.makeText(getActivity(), "Registered as Doctor", Toast.LENGTH_SHORT).show();
            navController = Navigation.findNavController(v);

            navController.navigate(R.id.nav_daily_state);
        });

        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        navController = Navigation.findNavController(view);
//    }
}

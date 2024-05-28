package myapplication.pages.RegistrationAndLogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import myapplication.pages.MainActivity;
import myapplication.pages.R;
import database.DoctorsDb;

public class DoctorRegistrationFragment extends Fragment {

    private EditText etName, etEmail, etSpecialization, etPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_registration, container, false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etSpecialization = view.findViewById(R.id.etSpecialization);
        etPhone = view.findViewById(R.id.etPhone);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            if (isAdded() && getView() != null) {
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

                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }
}

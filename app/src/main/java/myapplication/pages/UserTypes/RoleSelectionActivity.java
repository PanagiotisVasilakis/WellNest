package myapplication.pages.UserTypes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import database.DoctorsDb;
import database.PatientInfoDb;
import myapplication.pages.MainActivity;
import myapplication.pages.R;

public class RoleSelectionActivity extends AppCompatActivity {

    private DoctorsDb doctorsDb;
    private PatientInfoDb patientInfoDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button btnPatient = findViewById(R.id.btnPatient);
        Button btnDoctor = findViewById(R.id.btnDoctor);
        doctorsDb = new DoctorsDb(this);
        patientInfoDb = new PatientInfoDb(this);

        btnPatient.setOnClickListener(v -> showConfirmationDialog("Patient"));
        btnDoctor.setOnClickListener(v -> showConfirmationDialog("Doctor"));
    }

    private void showConfirmationDialog(String role) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Selection")
                .setMessage("Are you sure you want to register as a " + role + "?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> registerUser(role))
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void registerUser(String role) {
        // Assuming you have a method to get the logged-in user's details
        String email = "user@example.com"; // Get this from the login information
        String name = "John Doe"; // Get this from the registration information
        String age = "30"; // Get this from the registration information

        if (role.equals("Doctor")) {
            DoctorsDb.Doctor doctor = new DoctorsDb.Doctor();
            doctor.setName(name);
            doctor.setEmail(email);
            // Add other necessary details
            doctorsDb.addDoctor(doctor);
        } else if (role.equals("Patient")) {
            PatientInfoDb.PatientInfo patient = new PatientInfoDb.PatientInfo();
            patient.setName(name);
            patient.setAge(age);
            patient.setEmail(email);
            // Add other necessary details
            patientInfoDb.addPatient(patient);
        }

        // Navigate to MainActivity
        Intent intent = new Intent(RoleSelectionActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

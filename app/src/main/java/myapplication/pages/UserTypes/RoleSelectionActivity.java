package myapplication.pages.UserTypes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import database.DoctorsDb;
import database.PatientInfoDb;
import database.UserDb;
import myapplication.pages.MainActivity;
import myapplication.pages.R;
import myapplication.pages.RegistrationAndLogin.DoctorRegistrationFragment;

public class RoleSelectionActivity extends AppCompatActivity {

    private DoctorsDb doctorsDb;
    private PatientInfoDb patientInfoDb;
    private UserDb userDb;
    private String email, name;
    private int age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button btnPatient = findViewById(R.id.btnPatient);
        Button btnDoctor = findViewById(R.id.btnDoctor);
        doctorsDb = new DoctorsDb(this);
        patientInfoDb = new PatientInfoDb(this);
        userDb = new UserDb(this);

        // Assuming you pass user info from login or registration
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age", 0);

        btnPatient.setOnClickListener(v -> showConfirmationDialog("Patient"));
        btnDoctor.setOnClickListener(v -> showConfirmationDialog("Doctor"));
    }

    private void showConfirmationDialog(String role) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.confirm)
                .setMessage(getString(R.string.confirm_selection, role))
                .setPositiveButton(R.string.yes, (dialog, which) -> registerUser(role))
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void registerUser(String role) {
        // Register in UserDb
        UserDb.User user = new UserDb.User();
        user.setEmail(email);
        user.setName(name);
        user.setAge(age);
        // Add a placeholder password and phone
        user.setPassword("password123");
        user.setPhone("123-456-7890");
        userDb.addUser(user);

        if (role.equals("Doctor")) {
            // Add user to DoctorsDb
            DoctorsDb.Doctor doctor = new DoctorsDb.Doctor();
            doctor.setEmail(email);
            doctor.setName(name);
            doctor.setContact("123-456-7890"); // Placeholder contact
            SQLiteDatabase db = doctorsDb.getWritableDatabase();
            doctorsDb.doctorAddition(db, doctor);
            db.close();

            // Navigate to DoctorRegistrationFragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new DoctorRegistrationFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (role.equals("Patient")) {
            PatientInfoDb.PatientInfo patient = new PatientInfoDb.PatientInfo();
            patient.setName(name);
            patient.setAge(age);
            patient.setEmail(email);
            patientInfoDb.addPatient(patient);
            Toast.makeText(this, "Registered as Patient", Toast.LENGTH_SHORT).show();

            // Navigate to MainActivity
            Intent intent = new Intent(RoleSelectionActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

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
    private String email;
    private String name;
    private int age;
    private String contact;
    private int id;

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
        contact = intent.getStringExtra("phone");
        id = intent.getIntExtra("id", 0);

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
        if (role.equals("Doctor")) {
            // Check if the doctor already exists
            if (!doctorsDb.isDoctorExists(id)) {
                // Add user to DoctorsDb
                DoctorsDb.Doctor doctor = new DoctorsDb.Doctor();
                doctor.setId(id);
                SQLiteDatabase db = doctorsDb.getWritableDatabase();
                doctorsDb.doctorAddition(db, doctor);
                db.close();
                // Navigate to DoctorRegistrationFragment
                DoctorRegistrationFragment doctorRegistrationFragment = new DoctorRegistrationFragment();
                Bundle args = new Bundle();
                args.putInt("doctor_id", id); // 'id' is the doctor's ID
                doctorRegistrationFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, doctorRegistrationFragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                // Navigate to MainActivity
                Intent intent = new Intent(RoleSelectionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
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

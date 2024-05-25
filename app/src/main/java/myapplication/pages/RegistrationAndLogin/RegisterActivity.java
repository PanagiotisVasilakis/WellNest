package myapplication.pages.RegistrationAndLogin;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import database.UserDb;
import myapplication.pages.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etName, etAge, etPassword, etPhone;
    private UserDb userDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        Button btnRegister = findViewById(R.id.btnRegister);

        userDb = new UserDb(this);

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(ageStr) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
                Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);

            if (userDb.isUserExists(email)) {
                Toast.makeText(RegisterActivity.this, "You are already registered with this email", Toast.LENGTH_SHORT).show();
                finish(); // Go back to login page
                return;
            }

            long result = userDb.registerUser(email, name, age, password, phone);
            if (result == -1) {
                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                finish(); // Go back to login page
            }
        });
    }
}

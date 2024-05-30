package myapplication.pages.RegistrationAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import database.UserDb;
import myapplication.pages.R;
import myapplication.pages.UserTypes.RoleSelectionActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etName, etAge, etPassword, etPhone;
    private UserDb userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        userDb = new UserDb(this);

        btnSignUp.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String name = etName.getText().toString().trim();
            int age = Integer.parseInt(etAge.getText().toString().trim());
            String password = etPassword.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (userDb.isUserExists(email)) {
                Toast.makeText(RegisterActivity.this, "You are already registered with this email", Toast.LENGTH_SHORT).show();
            } else {
                UserDb.User user = new UserDb.User();
                user.setEmail(email);
                user.setName(name);
                user.setAge(age);
                user.setPassword(password);
                user.setPhone(phone);
                userDb.addUser(user);

                Intent intent = new Intent(RegisterActivity.this, RoleSelectionActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("phone", phone);
                intent.putExtra("id", user.getId());
                startActivity(intent);
                finish();

//                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }
}

package myapplication.pages.UserTypes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import myapplication.pages.MainActivity;
import myapplication.pages.R;

public class RoleSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button btnPatient = findViewById(R.id.btnPatient);
        Button btnDoctor = findViewById(R.id.btnDoctor);

        btnPatient.setOnClickListener(v -> navigateToMainActivity());
        btnDoctor.setOnClickListener(v -> navigateToMainActivity());
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(RoleSelectionActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

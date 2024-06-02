package myapplication.pages.DailyState;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import database.DailyStateDb;
import myapplication.pages.R;

public class HealthProgramFragment extends Fragment {

    private final HealthProgramClass healthProgramClass;
    private DailyStateDb dbHelper;

    public HealthProgramFragment() {
        // Required empty public constructor
        healthProgramClass = new HealthProgramClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new DailyStateDb(getContext());
        return inflater.inflate(R.layout.fragment_health_program, container, false);
    }

    public class DailyState {

        public void getHealthProgram() {
            healthProgramClass.getHealthProgram("Food Pyramid");
        }

        public void showHealthProgram() {
            healthProgramClass.showProgram("Food Pyramid");
        }

        public void showProgramDetected() {
            // Display a message indicating that a program has been detected
            Toast.makeText(getContext(), "Program Detected!", Toast.LENGTH_SHORT).show();
        }
    }

    public class HealthProgramClass {

        public void calculateBMI(double weight, double height) {
            double bmi = weight / (height * height);
            saveBMI(bmi);
        }

        public void saveBMI(double bmi) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DailyStateDb.COLUMN_BMI, bmi);
            db.insert(DailyStateDb.TABLE_HEALTH_PROGRAM, null, values);
        }

        public void getHealthProgram(String programName) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DailyStateDb.TABLE_HEALTH_PROGRAM,
                    new String[]{DailyStateDb.COLUMN_NAME, DailyStateDb.COLUMN_DESCRIPTION},
                    DailyStateDb.COLUMN_NAME + " = ?",
                    new String[]{programName},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DailyStateDb.COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DailyStateDb.COLUMN_DESCRIPTION));
                // Use this data as needed
                cursor.close();
            }
        }

        public void showProgram(String programName) {
            getHealthProgram(programName);
            // Display the program details to the user
        }

        public void showSubcategory() {
            // Implementation here
        }
    }
}

package myapplication.pages.DailyState;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import database.DailyStateDb;
import myapplication.pages.R;

public class HealthProgramFragment extends Fragment {

    private HealthProgramClass healthProgramClass;
    private DailyStateDb dbHelper;
    private CircularProgressBar progressDaily, progressDiet, progressExercise;

    public HealthProgramFragment() {
        // Required empty public constructor
        healthProgramClass = new HealthProgramClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new DailyStateDb(getContext());
        View view = inflater.inflate(R.layout.fragment_health_program, container, false);

        progressDaily = view.findViewById(R.id.progress_daily);
        progressDiet = view.findViewById(R.id.progress_diet);
        progressExercise = view.findViewById(R.id.progress_exercise);

        // Example input percentages
        int dietPercentage = 64;
        int exercisePercentage = 90;

        updateProgress("Diet", dietPercentage);
        updateProgress("Exercise", exercisePercentage);

        int dailyProgress = (dietPercentage + exercisePercentage) / 2;
        progressDaily.setProgressWithAnimation((float) dailyProgress, 1000L); // Animation for better UX

        setupSuggestionsList(view);

        return view;
    }

    private void updateProgress(String category, int percentage) {
        EditCategoryProgressFragment editFragment = new EditCategoryProgressFragment(dbHelper);
        EditCategoryProgressFragment.Subcategory subcategory = editFragment.new Subcategory(getContext());
        subcategory.saveProgress(category, percentage);
    }

    private void setupSuggestionsList(View view) {
        RecyclerView suggestionsList = view.findViewById(R.id.suggestions_list);
        suggestionsList.setLayoutManager(new LinearLayoutManager(getContext()));
        suggestionsList.setAdapter(new SuggestionsAdapter());
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
        }
    }
}

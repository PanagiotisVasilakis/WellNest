package myapplication.pages.DailyState;

import android.content.ContentValues;
import android.content.Context;
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

public class EditCategoryProgressFragment extends Fragment {

    private HealthProgramFragment.HealthProgramClass healthProgramC;
    private DailyStateDb dbHelper;

    public EditCategoryProgressFragment() {
        HealthProgramFragment healthProgram = new HealthProgramFragment();
        healthProgramC = healthProgram.new HealthProgramClass();
    }

    public EditCategoryProgressFragment(DailyStateDb dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (dbHelper == null) {
            dbHelper = new DailyStateDb(getContext());
        }
        return inflater.inflate(R.layout.fragment_edit_category_progress, container, false);
    }

    public class Subcategory {

        private Context context;

        public Subcategory(Context context) {
            this.context = context;
        }

        public void getSubcategory(String subcategoryName) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DailyStateDb.TABLE_HEALTH_PROGRAM,
                    new String[]{DailyStateDb.COLUMN_NAME, DailyStateDb.COLUMN_DESCRIPTION},
                    DailyStateDb.COLUMN_NAME + " = ?",
                    new String[]{subcategoryName},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DailyStateDb.COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DailyStateDb.COLUMN_DESCRIPTION));
                // Use this data as needed
                cursor.close();
            }
        }

        public void saveProgress(String subcategoryName, int progress) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DailyStateDb.COLUMN_NAME, subcategoryName);
            values.put("progress", progress); // Assuming there is a progress column

            long rowId = db.insertWithOnConflict(DailyStateDb.TABLE_HEALTH_PROGRAM, null, values, SQLiteDatabase.CONFLICT_REPLACE);

            if (rowId != -1) {
                showProgressUpdated();
            }
        }

        public void checkProgress(String subcategoryName) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DailyStateDb.TABLE_HEALTH_PROGRAM,
                    new String[]{"progress"}, // Assuming there is a progress column
                    DailyStateDb.COLUMN_NAME + " = ?",
                    new String[]{subcategoryName},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int progress = cursor.getInt(cursor.getColumnIndexOrThrow("progress"));
                // Use this data as needed
                cursor.close();

                if (progress >= 100) { // Assuming 100 is the goal
                    showGoalReached();
                }
            }
        }

        public void showProgressUpdated() {
            // Display a message indicating that progress has been updated
            Toast.makeText(context, "Progress Updated!", Toast.LENGTH_SHORT).show();
        }

        public void showGoalReached() {
            // Display a message indicating that the goal has been reached
            Toast.makeText(context, "Goal Reached!", Toast.LENGTH_SHORT).show();
        }
    }

    public class GoalReached {
        // Operations for Edit Category Progress
    }
}

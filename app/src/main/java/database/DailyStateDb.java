package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DailyStateDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DailyState.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_HEALTH_PROGRAM = "health_program";
    public static final String TABLE_WORKOUT_PROGRAM = "workout_program";

    // Columns for health program
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_BMI = "bmi";

    // Columns for workout program
    // Define as needed

    // Create health program table SQL statement
    private static final String TABLE_HEALTH_PROGRAM_CREATE =
            "CREATE TABLE " + TABLE_HEALTH_PROGRAM + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_BMI + " REAL);";

    public DailyStateDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_HEALTH_PROGRAM_CREATE);
        // Insert example health programs
        db.execSQL("INSERT INTO " + TABLE_HEALTH_PROGRAM + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") VALUES ('Food Pyramid', 'A balanced diet with portions of each food group');");
        db.execSQL("INSERT INTO " + TABLE_HEALTH_PROGRAM + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") VALUES ('Keto', 'A high-fat, low-carbohydrate diet');");
        db.execSQL("INSERT INTO " + TABLE_HEALTH_PROGRAM + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") VALUES ('Vegan', 'A plant-based diet excluding animal products');");
        // Insert other initial data
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTH_PROGRAM);
        onCreate(db);
    }
}


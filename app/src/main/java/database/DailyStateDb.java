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
    public static final String COLUMN_PROGRESS = "progress"; // Adding progress column

    // Create health program table SQL statement
    private static final String TABLE_HEALTH_PROGRAM_CREATE =
            "CREATE TABLE " + TABLE_HEALTH_PROGRAM + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_BMI + " REAL, " +
                    COLUMN_PROGRESS + " INTEGER DEFAULT 0);";

    // Create workout program table SQL statement
    private static final String TABLE_WORKOUT_PROGRAM_CREATE =
            "CREATE TABLE " + TABLE_WORKOUT_PROGRAM + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_PROGRESS + " INTEGER DEFAULT 0);";

    public DailyStateDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_HEALTH_PROGRAM_CREATE);
        db.execSQL(TABLE_WORKOUT_PROGRAM_CREATE);

        // Insert example health programs
        db.execSQL("INSERT INTO " + TABLE_HEALTH_PROGRAM + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") VALUES ('Food Pyramid', 'Eat Most: Grains (6-11 servings/day). Eat More: Vegetables (3-5 servings/day) and Fruits (2-4 servings/day). Eat Moderately: Dairy (2-3 servings/day) and Protein (2-3 servings/day). Eat Least: Fats, oils, and sweets.');");
        db.execSQL("INSERT INTO " + TABLE_HEALTH_PROGRAM + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") VALUES ('Keto', 'Allowed Foods: Meats, fatty fish, eggs, butter, cheese, nuts, seeds, healthy oils, avocados, low-carb vegetables. Avoided Foods: Sugary foods, grains, fruit, beans, root vegetables, low-fat products, unhealthy fats, alcohol, sugar-free diet foods.');");
        db.execSQL("INSERT INTO " + TABLE_HEALTH_PROGRAM + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") VALUES ('Vegan', 'Allowed Foods: Fruits, vegetables, nuts, seeds, legumes, whole grains, plant-based oils, fortified plant milks and yogurts. Avoided Foods: All meat, dairy products, eggs, animal-derived ingredients like gelatin and honey.');");
        // Insert other initial data
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTH_PROGRAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_PROGRAM);
        onCreate(db);
    }
}

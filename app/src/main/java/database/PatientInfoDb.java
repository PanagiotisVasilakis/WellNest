package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PatientInfoDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "patientinfo.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_EMAIL = "email";
    // Add other necessary columns

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PATIENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_AGE + " TEXT, " +
                    COLUMN_EMAIL + " TEXT);"; // Add other columns here

    public PatientInfoDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        onCreate(db);
    }

    public void addPatient(PatientInfo patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_AGE, patient.getAge());
        values.put(COLUMN_EMAIL, patient.getEmail());
        // Add other values
        db.insert(TABLE_PATIENTS, null, values);
    }

    public static class PatientInfo {
        private long id;
        private String name;
        private String age;
        private String email;
        // Add other necessary fields

        // Getters and Setters
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getAge() {
            return age;
        }
        public void setAge(String age) {
            this.age = age;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
    }
}

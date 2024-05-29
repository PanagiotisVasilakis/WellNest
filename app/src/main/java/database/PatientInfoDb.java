package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PatientInfoDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "patients.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_EMAIL = "email";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PATIENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_AGE + " INTEGER, " +
                    COLUMN_EMAIL + " TEXT);";

    public PatientInfoDb(@Nullable Context context) {
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
        values.put(COLUMN_ID, PatientInfo.getId());
        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_AGE, patient.getAge());
        values.put(COLUMN_EMAIL, patient.getEmail());
        db.insert(TABLE_PATIENTS, null, values);
    }

    public List<PatientInfo> getAllPatients() {
        List<PatientInfo> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_AGE,
                COLUMN_EMAIL
        };

        Cursor cursor = db.query(
                TABLE_PATIENTS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));

            PatientInfo patient = new PatientInfo(id, name, age, email);
            patientList.add(patient);
        }
        cursor.close();

        return patientList;
    }

    public boolean isPatientExists(int PatientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(PatientId) };
        Cursor cursor = db.query(TABLE_PATIENTS, null, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public PatientInfo getPatientById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_PATIENTS, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));

            PatientInfo patient = new PatientInfo(id, name, age, email);
            cursor.close();
            return patient;
        }
        cursor.close();
        return null;
    }

    public static class PatientInfo {
        private static long id;
        private String name;
        private int age;
        private String email;

        public PatientInfo() {
            // Default constructor
        }

        public PatientInfo(long id, String name, int age, String email) {
            PatientInfo.id = id;
            this.name = name;
            this.age = age;
            this.email = email;
        }

        // Getters and Setters
        public static long getId() {
            return id;
        }

        public void setId(long id) {
            PatientInfo.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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

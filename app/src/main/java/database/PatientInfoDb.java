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

    private static final String DATABASE_NAME = "patientinfo.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_PATIENT_ID = "_id";
    public static final String COLUMN_PATIENT_NAME = "name";
    public static final String COLUMN_PATIENT_AGE = "age";

    public static final String TABLE_PATIENT_DOCTORS = "patient_doctors";
    public static final String COLUMN_DOCTOR_ID = "_id";
    public static final String COLUMN_PATIENT_ID_FK = "patient_id";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String COLUMN_DOCTOR_SPECIALIZATION = "doctor_specialization";

    private static final String TABLE_CREATE_PATIENTS =
            "CREATE TABLE " + TABLE_PATIENTS + " (" +
                    COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PATIENT_NAME + " TEXT, " +
                    COLUMN_PATIENT_AGE + " INTEGER);";

    private static final String TABLE_CREATE_PATIENT_DOCTORS =
            "CREATE TABLE " + TABLE_PATIENT_DOCTORS + " (" +
                    COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PATIENT_ID_FK + " INTEGER, " +
                    COLUMN_DOCTOR_NAME + " TEXT, " +
                    COLUMN_DOCTOR_SPECIALIZATION + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_PATIENT_ID_FK + ") REFERENCES " + TABLE_PATIENTS + "(" + COLUMN_PATIENT_ID + "));";

    public PatientInfoDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PATIENTS);
        db.execSQL(TABLE_CREATE_PATIENT_DOCTORS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_DOCTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        onCreate(db);
    }

    public long addPatient(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_NAME, name);
        values.put(COLUMN_PATIENT_AGE, age);

        return db.insert(TABLE_PATIENTS, null, values);
    }

    public void addPatientDoctor(long patientId, String doctorName, String doctorSpecialization) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_ID_FK, patientId);
        values.put(COLUMN_DOCTOR_NAME, doctorName);
        values.put(COLUMN_DOCTOR_SPECIALIZATION, doctorSpecialization);

        db.insert(TABLE_PATIENT_DOCTORS, null, values);
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_PATIENT_ID,
                COLUMN_PATIENT_NAME,
                COLUMN_PATIENT_AGE
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
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_NAME));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_AGE));

            Patient patient = new Patient(id, name, age);
            patientList.add(patient);
        }
        cursor.close();

        return patientList;
    }

    public List<Doctor> getDoctorsForPatient(long patientId) {
        List<Doctor> doctorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_DOCTOR_ID,
                COLUMN_DOCTOR_NAME,
                COLUMN_DOCTOR_SPECIALIZATION
        };

        String selection = COLUMN_PATIENT_ID_FK + " = ?";
        String[] selectionArgs = { String.valueOf(patientId) };

        Cursor cursor = db.query(
                TABLE_PATIENT_DOCTORS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME));
            String specialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_SPECIALIZATION));

            Doctor doctor = new Doctor(id, name, specialization);
            doctorList.add(doctor);
        }
        cursor.close();

        return doctorList;
    }

    // Inner Patient class
    public static class Patient {
        private long id;
        private String name;
        private int age;

        public Patient(long id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    // Inner Doctor class
    public static class Doctor {
        private long id;
        private String name;
        private String specialization;

        public Doctor(long id, String name, String specialization) {
            this.id = id;
            this.name = name;
            this.specialization = specialization;
        }

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

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }
    }
}

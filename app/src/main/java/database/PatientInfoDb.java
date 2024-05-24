package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PatientInfoDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "patientinfo.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PATIENT_INFO = "patient_info";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String COLUMN_DOCTOR_SPECIALIZATION = "doctor_specialization";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PATIENT_INFO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_AGE + " INTEGER, " +
                    COLUMN_DOCTOR_NAME + " TEXT, " +
                    COLUMN_DOCTOR_SPECIALIZATION + " TEXT);";

    public PatientInfoDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_INFO);
        onCreate(db);
    }

    public void addPatientInfo(String name, int age, String doctorName, String doctorSpecialization) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_DOCTOR_NAME, doctorName);
        values.put(COLUMN_DOCTOR_SPECIALIZATION, doctorSpecialization);

        long result = db.insert(TABLE_PATIENT_INFO, null, values);
        Log.d("PatientInfoDb", "Inserted Patient Info: " + name + ", Result: " + result);
    }

    public List<PatientInfo> getAllPatientInfo() {
        List<PatientInfo> patientInfoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_AGE,
                COLUMN_DOCTOR_NAME,
                COLUMN_DOCTOR_SPECIALIZATION
        };

        Cursor cursor = db.query(
                TABLE_PATIENT_INFO,
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
            String doctorName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME));
            String doctorSpecialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_SPECIALIZATION));

            PatientInfo patientInfo = new PatientInfo(id, name, age, doctorName, doctorSpecialization);
            patientInfoList.add(patientInfo);

            Log.d("PatientInfoDb", "Fetched Patient Info: " + name);
        }
        cursor.close();

        return patientInfoList;
    }

    // Inner PatientInfo class
    public static class PatientInfo {
        private long id;
        private String name;
        private int age;
        private String doctorName;
        private String doctorSpecialization;

        public PatientInfo(long id, String name, int age, String doctorName, String doctorSpecialization) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.doctorName = doctorName;
            this.doctorSpecialization = doctorSpecialization;
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

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDoctorSpecialization() {
            return doctorSpecialization;
        }

        public void setDoctorSpecialization(String doctorSpecialization) {
            this.doctorSpecialization = doctorSpecialization;
        }
    }
}

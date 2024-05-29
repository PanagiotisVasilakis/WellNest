package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "doctors.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DOCTORS = "doctors";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_AVAILABILITY = "availability";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SPECIALIZATION = "specialization";

    private static final String TABLE_PATIENT_DOCTORS = "patient_doctors";
    public static final String COLUMN_PATIENT_ID = "patient_id";
    public static final String COLUMN_DOCTOR_ID = "doctor_id";

    private static final String TABLE_CREATE_DOCTORS =
            "CREATE TABLE " + TABLE_DOCTORS + " (" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_LOCATION + " TEXT, " +
                    COLUMN_AVAILABILITY + " TEXT, " +
                    COLUMN_CONTACT + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_SPECIALIZATION + " TEXT);";

    private static final String TABLE_CREATE_PATIENT_DOCTORS =
            "CREATE TABLE " + TABLE_PATIENT_DOCTORS + " (" +
                    COLUMN_PATIENT_ID + " INTEGER, " +
                    COLUMN_DOCTOR_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_PATIENT_ID + ") REFERENCES " + UserDb.TABLE_USERS + "(" + UserDb.COLUMN_ID + "), " +
                    "FOREIGN KEY(" + COLUMN_DOCTOR_ID + ") REFERENCES " + TABLE_DOCTORS + "(" + COLUMN_ID + "));";

    public DoctorsDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_DOCTORS);
        db.execSQL(TABLE_CREATE_PATIENT_DOCTORS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_DOCTORS);
        onCreate(db);
    }

    public void doctorAddition(SQLiteDatabase db, Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, doctor.getId());
        values.put(COLUMN_NAME, doctor.getName());
        values.put(COLUMN_LOCATION, doctor.getLocation());
        values.put(COLUMN_AVAILABILITY, doctor.getAvailability());
        values.put(COLUMN_CONTACT, doctor.getContact());
        values.put(COLUMN_EMAIL, doctor.getEmail());
        values.put(COLUMN_SPECIALIZATION, doctor.getSpecialization());

        db.insert(TABLE_DOCTORS, null, values);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_LOCATION,
                COLUMN_AVAILABILITY,
                COLUMN_CONTACT,
                COLUMN_EMAIL,
                COLUMN_SPECIALIZATION
        };

        Cursor cursor = db.query(
                TABLE_DOCTORS,
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
            String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION));
            String availability = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVAILABILITY));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String specialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPECIALIZATION));

            Doctor doctor = new Doctor(id, name, location, availability, contact, email, specialization);
            doctor.setId(id);
            doctorList.add(doctor);
        }
        cursor.close();

        return doctorList;
    }

    public boolean isDoctorExists(int doctorId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(doctorId) };
        Cursor cursor = db.query(TABLE_DOCTORS, null, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void updateDoctor(SQLiteDatabase db, int doctorId, Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, doctor.getName());
        values.put(COLUMN_LOCATION, doctor.getLocation());
        values.put(COLUMN_AVAILABILITY, doctor.getAvailability());
        values.put(COLUMN_CONTACT, doctor.getContact());
        values.put(COLUMN_EMAIL, doctor.getEmail());
        values.put(COLUMN_SPECIALIZATION, doctor.getSpecialization());

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(doctorId) };

        db.update(TABLE_DOCTORS, values, selection, selectionArgs);
    }

    public List<Doctor> getAllDoctorsFromPatientDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PATIENT_DOCTORS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_ID));
            // Fetch other doctor details using the id from the TABLE_DOCTORS
            Doctor doctor = getDoctorById(id);
            if (doctor != null) {
                doctorList.add(doctor);
            }
        }
        cursor.close();

        return doctorList;
    }

    public Doctor getDoctorById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_DOCTORS, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION));
            String availability = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVAILABILITY));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String specialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPECIALIZATION));

            Doctor doctor = new Doctor(id, name, location, availability, contact, email, specialization);
            cursor.close();
            return doctor;
        }
        cursor.close();
        return null;
    }

    // Inner Doctor class
    public static class Doctor {
        private long id;
        private String name;
        private String location;
        private String availability;
        private String contact;
        private String email;
        private String specialization;

        public Doctor() {
            // Default constructor
        }

        public Doctor(Long id, String name, String location, String availability, String contact, String email, String specialization) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.availability = availability;
            this.contact = contact;
            this.email = email;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }
    }
}

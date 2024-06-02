package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import myapplication.pages.CheckUps.Checkup;
import myapplication.pages.CheckUps.Examination;

public class CheckUpDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "checkups.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CHECKUP = "checkup";
    public static final String COLUMN_CHECKUP_ID = "_id";
    public static final String COLUMN_CHECKUP_NAME = "name";

    public static final String TABLE_EXAMINATION = "examination";
    public static final String COLUMN_EXAMINATION_ID = "_id";
    public static final String COLUMN_EXAMINATION_NAME = "name";
    public static final String COLUMN_CHECKUP_ID_FK = "checkup_id";

    private static final String TABLE_CREATE_CHECKUP =
            "CREATE TABLE " + TABLE_CHECKUP + " (" +
                    COLUMN_CHECKUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CHECKUP_NAME + " TEXT NOT NULL);";

    private static final String TABLE_CREATE_EXAMINATION =
            "CREATE TABLE " + TABLE_EXAMINATION + " (" +
                    COLUMN_EXAMINATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EXAMINATION_NAME + " TEXT NOT NULL, " +
                    COLUMN_CHECKUP_ID_FK + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_CHECKUP_ID_FK + ") REFERENCES " + TABLE_CHECKUP + "(" + COLUMN_CHECKUP_ID + "));";

    public CheckUpDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_CHECKUP);
        db.execSQL(TABLE_CREATE_EXAMINATION);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAMINATION);
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        long basicHematologyId = insertCheckup(db, "Basic Hematology Check-up");
        long completeHematologyId = insertCheckup(db, "Complete Hematology Check-up");
        long cardiologicalId = insertCheckup(db, "Cardiological Examination");
        long thyroidId = insertCheckup(db, "Thyroid Check-up");

        insertExamination(db, "Complete Blood Count (CBC)", basicHematologyId);
        insertExamination(db, "Erythrocyte Sedimentation Rate (ESR)", basicHematologyId);
        insertExamination(db, "Cholesterol", basicHematologyId);
        insertExamination(db, "Iron", basicHematologyId);

        insertExamination(db, "HDL (High-Density Lipoprotein)", completeHematologyId);
        insertExamination(db, "Magnesium", completeHematologyId);

        insertExamination(db, "Electrocardiogram (ECG)", cardiologicalId);
        insertExamination(db, "Heart Triplex (Echocardiography)", cardiologicalId);

        insertExamination(db, "Thyroid-Stimulating Hormone (TSH)", thyroidId);
        insertExamination(db, "Thyroid Ultrasound", thyroidId);

        // Log inserted data
        Log.d("DatabaseHelper", "Sample data inserted");
    }

    private long insertCheckup(SQLiteDatabase db, String name) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHECKUP_NAME, name);
        long id = db.insert(TABLE_CHECKUP, null, values);
        Log.d("DatabaseHelper", "Inserted Checkup: " + name + " with ID: " + id);
        return id;
    }

    private void insertExamination(SQLiteDatabase db, String name, long checkupId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXAMINATION_NAME, name);
        values.put(COLUMN_CHECKUP_ID_FK, checkupId);
        db.insert(TABLE_EXAMINATION, null, values);
        Log.d("DatabaseHelper", "Inserted Examination: " + name + " for Checkup ID: " + checkupId);
    }

    public List<Checkup> getCheckups() {
        List<Checkup> checkupList = new ArrayList<>();
        try (SQLiteDatabase db = this.getReadableDatabase(); Cursor cursor = db.query(TABLE_CHECKUP, null, null, null, null, null, null)) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHECKUP_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CHECKUP_NAME));
                    checkupList.add(new Checkup(id, name));
                } while (cursor.moveToNext());
            }
        }
        return checkupList;
    }

    public List<Examination> getExaminations(int checkupId) {
        List<Examination> examinationList = new ArrayList<>();
        try (SQLiteDatabase db = this.getReadableDatabase(); Cursor cursor = db.query(TABLE_EXAMINATION, null, COLUMN_CHECKUP_ID_FK + "=?",
                new String[]{String.valueOf(checkupId)}, null, null, null)) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXAMINATION_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXAMINATION_NAME));
                    int chkId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHECKUP_ID_FK));
                    examinationList.add(new Examination(id, name, chkId));
                } while (cursor.moveToNext());
            }
        }
        return examinationList;
    }

    public void saveCheckup(Checkup checkup) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CHECKUP_NAME, checkup.getName());
            db.insert(TABLE_CHECKUP, null, values);
        }
    }

    public void saveExamination(Examination examination) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_EXAMINATION_NAME, examination.getName());
            values.put(COLUMN_CHECKUP_ID_FK, examination.getCheckupId());
            db.insert(TABLE_EXAMINATION, null, values);
        }
    }
}

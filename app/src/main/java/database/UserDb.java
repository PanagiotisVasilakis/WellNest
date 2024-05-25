package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class UserDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_AGE = "age";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_PHONE = "phone";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_AGE + " INTEGER, " +
                    COLUMN_USER_PASSWORD + " TEXT, " +
                    COLUMN_USER_PHONE + " TEXT);";

    public UserDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long registerUser(String email, String name, int age, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (isUserExists(email)) {
            return -1; // User already exists
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_AGE, age);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_PHONE, phone);

        return db.insert(TABLE_USERS, null, values);
    }

    public boolean isUserExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = { COLUMN_USER_ID };
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = { COLUMN_USER_ID };
        String selection = COLUMN_USER_EMAIL + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(
                TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean authenticated = (cursor.getCount() > 0);
        cursor.close();
        return authenticated;
    }
}

package myapplication.pages;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import database.DatabaseHelper;
import database.Doctor;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class AddDoctorFragment extends Fragment {

    private DatabaseHelper dbHelper;
    DoctorProfile doctorProfile = new DoctorProfile();
    private DoctorAdapter doctorAdapter;
    private List<Doctor> doctorList;

    public AddDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_doctor, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        doctorList = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(doctorList, this::showDoctorProfile);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doctorAdapter);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getDoctors(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getDoctors(newText);
                return false;
            }
        });

        renewPageloadAllDoctors();

        return view;
    }


        // Operations for Add Doctor
        @SuppressLint("NotifyDataSetChanged")
        public void getDoctors(String query) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_NAME,
                    DatabaseHelper.COLUMN_LOCATION,
                    DatabaseHelper.COLUMN_AVAILABILITY,
                    DatabaseHelper.COLUMN_CONTACT,
                    DatabaseHelper.COLUMN_EMAIL
            };

            String selection = DatabaseHelper.COLUMN_NAME + " LIKE ?";
            String[] selectionArgs = { "%" + query + "%" };

            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_DOCTORS,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            doctorList.clear();
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION));
                String availability = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AVAILABILITY));
                String contact = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CONTACT));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));

                Doctor doctor = new Doctor(name, location, availability, contact, email);
                doctor.setId(id);
                doctorList.add(doctor);
            }
            cursor.close();
            doctorAdapter.notifyDataSetChanged();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void renewPageloadAllDoctors() {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_NAME,
                    DatabaseHelper.COLUMN_LOCATION,
                    DatabaseHelper.COLUMN_AVAILABILITY,
                    DatabaseHelper.COLUMN_CONTACT,
                    DatabaseHelper.COLUMN_EMAIL
            };

            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_DOCTORS,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            doctorList.clear();
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION));
                String availability = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AVAILABILITY));
                String contact = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CONTACT));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));

                Doctor doctor = new Doctor(name, location, availability, contact, email);
                doctor.setId(id);
                doctorList.add(doctor);
            }
            cursor.close();
            doctorAdapter.notifyDataSetChanged();
        }
        public void showDoctorProfile(Doctor doctor) {

            DoctorInfo doctorInfo = doctorProfile.getDoctorInfo(doctor);
            View popupView = doctorInfo.getPopupView();
            Button btnAddDoctor = doctorInfo.getBtnAddDoctor();
            PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);

            btnAddDoctor.setOnClickListener(v -> {
                popupWindow.dismiss();
            });
        }


    // 19. Doctor Profile class
    public class DoctorProfile {

        public DoctorInfo getDoctorInfo(Doctor doctor) {
            @SuppressLint("InflateParams") View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_doctor_details, null);

            TextView tvDoctorName = popupView.findViewById(R.id.tvDoctorName);
            TextView tvDoctorLocation = popupView.findViewById(R.id.tvDoctorLocation);
            TextView tvDoctorAvailability = popupView.findViewById(R.id.tvDoctorAvailability);
            TextView tvDoctorContact = popupView.findViewById(R.id.tvDoctorContact);
            TextView tvDoctorEmail = popupView.findViewById(R.id.tvDoctorEmail);
            Button btnAddDoctor = popupView.findViewById(R.id.btnAddDoctor);

            tvDoctorName.setText(doctor.getName());
            tvDoctorLocation.setText(doctor.getLocation());
            tvDoctorAvailability.setText(doctor.getAvailability());
            tvDoctorContact.setText(doctor.getContact());
            tvDoctorEmail.setText(doctor.getEmail());
            return new DoctorInfo(popupView, btnAddDoctor);
        }

        public void returnToPage() {

        }

        public void addDoctor() {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NAME, "Dr. John Doe");
            values.put(DatabaseHelper.COLUMN_LOCATION, "123 Main St");
            values.put(DatabaseHelper.COLUMN_AVAILABILITY, "9 AM - 5 PM");
            values.put(DatabaseHelper.COLUMN_CONTACT, "123-456-7890");
            values.put(DatabaseHelper.COLUMN_EMAIL, "john.doe@example.com");

            long newRowId = db.insert(DatabaseHelper.TABLE_DOCTORS, null, values);
            if (newRowId != -1) {
                Toast.makeText(getActivity(), "Doctor saved with ID: " + newRowId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Error saving doctor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class DoctorInfo {
        private View popupView;
        private Button btnAddDoctor;

        public DoctorInfo(View popupView, Button btnAddDoctor) {
            this.popupView = popupView;
            this.btnAddDoctor = btnAddDoctor;
        }

        public View getPopupView() {
            return popupView;
        }

        public Button getBtnAddDoctor() {
            return btnAddDoctor;
        }
    }
}

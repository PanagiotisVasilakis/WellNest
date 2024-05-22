package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MakeAppointmentFragment extends Fragment {

    private MakeAppointment makeAppointment;

    public MakeAppointmentFragment() {
        // Required empty public constructor
        makeAppointment = new MakeAppointment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_make_appointment, container, false);
    }

    // 9. Make Appointment class
    public class MakeAppointment {
        // Attributes for Make Appointment
        private int new_appointment_id;
        private String new_appointment_date;
        private String new_appointment_time;

        // Operations for Make Appointment
        public void checkPatientDoctors() {
            // Implementation here
        }

        public void getDoctorAppointments() {
            // Implementation here
        }

        public void renewPage() {
            // Implementation here
        }

        public void getAppointmentsTime() {
            // Implementation here
        }

        public void showConfirmAppointment() {
            // Implementation here
        }
    }

    // 10. Add Doctor to List class
    public class AddDoctorToList {
        // Operations for Make Appointment
        public void gotoAddDoctor() {
            // Implementation here
        }

        public void returnToPage() {
            // Implementation here
        }
    }

    // 11. Doctors List class
    public class DoctorsList {
        // Attributes for Make Appointment
        private int patient_doctors_id;

        // Operations for Make Appointment
        public void getPatientDoctors() {
            // Implementation here
        }

        // Operations for Add Doctor
        public void showAddDoctor() {
            // Implementation here
        }
    }

    // 12. Doctors class
    public class Doctors {
        // Attributes for Make Appointment
        private int appointment_id;
        private String appointment_date;
        private String appointment_time;

        // Operations for Make Appointment
        public void returnDoctorAppointments() {
            // Implementation here
        }

        public void returnAppointmentTime() {
            // Implementation here
        }

        public void returnDoctors() {
            // Implementation here
        }

        public void returnNameNotFound() {
            // Implementation here
        }

        // Operations for Doctor Notes
        public void returnOldPatient() {
            // Implementation here
        }

        public void returnPatient() {
            // Implementation here
        }
    }

    // 13. Confirm Appointment class
    public class ConfirmAppointment {
        // Attributes for Make Appointment
        private boolean confirm_appointment;

        // Operations for Make Appointment
        public void addAppointment() {
            // Implementation here
        }

        public void sendMessage() {
            // Implementation here
        }

        public void returnToPage() {
            // Implementation here
        }
    }
}

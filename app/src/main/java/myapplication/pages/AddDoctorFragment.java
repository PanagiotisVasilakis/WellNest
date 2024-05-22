package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddDoctorFragment extends Fragment {

    private AddDoctor addDoctor;

    public AddDoctorFragment() {
        // Required empty public constructor
        addDoctor = new AddDoctor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_doctor, container, false);
    }

    // 18. Add Doctor class
    public class AddDoctor {
        // Operations for Add Doctor
        public void getDoctors() {
            // Implementation here
        }

        public void renewPage() {
            // Implementation here
        }

        public void showDoctorProfile() {
            // Implementation here
        }
    }

    // 19. Doctor Profile class
    public class DoctorProfile {
        // Operations for Add Doctor
        public void getDoctorInfo() {
            // Implementation here
        }

        public void returnToPage() {
            // Implementation here
        }

        public void addDoctor() {
            // Implementation here
        }
    }
}

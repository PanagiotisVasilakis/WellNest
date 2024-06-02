package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewVaccinesFragment extends Fragment {

    private NewVaccines newVaccines;

    public NewVaccinesFragment() {
        // Required empty public constructor
        newVaccines = new NewVaccines();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_vaccines, container, false);
    }

    // 25. New Vaccines class
    public class NewVaccines {
        // Operations for New Vaccines
        public void getVaccines() {
            // Implementation here
        }

        public void renewPage() {
            // Implementation here
        }

        public void showVaccinationCenters() {
            // Implementation here
        }
    }

    // 26. Vaccines class
    public class Vaccines {
        // Attributes for New Vaccines
        private String vaccineName;
        private String vaccinePurpose;
        private int vaccineID;

        // Getters and setters for attributes
        public String getVaccineName() {
            return vaccineName;
        }

        public void setVaccineName(String vaccineName) {
            this.vaccineName = vaccineName;
        }

        public String getVaccinePurpose() {
            return vaccinePurpose;
        }

        public void setVaccinePurpose(String vaccinePurpose) {
            this.vaccinePurpose = vaccinePurpose;
        }

        public int getVaccineID() {
            return vaccineID;
        }

        public void setVaccineID(int vaccineID) {
            this.vaccineID = vaccineID;
        }

        // Operations for New Vaccines
        public void returnVaccines() {
            // Implementation here
        }
    }

    // 27. Show Vaccine Centers class
    public class ShowVaccineCenters {
        // Operations for New Vaccines
        public void getVaccineCenters() {
            // Implementation here
        }

        public void getVaccineCenterInfo() {
            // Implementation here
        }

        public void showVaccineCenterInformation() {
            // Implementation here
        }

        public void renewPage() {
            // Implementation here
        }
    }

    // 28. Vaccine Centers class
    public class VaccineCenters {
        // Operations for New Vaccines
        public void returnVaccineCenterInfo() {
            // Implementation here
        }

        public void returnVaccineCenters() {
            // Implementation here
        }

        public void vaccineCenterNotFound() {
            // Implementation here
        }
    }
}

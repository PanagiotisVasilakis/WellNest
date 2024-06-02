package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EditPharmacyListFragment extends Fragment {

    private EditPharmacyList editPharmacyList;

    public EditPharmacyListFragment() {
        // Required empty public constructor
        editPharmacyList = new EditPharmacyList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_pharmacy_list, container, false);
    }

    // 29. Edit Pharmacy List class
    public class EditPharmacyList {
        // Operations for Edit Pharmacy List
        public void showAddPharmacy() {
            // Implementation here
        }

        public void showPharmacyList() {
            // Implementation here
        }
    }

    public class AddPharmacy {
        // Operations for Edit Pharmacy List
        public void getPharmacies() {
            // Implementation here
        }

        public void showPharmacyProfile() {
            // Implementation here
        }

        public void renewPage() {
            // Implementation here
        }
    }

    // 30. Pharmacy class
    public class Pharmacy {
        // Operations for Edit Pharmacy List
        public void returnPharmacies() {
            // Implementation here
        }

        public void showMessage() {
            // Implementation here
        }
    }

    // 31. Pharmacy Profile class
    public class PharmacyProfile {
        // Operations for Edit Pharmacy List
        public void getPharmacyInfo() {
            // Implementation here
        }

        public void addPharmacyToFile() {
            // Implementation here
        }

        public void addToPharmacyList() {
            // Implementation here
        }

        public void returnToPage() {
            // Implementation here
        }

        public void removePharmacy() {
            // Implementation here
        }
    }

    // 32. Pharmacy List class
    public class PharmacyList {
        // Operations for Edit Pharmacy List
        public void getPharmaciesListFromFile() {
            // Implementation here
        }

        public void showPharmacyProfile() {
            // Implementation here
        }

        public void showMessage() {
            // Implementation here
        }
    }
}

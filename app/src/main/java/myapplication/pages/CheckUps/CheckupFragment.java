package myapplication.pages.CheckUps;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myapplication.pages.R;

public class CheckupFragment extends Fragment {

    private Checkup checkup;

    public CheckupFragment() {
        // Required empty public constructor
        checkup = new Checkup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkup, container, false);
    }

    // 22. Checkup class
    public class Checkup {
        // Operations for Find and Close Checkup
        public void getCheckups() {
            // Implementation here
        }

        public void getExaminations() {
            // Implementation here
        }

        public void saveExaminations() {
            // Implementation here
        }

        public void prescribed() {
            // Implementation here
        }

        public void renewPage() {
            // Implementation here
        }

        public void gotoDailyState() {
            // Implementation here
        }
    }

    // 23. Examination class
    public class Examination {
        // Operations for Find and Close Checkup
        public void returnCheckups() {
            // Implementation here
        }

        public void returnExaminations() {
            // Implementation here
        }

        public void returnNoPrescribedMessage() {
            // Implementation here
        }
    }
}

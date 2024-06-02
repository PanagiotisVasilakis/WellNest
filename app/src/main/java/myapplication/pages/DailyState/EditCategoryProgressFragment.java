package myapplication.pages.DailyState;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myapplication.pages.R;

public class EditCategoryProgressFragment extends Fragment {

    private HealthProgramFragment.HealthProgramClass healthProgramC;

    public EditCategoryProgressFragment() {
        HealthProgramFragment healthProgram = new HealthProgramFragment();
        healthProgramC = healthProgram.new HealthProgramClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_category_progress, container, false);
    }

    public class Subcategory {
        // Operations for Edit Category Progress
        public void getSubcategory() {
            // Implementation here
        }

        public void saveProgress() {
            // Implementation here
        }

        public void checkProgress() {
            // Implementation here
        }

        public void showProgressUpdated() {
            // Implementation here
        }

        public void showGoalReached() {
            // Implementation here
        }
    }

    public class ProgressUpdated {
        // Operations for Edit Category Progress
    }

    public class GoalReached {
        // Operations for Edit Category Progress
    }
}

package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EditCategoryProgress extends Fragment {

    public EditCategoryProgress() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_category_progress, container, false);
    }

    // 15. Subcategory
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

    // 16. Progress Updated
    public class ProgressUpdated {
        // Operations for Edit Category Progress
    }

    // 17. Goal Reached
    public class GoalReached {
        // Operations for Edit Category Progress
    }
}

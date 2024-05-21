package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HealthProgram extends Fragment {

    private HealthProgramClass healthProgramClass;

    public HealthProgram() {
        // Required empty public constructor
        healthProgramClass = new HealthProgramClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_health_program, container, false);
    }

    public class DailyState {
        // Operations for Health Program
        public void getHealthProgram() {
            // Implementation here
        }

        public void showHealthProgram() {
            // Implementation here
        }

        public void showProgramDetected() {
            // Implementation here
        }

        // Operations for Edit Category Progress
    }


    // 3. Health Program class
    public class HealthProgramClass {
        // Operations for Health Program
        public void calculateBMI() {
            // Implementation here
        }

        public void saveBMI() {
            // Implementation here
        }

        public void getWorkoutProgram() {
            // Implementation here
        }

        public void showProgram() {
            // Implementation here
        }

        public void getHealthProgram() {
            // Implementation here
        }

        public void showSubcategory() {
            // Implementation here
        }
    }

    public class Program {
        // Attributes for Health Program
        private int program_id;

        // Getters and setters for program_id
        public int getProgramId() {
            return program_id;
        }

        public void setProgramId(int program_id) {
            this.program_id = program_id;
        }
    }

    public class ProgramDetected {
        // Attributes for Health Program
        private int current_program_id;

        // Getters and setters for current_program_id
        public int getCurrentProgramId() {
            return current_program_id;
        }

        public void setCurrentProgramId(int current_program_id) {
            this.current_program_id = current_program_id;
        }

        // Operations for Health Program
        public void useCurrentProgram() {
            // Implementation here
        }

        public void getCurrentProgram() {
            // Implementation here
        }

        public void deleteCurrentProgram() {
            // Implementation here
        }

        public void returnToPage() {
            // Implementation here
        }
    }

    public class WorkoutPrograms {
        // Attributes for Health Program
        private int workout_program_id;
        private int workout_program_hard;
        private int check_BMI;

        // Getters and setters for attributes
        public int getWorkoutProgramId() {
            return workout_program_id;
        }

        public void setWorkoutProgramId(int workout_program_id) {
            this.workout_program_id = workout_program_id;
        }

        public int getWorkoutProgramHard() {
            return workout_program_hard;
        }

        public void setWorkoutProgramHard(int workout_program_hard) {
            this.workout_program_hard = workout_program_hard;
        }

        public int getCheckBMI() {
            return check_BMI;
        }

        public void setCheckBMI(int check_BMI) {
            this.check_BMI = check_BMI;
        }
    }
}

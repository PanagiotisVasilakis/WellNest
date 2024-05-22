package myapplication.pages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MedicalAssistFragment extends Fragment {

    public MedicalAssistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_assist, container, false);

        Button btnMakeAppointment = view.findViewById(R.id.btnMakeAppointment);
        Button btnNewVaccines = view.findViewById(R.id.btnNewVaccines);
        Button btnEditPharmacyList = view.findViewById(R.id.btnEditPharmacyList);
        Button btnFindCheckups = view.findViewById(R.id.btnFindCheckups);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        btnMakeAppointment.setOnClickListener(v -> navController.navigate(R.id.nav_make_appointment));
        btnNewVaccines.setOnClickListener(v -> navController.navigate(R.id.nav_new_vaccines));
        btnEditPharmacyList.setOnClickListener(v -> navController.navigate(R.id.nav_edit_pharmacy_list));
        btnFindCheckups.setOnClickListener(v -> navController.navigate(R.id.nav_checkup));

        return view;
    }
}

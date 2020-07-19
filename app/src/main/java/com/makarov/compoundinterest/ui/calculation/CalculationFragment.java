package com.makarov.compoundinterest.ui.calculation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.makarov.compoundinterest.R;

public class CalculationFragment extends Fragment {

    private CalculationViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(CalculationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calculation, container, false);
        return root;
    }
}

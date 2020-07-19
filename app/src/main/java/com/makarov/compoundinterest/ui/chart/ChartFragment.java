package com.makarov.compoundinterest.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.makarov.compoundinterest.R;
import com.makarov.compoundinterest.ui.DataHolderClass;

public class ChartFragment extends Fragment {

    private ChartViewModel dashboardViewModel;


    View root;
    TextView textView;
    public String data = "Fuck";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(ChartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chart, container, false);
        textView = root.findViewById(R.id.text_dashboard);
        String data = DataHolderClass.getInstance().getDistributor_id();
        textView.setText(data);
        return root;
    }



}

package com.makarov.compoundinterest.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.makarov.compoundinterest.R;

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
        return root;
    }

    public void show_charts(){
        Toast.makeText(getActivity(),"Text!",Toast.LENGTH_LONG).show();
    }


}

package com.makarov.compoundinterest.ui.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.makarov.compoundinterest.R;
import com.makarov.compoundinterest.ui.DataHolderClass;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class ChartFragment extends Fragment {

    private ChartViewModel dashboardViewModel;


    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(ChartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chart, container, false);

        BarChart barChart = root.findViewById(R.id.barChart);
        ArrayList<BarEntry> balances = new ArrayList<>();

        try {
            String[] data = DataHolderClass.getInstance().getDistributor_id().split(",");
            if (data.length >= 4) {
                long startingBalance = Long.parseLong(data[0]);
                long monthlyContribution = Long.parseLong(data[1]);
                double interestRate = Double.parseDouble(data[2]);
                long duration = Long.parseLong(data[3]);

                balances.clear();
                BigDecimal capital = new BigDecimal(String.valueOf(startingBalance));
                String divider = "1";
                for(int i = 0; i < duration; i++) {
                    capital = capital.add(capital.multiply(new BigDecimal(String.valueOf(interestRate))));
                    capital = capital.add(new BigDecimal(String.valueOf(monthlyContribution * 12)));
                    capital = capital.divide(new BigDecimal(divider));

                    long chartValue = capital.longValue();
                    if(chartValue < 0){
                        balances.clear();
                        divider += "000";
                        capital = new BigDecimal(String.valueOf(startingBalance));
                        i = 0;
                    }
                    else {
                        balances.add(new BarEntry(i + 1, chartValue));
                    }

                }
            }

        }

        catch (Exception e){


        }

        finally {
            BarDataSet barDataSet = new BarDataSet(balances, "Balance");
            barDataSet.setColors(Color.rgb(144,238,144));
            barDataSet.setDrawValues(false);

            BarData barData = new BarData(barDataSet);

            barChart.setFitBars(true);
            barChart.setData(barData);
            barChart.animateY(1000);

            barChart.getDescription().setText("");
            barChart.getAxisLeft().setDrawLabels(false);
            barChart.getAxisRight().setDrawLabels(false);
            barChart.getXAxis().setDrawLabels(false);

            barChart.getLegend().setEnabled(false);
        }


        return root;
    }



}

package com.makarov.compoundinterest.ui.calculation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.makarov.compoundinterest.MainActivity;
import com.makarov.compoundinterest.R;
import com.makarov.compoundinterest.ui.DataHolderClass;
import com.makarov.compoundinterest.ui.chart.ChartFragment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

public class CalculationFragment extends Fragment {

    private CalculationViewModel homeViewModel;

    private EditText startingBalanceEdit;
    private EditText monthlyContributionEdit;
    private EditText interestRateEdit;
    private EditText durationEdit;
    private TextView balance;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(CalculationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calculation, container, false);

        startingBalanceEdit = (EditText) root.findViewById(R.id.startingBalanceEdit);
        monthlyContributionEdit = (EditText) root.findViewById(R.id.monthlyContributionEdit);
        interestRateEdit = (EditText) root.findViewById(R.id.interestRateEdit);
        durationEdit = (EditText) root.findViewById(R.id.durationEdit);

        balance = (TextView) root.findViewById(R.id.balance);

        try{
            String[] data = DataHolderClass.getInstance().getDistributor_id().split(",");
            if (data.length >= 4) {
                startingBalanceEdit.setText(data[0]);
                monthlyContributionEdit.setText(data[1]);
                double interest = Double.parseDouble(data[2]) * 100;
                if(interest == (int)interest){
                    interestRateEdit.setText(String.valueOf((int)interest));
                }
                else{
                    interestRateEdit.setText(String.valueOf(interest));
                }
                durationEdit.setText(data[3]);
                balance.setText(NumberFormat.getInstance(Locale.US).format(new BigDecimal(data[4])) + " $");
            }
        }

        catch(Exception e){

        }

        Button calculate = (Button) root.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    long startingBalance = Long.parseLong(startingBalanceEdit.getText().toString());
                    long monthlyContribution = Long.parseLong(monthlyContributionEdit.getText().toString());
                    double interestRate = Double.parseDouble(interestRateEdit.getText().toString());
                    interestRate = interestRate * 0.01;
                    long duration = Long.parseLong(durationEdit.getText().toString());

                    BigDecimal capital = new BigDecimal(String.valueOf(startingBalance));

                    for (int i = 0; i < duration; i++) {
                        capital = capital.add(capital.multiply(new BigDecimal(String.valueOf(interestRate))));
                        capital = capital.add(new BigDecimal(String.valueOf(monthlyContribution * 12)));
                    }
                    // round to hundredths
                    capital = capital.multiply(new BigDecimal(100));
                    BigInteger capital_integer = capital.toBigInteger();
                    BigDecimal result = new BigDecimal(capital_integer);
                    result = result.divide(new BigDecimal(100));


                    String balanceText = NumberFormat.getInstance(Locale.US).format(result) + " $";

                    balance.setText(balanceText);
                    String data = String.valueOf(startingBalance) + "," + String.valueOf(monthlyContribution)
                            + "," + String.valueOf(interestRate) + "," + String.valueOf(duration) + "," + String.valueOf(result);
                    DataHolderClass.getInstance().setDistributor_id(data);
                }
                catch (Exception e){

                }


            }
        });
        return root;
    }

}

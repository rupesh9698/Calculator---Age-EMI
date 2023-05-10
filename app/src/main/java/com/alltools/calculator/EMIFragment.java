package com.alltools.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EMIFragment extends Fragment {

    EditText loanAmountEt, interestRateEt, loanTenureEt;
    Button emiCalculateBtn;
    TextView resultTv, loanEMITv, totalInterestPayableTv, totalPaymentTv;
    String loanAmount, interestRate, loanTenure;
    LinearLayout emiLL1, emiLL2, emiLL3;
    RadioGroup loanTenureRg;
    RadioButton loanMonthsRb, loanYearRb;
    float loanAmountFloat, interestRateFloat, loanTenureFloat, loanEMIFloat, totalPaymentFloat, totalInterestPayableFloat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_e_m_i, container, false);

        loanAmountEt = view.findViewById(R.id.loanAmountEt);
        interestRateEt = view.findViewById(R.id.interestRateEt);
        loanTenureEt = view.findViewById(R.id.loanTenureEt);
        emiCalculateBtn = view.findViewById(R.id.emiCalculateBtn);
        resultTv = view.findViewById(R.id.resultTv);
        loanEMITv = view.findViewById(R.id.loanEMITv);
        totalInterestPayableTv = view.findViewById(R.id.totalInterestPayableTv);
        totalPaymentTv = view.findViewById(R.id.totalPaymentTv);
        emiLL1 = view.findViewById(R.id.emiLL1);
        emiLL2 = view.findViewById(R.id.emiLL2);
        emiLL3 = view.findViewById(R.id.emiLL3);
        loanTenureRg = view.findViewById(R.id.loanTenureRg);
        loanTenureRg.check(R.id.loanMonthsRb);
        loanMonthsRb = view.findViewById(R.id.loanMonthsRb);
        loanYearRb = view.findViewById(R.id.loanYearRb);

        emiCalculateBtn.setOnClickListener(v -> {

            loanAmount = loanAmountEt.getText().toString();
            interestRate = interestRateEt.getText().toString();
            loanTenure = loanTenureEt.getText().toString();

            if (TextUtils.isEmpty(loanAmount)) {
                Toast.makeText(getActivity(), "Enter Loan Amount", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(interestRate)) {
                Toast.makeText(getActivity(), "Enter Interest Rate", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(loanTenure)) {
                Toast.makeText(getActivity(), "Enter Loan Tenure", Toast.LENGTH_LONG).show();
            }
            else {

                resultTv.setVisibility(View.VISIBLE);
                emiLL1.setVisibility(View.VISIBLE);
                emiLL2.setVisibility(View.VISIBLE);
                emiLL3.setVisibility(View.VISIBLE);

                int selectId = loanTenureRg.getCheckedRadioButtonId();
                final RadioButton radioButton = view.findViewById(selectId);
                String tenureType = radioButton.getText().toString();

                loanAmountFloat = Float.parseFloat(loanAmount);
                interestRateFloat = Float.parseFloat(interestRate);

                if (tenureType.equals("Months")) {
                    loanTenureFloat = Float.parseFloat(loanTenure);
                }
                else {
                    loanTenureFloat = Float.parseFloat(loanTenure) * 12;
                }

                interestRateFloat = interestRateFloat / (12 * 100);

                loanEMIFloat = (float) ((loanAmountFloat * interestRateFloat * Math.pow(1 + interestRateFloat, loanTenureFloat)) / (Math.pow(1 + interestRateFloat, loanTenureFloat) - 1));

                totalPaymentFloat = loanEMIFloat * loanTenureFloat;

                totalInterestPayableFloat = totalPaymentFloat - loanAmountFloat;

                String loanEMIString = Float.toString(loanEMIFloat);
                loanEMITv.setText(loanEMIString);

                String totalInterestPayableString = Float.toString(totalInterestPayableFloat);
                totalInterestPayableTv.setText(totalInterestPayableString);

                String totalPaymentString = Float.toString(totalPaymentFloat);
                totalPaymentTv.setText(totalPaymentString);
            }
        });

        return view;
    }
}
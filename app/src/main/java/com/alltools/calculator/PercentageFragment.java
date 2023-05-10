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
import android.widget.TextView;
import android.widget.Toast;

public class PercentageFragment extends Fragment {

    EditText obtainedMarksEt, totalMarksEt;
    String obtainedMarks, totalMarks;
    Button percentageCalculateBtn;
    TextView resultTv, percentageTv;
    LinearLayout percentageLL1;

    float obtainedMarksFloat, totalMarksFloat, percentageFloat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_percentage, container, false);

        obtainedMarksEt = view.findViewById(R.id.obtainedMarksEt);
        totalMarksEt = view.findViewById(R.id.totalMarksEt);
        percentageCalculateBtn = view.findViewById(R.id.percentageCalculateBtn);
        resultTv = view.findViewById(R.id.resultTv);
        percentageTv = view.findViewById(R.id.percentageTv);
        percentageLL1 = view.findViewById(R.id.percentageLL1);

        percentageCalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obtainedMarks = obtainedMarksEt.getText().toString();
                totalMarks = totalMarksEt.getText().toString();

                if (TextUtils.isEmpty(obtainedMarks)) {
                    Toast.makeText(getActivity(), "Enter Obtained Marks", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(totalMarks)) {
                    Toast.makeText(getActivity(), "Enter Total Marks", Toast.LENGTH_LONG).show();
                }
                else {

                    resultTv.setVisibility(View.VISIBLE);
                    percentageLL1.setVisibility(View.VISIBLE);

                    obtainedMarksFloat = Float.parseFloat(obtainedMarks);
                    totalMarksFloat = Float.parseFloat(totalMarks);

                    percentageFloat = ((obtainedMarksFloat / totalMarksFloat) * 100);

                    String percentageString = Float.toString(percentageFloat);
                    percentageTv.setText(percentageString);
                }
            }
        });

        return view;
    }
}
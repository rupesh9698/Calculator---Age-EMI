package com.alltools.calculator;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeFragment extends Fragment {

    Button birthBtn, todayBtn, ageCalculateBtn;
    TextView resultTv, result1Tv, result2Tv, result3Tv, result4Tv, result5Tv, result6Tv, result7Tv;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_age, container, false);

        birthBtn = view.findViewById(R.id.birthBtn);
        todayBtn = view.findViewById(R.id.todayBtn);
        ageCalculateBtn = view.findViewById(R.id.ageCalculateBtn);
        resultTv = view.findViewById(R.id.resultTv);
        result1Tv = view.findViewById(R.id.result1Tv);
        result2Tv = view.findViewById(R.id.result2Tv);
        result3Tv = view.findViewById(R.id.result3Tv);
        result4Tv = view.findViewById(R.id.result4Tv);
        result5Tv = view.findViewById(R.id.result5Tv);
        result6Tv = view.findViewById(R.id.result6Tv);
        result7Tv = view.findViewById(R.id.result7Tv);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        birthBtn.setText(date);
        todayBtn.setText(date);

        birthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener1, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month = month + 1;
                if (month == 10) {
                    String date = day + "/" + month + "/" + year;
                    birthBtn.setText(date);
                }
                else if (month == 11) {
                    String date = day + "/" + month + "/" + year;
                    birthBtn.setText(date);
                }
                else if (month == 12) {
                    String date = day + "/" + month + "/" + year;
                    birthBtn.setText(date);
                }
                else {
                    String date = day + "/" + "0" + month + "/" + year;
                    birthBtn.setText(date);
                }
            }
        };

        todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener2, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month = month + 1;
                if (month == 10) {
                    String date = day + "/" + month + "/" + year;
                    todayBtn.setText(date);
                }
                else if (month == 11) {
                    String date = day + "/" + month + "/" + year;
                    todayBtn.setText(date);
                }
                else if (month == 12) {
                    String date = day + "/" + month + "/" + year;
                    todayBtn.setText(date);
                }
                else {
                    String date = day + "/" + "0" + month + "/" + year;
                    todayBtn.setText(date);
                }
            }
        };

        ageCalculateBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                resultTv.setVisibility(View.INVISIBLE);
                result1Tv.setVisibility(View.INVISIBLE);
                result2Tv.setVisibility(View.INVISIBLE);
                result3Tv.setVisibility(View.INVISIBLE);
                result4Tv.setVisibility(View.INVISIBLE);
                result5Tv.setVisibility(View.INVISIBLE);
                result6Tv.setVisibility(View.INVISIBLE);
                result7Tv.setVisibility(View.INVISIBLE);

                String sDate = birthBtn.getText().toString();
                String eDate = todayBtn.getText().toString();

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                try {

                    Date date1 = simpleDateFormat1.parse(sDate);
                    Date date2 = simpleDateFormat1.parse(eDate);

                    long startDate = date1.getTime();
                    long endDate = date2.getTime();

                    if (startDate <= endDate) {

                        resultTv.setVisibility(View.VISIBLE);
                        result1Tv.setVisibility(View.VISIBLE);
                        result2Tv.setVisibility(View.VISIBLE);
                        result3Tv.setVisibility(View.VISIBLE);
                        result4Tv.setVisibility(View.VISIBLE);
                        result5Tv.setVisibility(View.VISIBLE);
                        result6Tv.setVisibility(View.VISIBLE);
                        result7Tv.setVisibility(View.VISIBLE);

                        Period period1 = new Period(startDate, endDate, PeriodType.yearMonthDay());
                        int years = period1.getYears();
                        int months = period1.getMonths();
                        int days = period1.getDays();
                        result1Tv.setText(years + " Years" + " | " + months + " Months" + " | " + days + " Days");

                        Period period2 = new Period(startDate, endDate, PeriodType.months());
                        int totalMonths = period2.getMonths();
                        result2Tv.setText(totalMonths + " Months" + " | " + days + " Days");

                        Period period3 = new Period(startDate, endDate, PeriodType.weeks());
                        int totalWeeks = period3.getWeeks();
                        result3Tv.setText(totalWeeks + " Weeks");

                        Period period4 = new Period(startDate, endDate, PeriodType.days());
                        int totalDays = period4.getDays();
                        result4Tv.setText(totalDays + " Days");

                        Period period5 = new Period(startDate, endDate, PeriodType.hours());
                        int hours = period5.getHours();
                        result5Tv.setText(hours + " Hours");

                        Period period6 = new Period(startDate, endDate, PeriodType.minutes());
                        int minutes = period6.getMinutes();
                        result6Tv.setText(minutes + " Minutes");

                        Period period7 = new Period(startDate, endDate, PeriodType.seconds());
                        int seconds = period7.getSeconds();
                        result7Tv.setText(seconds + " Seconds");
                    }
                    else {
                        Toast.makeText(getContext(), "Birth date should not be larger than today's date", Toast.LENGTH_LONG).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
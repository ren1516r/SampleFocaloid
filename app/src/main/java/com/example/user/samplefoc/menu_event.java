package com.example.user.samplefoc;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.samplefoc.Broadcast.AlarmRecBroadcast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by user on 2/21/2017.
 */

public class menu_event extends Fragment  {
TextView txt_time,txt_date;
    Button btn_add_evnt;
    EditText txt_evnt_name;
    String evnt_name;
    private Calendar target_cal =Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //Edited like this to get the view, so that we can use view.findbyid
        View view= inflater.inflate(R.layout.frg_event, container, false);

        txt_date=(TextView)view.findViewById(R.id.edtxtDate);
        txt_time=(TextView)view.findViewById(R.id.edtxtTime);
txt_evnt_name= (EditText)view.findViewById(R.id.edtxtEventName);
        evnt_name=txt_evnt_name.getText().toString();
btn_add_evnt=(Button)view.findViewById(R.id.btnAddEvent);


txt_date.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // Log.d("Clicked date", String.valueOf(txt_date));

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int display_month;
                display_month = monthOfYear + 1;
                txt_date.setText(dayOfMonth + "-" + (display_month) + "-" + year);
                target_cal.set(Calendar.YEAR, year);

                target_cal.set(Calendar.MONTH, monthOfYear);
                target_cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);


            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();



}
});

    txt_time.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog= new TimePickerDialog(getActivity(),new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txt_time.setText(hourOfDay+" : "+minute);
                    target_cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    target_cal.set(Calendar.MINUTE,minute);
                }
            },mHour,mMinute,false);
            timePickerDialog.show();

        }
    });

        btn_add_evnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(evnt_name)||txt_date.getText()==""||txt_time.getText()==""){

                    Toast.makeText(getContext(),"Fill all fields",Toast.LENGTH_LONG).show();
                }
                else{


                    Log.d("Target CAl", String.valueOf(target_cal.get(Calendar.MONTH)));

                    AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);

                    Intent i =new Intent(getContext(),AlarmRecBroadcast.class);

                    PendingIntent pendingIntent =PendingIntent.getBroadcast(getContext(),0,i,0);
                    Log.d("Target time", String.valueOf(target_cal.getTime()));
                    alarmManager.set(AlarmManager.RTC,target_cal.getTimeInMillis(),pendingIntent);

               }



            }
        });

        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Events");




    }




    }


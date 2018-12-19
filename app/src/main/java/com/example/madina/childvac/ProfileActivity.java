package com.example.madina.childvac;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madina.childvac.models.Child;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    //editable
    TextView phone_number;
    TextView date_of_birth;
    TextView pacient_name;
    TextView edit_text;
    ImageView edit_icon;
    TextView cancel;
    TextView save;

    //non-editable
    TextView age;
    TextView total_vaccines;
    TextView hospital_name;
    TextView month;
    TextView vaccines_month;
    TextView visits_month;
    TextView doctor;
    TextView division;


    //Random Shiz
    String name = "";
    String phone = "";
    String birth = "";

    public void edit(View view){
        phone_number.setEnabled(true);
        date_of_birth.setEnabled(true);
        pacient_name.setEnabled(true);

        pacient_name.setBackgroundColor(Color.parseColor("#e2f5fb"));
        phone_number.setBackgroundColor(Color.parseColor("#e2f5fb"));
        date_of_birth.setBackgroundColor(Color.parseColor("#e2f5fb"));

        edit_icon.setVisibility(View.INVISIBLE);
        edit_text.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
    }

    public void onSave(View view){
        phone_number.setEnabled(false);
        date_of_birth.setEnabled(false);
        pacient_name.setEnabled(false);

        pacient_name.setBackgroundResource(0);
        date_of_birth.setBackgroundResource(0);
        phone_number.setBackgroundResource(0);

        edit_icon.setVisibility(View.VISIBLE);
        edit_text.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

    }

    public void onCancel(View view){

        pacient_name.setText(name);
        phone_number.setText(phone);
        date_of_birth.setText(birth);


        phone_number.setEnabled(false);
        date_of_birth.setEnabled(false);
        pacient_name.setEnabled(false);

        pacient_name.setBackgroundResource(0);
        date_of_birth.setBackgroundResource(0);
        phone_number.setBackgroundResource(0);

        edit_icon.setVisibility(View.VISIBLE);
        edit_text.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

    }

    private void setViews(){
        phone_number = findViewById(R.id.baby_phone);
        date_of_birth = findViewById(R.id.baby_birth);
        pacient_name = findViewById(R.id.baby_name);
        edit_icon = findViewById(R.id.edit_icon);
        edit_text = findViewById(R.id.edit_textView);
        cancel = findViewById(R.id.button_cancel);
        save = findViewById(R.id.button_save);
        age = findViewById(R.id.baby_age);
        total_vaccines = findViewById(R.id.baby_vaccines);
        hospital_name = findViewById(R.id.hospital_name);
        month = findViewById(R.id.month);
        vaccines_month = findViewById(R.id.number_vaccines);
        visits_month = findViewById(R.id.number_visits);
        doctor = findViewById(R.id.personal_doctor);
        division = findViewById(R.id.hospital_division);

        String login = getIntent().getStringExtra("childLogin");

        App.getApi().getChild(login).enqueue(new Callback<Child>() {
            @Override
            public void onResponse(Call<Child> call, Response<Child> response) {
                Child c = response.body();
                String dOfB = "";
                try {
                    dOfB = new SimpleDateFormat("dd/MM/YYYY")
                                    .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
                                    .parse(c.getDateOfBirth()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                pacient_name.setText(c.getFirstName() + " " + c.getLastName());
                date_of_birth.setText(dOfB);
                phone_number.setText(c.getPhone());
            }

            @Override
            public void onFailure(Call<Child> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setViews();
    }
}

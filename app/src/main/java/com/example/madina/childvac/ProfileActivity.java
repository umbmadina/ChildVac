package com.example.madina.childvac;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madina.childvac.models.Child;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static Child child = null;
    Toast toast;

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
        if(phone_number.getText().length() !=  0 || date_of_birth.getText().length() != 0 || pacient_name.getText().length() != 0){
            String[] fullName = pacient_name.getText().toString().split(" ");

            String date = date_of_birth.getText().toString();
            Pattern p = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");
            Matcher m = p.matcher(date);
            boolean valid = m.matches();

            if(fullName.length == 2 && valid) {
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

                child.setFirstName(fullName[0]);
                child.setLastName(fullName[1]);
                child.setPhone(phone_number.getText().toString());
                child.setDateOfBirth(date_of_birth.getText().toString());

                App.getApi().updateChild(child.getId(), child).enqueue(new Callback<Child>(){

                    @Override
                    public void onResponse(Call<Child> call, Response<Child> response) {
                        setViews();
                    }

                    @Override
                    public void onFailure(Call<Child> call, Throwable t) {

                    }
                });
            } else {
                toast.show();
            }
        } else {
            toast.show();
        }

    }

    public void onCancel(View view){
        setViews();

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
        final String datePattern = "MM/dd/YYYY";

        String login = getIntent().getStringExtra("childLogin");
        App.getApi().getChild(login).enqueue(new Callback<Child>() {
            @Override
            public void onResponse(Call<Child> call, Response<Child> response) {
                child = response.body();

                String dOfB = "";
                Date date;
                int intAge = 0;
                try {
                    dOfB = new SimpleDateFormat(datePattern)
                                    .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    .parse(child.getDateOfBirth()));
                    date = new SimpleDateFormat("MM/dd/YYYY").parse(dOfB);
                    Date today  = Calendar.getInstance().getTime();
                    intAge = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                            today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                            .getYears();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                age.setText(String.valueOf(intAge));
                pacient_name.setText(child.getFirstName() + " " + child.getLastName());
                date_of_birth.setText(dOfB);
                phone_number.setText(child.getPhone());
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
        toast = Toast.makeText(getApplicationContext(),
                "Wrong Inputs",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);

        setViews();
    }
}

package com.example.madina.childvac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madina.childvac.models.Child;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Button authButton = findViewById(R.id.button_auth);

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView login = findViewById(R.id.editText);
                final TextView password = findViewById(R.id.editText2);
                final Toast toast = Toast.makeText(getApplicationContext(),
                        "",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);

                if(login.getText().length() == 0  || password.getText().length() == 0) {
                    toast.setText("Please, fill in all blanks.");
                    toast.show();
                } else {
                    App.getApi().getChild(login.getText().toString()).enqueue(new Callback<Child>() {
                        @Override
                        public void onResponse(Call<Child> call, Response<Child> response) {
                            String p = password.getText().toString();
                            try {
                                if (!response.body().getPassword().equals(p) || response.body() == null){
                                    throw new NullPointerException();
                                } else {
                                    /* save child data to use in the future */
                                    Gson gson = new Gson();
                                    String childJson = gson.toJson(response.body());
                                    /* end */

                                    Intent mainIntent = new Intent(AuthActivity.this,MainActivity.class);
                                    mainIntent.putExtra("ChildJson", childJson);
                                    startActivity(mainIntent);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                toast.setText("Incorrect password or login. Try again.");
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Child> call, Throwable t) {
                            toast.setText("Sorry, service error occurred, try later.");
                            toast.show();
                            t.printStackTrace();
                        }
                    });
                }

            }
        });

    }
}

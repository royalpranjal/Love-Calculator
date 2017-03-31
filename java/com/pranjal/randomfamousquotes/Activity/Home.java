package com.pranjal.randomfamousquotes.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pranjal.randomfamousquotes.R;
import com.pranjal.randomfamousquotes.Response.GetPercentageResponse;
import com.pranjal.randomfamousquotes.Service.GetPercentage;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.os.Handler;

import static com.pranjal.randomfamousquotes.Utils.Constants.BASE_URL;

public class Home extends AppCompatActivity {

    ProgressBar progressBar;
    EditText editTextName1, editTextName2;
    TextView textViewResult;

    String ans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextName1 = (EditText) findViewById(R.id.editTextName1);
        editTextName2 = (EditText) findViewById(R.id.editTextName2);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setIndeterminate(true);
    }

    public void APICALL(View v) {
        progressBar.setVisibility(View.VISIBLE);

        textViewResult.setText("");

        String name1 = editTextName1.getText().toString().trim();
        String name2 = editTextName2.getText().toString().trim();

        writeAPI(name1, name2);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);

                textViewResult.setText(ans);
            }
        }, 2000);

    }

    private void writeAPI(String fname, String sname) {
        RestAdapter adapter = new RestAdapter
                .Builder()
                .setEndpoint(BASE_URL)
                .build();

        GetPercentage service = adapter.create(GetPercentage.class);

//        Toast.makeText(Home.this, name1 + " " + name2, Toast.LENGTH_LONG).show();

        service.getPercentage(fname, sname, new Callback<GetPercentageResponse>() {
            @Override
            public void success(GetPercentageResponse getPercentageResponse, Response response) {
                ans = getPercentageResponse.getPercentage() + " %";
            }

            @Override
            public void failure(RetrofitError error) {
                ans = "No Internet";
                Toast.makeText(Home.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

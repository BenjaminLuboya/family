package com.code_africa.familyapp.familyapp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class Income extends AppCompatActivity {

    private EditText incomeAmount, incomeType;
    private Button submitBtn;
    private ProgressBar progressBar;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "familiabora_app_pref";
    public static final String token = "token";
    String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        Token = sharedpreferences.getString(token, null);

        if (Token == null) {
            Intent i = new Intent(Income.this, Log_In.class);
            startActivity(i);
            Income.this.finish();
        }

        incomeAmount = (EditText) findViewById(R.id.incomeAmount);
        incomeType = (EditText) findViewById(R.id.incomeType);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    private void sendData() {
        //first getting the values
        final String amount = incomeAmount.getText().toString();
        final String type = incomeType.getText().toString();

        //Toast.makeText(getBaseContext().getApplicationContext(),amount,Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext().getApplicationContext(),type,Toast.LENGTH_LONG).show();


        //validating inputs
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(getBaseContext().getApplicationContext(),amount,Toast.LENGTH_LONG).show();
            incomeAmount.setError("Please enter the amount");
            incomeAmount.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(type)) {
            incomeType.setError("Please enter your type");
            incomeType.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);



        AndroidNetworking.post("http://10.0.3.2/FBA/public/api/income")
                .addHeaders("Authorization", "Bearer " + Token)
                .addHeaders("Content-Type", "application/x-www-form-urlencoded")
                .addHeaders("Accept", "application/json")
                .addBodyParameter("amount",incomeAmount.getText().toString() )
                .addBodyParameter("type",incomeType.getText().toString() )
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginReceived.class, new ParsedRequestListener<LoginReceived>() {

                    @Override
                    public void onResponse(LoginReceived received) {
                        // do anything with response
                        Log.d("response", "Success!!");
                        Log.d("token", sharedpreferences.getString(token, null));
                        Toast.makeText(getBaseContext().getApplicationContext(),"Data Sent",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(Income.this, Dashboard.class);
                        startActivity(i);
                        Income.this.finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("error", "onError errorCode : " + error.getErrorCode());
                            Log.d("error", "onError errorBody : " + error.getErrorBody());
                            Log.d("error", "onError errorDetail : " + error.getErrorDetail());
                            // get parsed error object (If ApiError is your class)
                            // ApiError apiError = error.getErrorAsObject(ApiError.class);

                            progressBar.setVisibility(View.INVISIBLE);
                            incomeType.setError("Incorrect type");
                            incomeType.requestFocus();

                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("error", "onError errorDetail : " + error.getErrorDetail());
                            incomeType.setError("");
                            incomeType.requestFocus();
                            incomeAmount.setError("");
                            incomeAmount.requestFocus();
                        }
                    }



                });


    }

}

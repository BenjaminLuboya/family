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

public class Log_In extends AppCompatActivity {
    private Button Login,Join;
    SharedPreferences sharedpreferences;
    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;
    public static final String mypreference = "familiabora_app_pref";
    public static final String token = "token";
    String Token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        AndroidNetworking.initialize(getApplicationContext());

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        Token = sharedpreferences.getString(token,null);

        if (Token != null ){
            Intent i = new Intent(Log_In.this, Dashboard.class);
            startActivity(i);
            Log_In.this.finish();
        }



        Login = (Button)findViewById(R.id.btnLogin);
        Join = (Button)findViewById(R.id.btnJoin);

        editTextUsername = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Dashboard = new Intent(Log_In.this, create_account.class);
                startActivity(Dashboard);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLogin();

            }
        });
    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //Toast.makeText(getBaseContext().getApplicationContext(),username,Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext().getApplicationContext(),password,Toast.LENGTH_LONG).show();


        //validating inputs
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getBaseContext().getApplicationContext(),username,Toast.LENGTH_LONG).show();
            editTextUsername.setError("Please enter your email");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);



        AndroidNetworking.post("http://10.0.3.2/FBA/public/api/login")
                .addBodyParameter("email",editTextUsername.getText().toString() )
                .addBodyParameter("password",editTextPassword.getText().toString() )
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginReceived.class, new ParsedRequestListener<LoginReceived>() {

                    @Override
                    public void onResponse(LoginReceived received) {
                        // do anything with response
                        Log.d("response","Bearer" + received.success.token);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(token,received.success.token);
                        editor.commit();
                        Log.d("token",sharedpreferences.getString(token,null));
                        Toast.makeText(getBaseContext().getApplicationContext(),"Logged In",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(Log_In.this, Dashboard.class);
                        startActivity(i);
                        Log_In.this.finish();
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
                            editTextPassword.setError("Incorrect password");
                            editTextPassword.requestFocus();

                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("error", "onError errorDetail : " + error.getErrorDetail());
                            editTextPassword.setError("");
                            editTextPassword.requestFocus();
                            editTextUsername.setError("");
                            editTextUsername.requestFocus();
                        }
                    }



                });


    }



}

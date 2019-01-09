package com.code_africa.familyapp.familyapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Log_In extends AppCompatActivity {
    private Button Login,Join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        Login = (Button)findViewById(R.id.btnLogin);
        Join = (Button)findViewById(R.id.btnJoin);


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
                Intent Dashboard = new Intent(Log_In.this, Dashboard.class);
                startActivity(Dashboard);
            }
        });
    }
}
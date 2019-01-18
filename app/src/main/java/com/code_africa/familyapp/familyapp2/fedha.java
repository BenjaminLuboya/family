package com.code_africa.familyapp.familyapp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fedha extends AppCompatActivity {
private Button income,expenditure,reports,dashboardBtn, logout;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "familiabora_app_pref";
    public static final String token = "token";
    String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fedha);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        Token = sharedpreferences.getString(token, null);

        income = (Button)findViewById(R.id.btnincome);
        expenditure = (Button)findViewById(R.id.btnExpenditure);
        reports = (Button)findViewById(R.id.btnReports);
        dashboardBtn = (Button) findViewById(R.id.dashboard);
        logout = (Button) findViewById(R.id.logoutBtn);



        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(fedha.this, Dashboard.class);
                startActivity(i);
                fedha.this.finish();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove(token);
                editor.commit();
                Intent i = new Intent(fedha.this, Log_In.class);
                startActivity(i);
                fedha.this.finish();
            }
        });
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent income = new Intent (fedha.this,Income.class);
                startActivity(income);
            }
        });

        expenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expenditure = new Intent(fedha.this,Expenditure.class);
                startActivity(expenditure);
            }
        });


        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reports = new Intent(fedha.this,Reports.class);
                startActivity(reports);
            }
        });
    }
}

package com.code_africa.familyapp.familyapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fedha extends AppCompatActivity {
private Button income,expenditure,reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fedha);

        income = (Button)findViewById(R.id.btnincome);
        expenditure = (Button)findViewById(R.id.btnExpenditure);
        reports = (Button)findViewById(R.id.btnReports);



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

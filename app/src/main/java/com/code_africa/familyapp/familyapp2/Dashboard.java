package com.code_africa.familyapp.familyapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {
        private ImageView fedha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        fedha = (ImageView)findViewById(R.id.imgFedha);



        fedha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fedhaopen = new Intent(Dashboard.this,fedha.class);
                startActivity(fedhaopen);
            }
        });
    }
}

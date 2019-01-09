package com.code_africa.familyapp.familyapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class create_account extends AppCompatActivity {
private Button CreateLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        CreateLogin = (Button)findViewById(R.id.btnCreateLogin);

        CreateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoveToLogin = new Intent(create_account.this,Log_In.class);
                startActivity(MoveToLogin);
            }
        });
    }
}

package com.example.alex.oxylaundryonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private TextView tv_email;
    private FirebaseAuth auth;
    private Button btn_change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        tv_email = (TextView) findViewById(R.id.tv_emailSettings);
        btn_change = (Button) findViewById(R.id.btn_change);
        auth = FirebaseAuth.getInstance();
        tv_email.setText(auth.getCurrentUser().getEmail());
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, ChangeEmail.class));
            }
        });
    }
}

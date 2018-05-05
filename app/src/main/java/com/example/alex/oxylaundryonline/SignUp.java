package com.example.alex.oxylaundryonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText username;
    private EditText email, password, retypePassword, alamat, nomorHP;
    private CheckBox agreement;
    private Button submit;
    private TextView wrongPass;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        email = (EditText) findViewById(R.id.txt_email);
        password = (EditText) findViewById(R.id.txt_password);
        retypePassword = (EditText) findViewById(R.id.txt_retype_password);
        //username = (EditText) findViewById(R.id.txt_username);
        alamat = (EditText) findViewById(R.id.txt_alamat);
        nomorHP = (EditText) findViewById(R.id.txt_nomorHP);
        agreement = (CheckBox) findViewById(R.id.agreement);
        wrongPass = (TextView) findViewById(R.id.txt_wrong_pass);
        submit = (Button) findViewById(R.id.btn_submit);
        auth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(retypePassword.getText().toString()) && agreement.isChecked()) {

                    String emailString = email.getText().toString().trim();
                    String passwordString = password.getText().toString().trim();
                    Log.d("cekEmail",emailString);
                    auth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String retypePasswordString = retypePassword.getText().toString().trim();
                            //String usernameString = username.getText().toString().trim();
                            String alamatString = alamat.getText().toString().trim();
                            String nomorHPString = nomorHP.getText().toString().trim();

                            HashMap<String, String> dataMap = new HashMap<String, String>();
                            dataMap.put("retypePassword", retypePasswordString);
                            //dataMap.put("username", usernameString);
                            dataMap.put("alamat", alamatString);
                            dataMap.put("nomorHP", nomorHPString);
                            mDatabase.child("Users").child(auth.getCurrentUser().getUid().toString()).setValue(dataMap);
                            Toast.makeText(SignUp.this,"Akun berhasil dibuat, silakan sign in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,SignInActivity.class));
                        }
                    });

                } else {
                    wrongPass.setText("Password tidak cocok");
                }

            }
        });
    }


}

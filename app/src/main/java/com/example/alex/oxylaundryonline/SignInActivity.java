package com.example.alex.oxylaundryonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button signIn;
    private TextView message;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username = (EditText)findViewById(R.id.txtusername);
        password= (EditText)findViewById(R.id.txtpassword);
        signIn = (Button)findViewById(R.id.btn_signIn);
        message=(TextView)findViewById(R.id.message);
        auth = FirebaseAuth.getInstance();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate(username.getText().toString(), password.getText().toString());
                auth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(SignInActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        //Log.d("cek","success");

                    }
                });

            }
        });
    }


//
//    private void validate(String username, String password){
//        if ((username=="admin")&&(password=="1234")){
//            //Intent
//
//        } else {
//            message.setText("Username atau Password salah");
//        }
//    }


}

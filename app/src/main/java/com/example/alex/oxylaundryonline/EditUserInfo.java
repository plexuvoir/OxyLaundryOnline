package com.example.alex.oxylaundryonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditUserInfo extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private EditText et_namaEdt;
    private EditText et_phoneEdt;
    private EditText et_addrEdt;
    private Button btn_saveChg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();
        et_namaEdt = (EditText)findViewById(R.id.et_namaEdt);
        et_phoneEdt = (EditText)findViewById(R.id.et_namaEdt);
        et_addrEdt = (EditText)findViewById(R.id.et_addrEdt);
        btn_saveChg = (Button) findViewById(R.id.btn_saveChange);

        mDatabase.child(auth.getCurrentUser().getUid()).child("nama").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                et_namaEdt.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

    });

        mDatabase.child(auth.getCurrentUser().getUid()).child("nomorHP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                et_phoneEdt.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        mDatabase.child(auth.getCurrentUser().getUid()).child("alamat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                et_addrEdt.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        btn_saveChg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(auth.getCurrentUser().getUid()).child("nama").setValue(et_namaEdt.getText());
                mDatabase.child(auth.getCurrentUser().getUid()).child("nomorHP").setValue(et_phoneEdt.getText());
                mDatabase.child(auth.getCurrentUser().getUid()).child("alamat").setValue(et_addrEdt.getText());
                Toast.makeText(EditUserInfo.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditUserInfo.this, MainActivity.class));
                finish();
            }
        });



}

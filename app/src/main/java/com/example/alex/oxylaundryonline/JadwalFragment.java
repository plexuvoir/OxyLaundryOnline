package com.example.alex.oxylaundryonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JadwalFragment extends android.support.v4.app.Fragment {
    @Nullable
//    private CheckBox senin, selasa, rabu, kamis, jumat, sabtu, minggu;
    private Spinner spinner, spinner2;
    private TextView biaya;
    private Button bt_submit;
    int count=0;
    int quantity;
    String day;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    HashMap post = new HashMap();

    //    private int isCheckedGetCount(){
//        //count=0;
//        if (senin.isChecked()){
//            count++;
//        }
//        if (selasa.isChecked()){
//            count++;
//        }
//        if (rabu.isChecked()){
//            count++;
//        }
//        if (kamis.isChecked()){
//            count++;
//        }
//        if (jumat.isChecked()){
//            count++;
//        }
//        if (sabtu.isChecked()){
//            count++;
//        }
//        if (minggu.isChecked()){
//            count++;
//        }
//        return count;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View inflate =inflater.inflate(R.layout.fragment_jadwal,null);

//        senin=(CheckBox)inflate.findViewById(R.id.senin);
//        selasa=(CheckBox)inflate.findViewById(R.id.selasa);
//        rabu=(CheckBox)inflate.findViewById(R.id.rabu);
//        kamis=(CheckBox)inflate.findViewById(R.id.kamis);
//        jumat=(CheckBox)inflate.findViewById(R.id.jumat);
//        sabtu=(CheckBox)inflate.findViewById(R.id.sabtu);
//        minggu=(CheckBox)inflate.findViewById(R.id.minggu);
        biaya=(TextView)inflate.findViewById(R.id.txt_biaya);
        spinner=(Spinner)inflate.findViewById(R.id.spinner);
        spinner2=(Spinner)inflate.findViewById(R.id.spinner2);
        bt_submit = inflate.findViewById(R.id.bt_sub_jdw);



        String [] items=new String[]{"1 Kali","1 Bulan","2 Bulan", "3 Bulan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString().equalsIgnoreCase("1 Kali")){
                    quantity=1;

                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("1 Bulan")){
                    quantity=4;
                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("2 Bulan")){
                    quantity=8;
                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("3 Bulan")){
                    quantity=12;
                }

                biaya.setText("Rp "+String.valueOf(5000*quantity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String [] items2=new String[]{"Senin","Selasa","Rabu", "Kamis","Jumat","Sabtu","Minggu"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,items2);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (spinner2.getSelectedItem().toString()){
                case "Senin" :
                    day = "Mon";
                    break;
                case "Selasa" :
                    day = "Tue";
                    break;
                case  "Rabu" :
                    day = "Wed";
                    break;
                case "Kamis" :
                    day = "Thu";
                    break;
                case "Jumat" :
                    day = "Fri";
                    break;
                case "Sabtu" :
                    day = "Sat";
                    break;
                case "Minggu" :
                    day = "Sun";
                    break;
            }
                //biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPesanan = db.getReference("Pesanan").push().getKey();

                DatabaseReference ref = db.getReference("Pesanan").child(idPesanan);
                if (quantity == 1){
                    for (int i = 0; i < 7; i++){
                        Calendar d = Calendar.getInstance();
                        d.add(Calendar.DATE, i);
                        String day1 = (String) DateFormat.format("EEE", d);
                        if (day1.equalsIgnoreCase(day)){
                            String date = spinner2.getSelectedItem().toString()+", "+(String) DateFormat.format("dd-MMMM-yyyy", d);
                            Log.d("alex", date);
//                            post.put("UID", mAuth.getCurrentUser().getUid());
//                            post.put("Jenis", spinner.getSelectedItem().toString());
                            post.put(Integer.toString(count)+"-day", date);


                        }

                    }
                }
                else if (quantity == 4){
                    int count = 0;
                    for (int i = 0; i < 28; i++){
                        Calendar d = Calendar.getInstance();
                        d.add(Calendar.DATE, i);
                        String day1 = (String) DateFormat.format("EEE", d);

                        if (day1.equalsIgnoreCase(day)) {
                            count++;
                            //String[] days = new String[4];
                            String date =  spinner2.getSelectedItem().toString()+", "+(String) DateFormat.format("dd-MMMM-yyyy", d);
                            post.put(Integer.toString(count)+"-day", date);
                        }
                    }
                    count = 0;
                }
                else if (quantity == 8){
                    int count = 0;
                    for (int i = 0; i < 56; i++){
                        Calendar d = Calendar.getInstance();
                        d.add(Calendar.DATE, i);
                        String day1 = (String) DateFormat.format("EEE", d);

                        if (day1.equalsIgnoreCase(day)) {
                            count++;
                            //String[] days = new String[4];
                            String date =  spinner2.getSelectedItem().toString()+", "+(String) DateFormat.format("dd-MMMM-yyyy", d);
                            post.put(Integer.toString(count)+"-day", date);
                        }
                    }
                    count = 0;

                }
                else if (quantity == 12){
                    int count = 0;
                    for (int i = 0; i < 84; i++){
                        Calendar d = Calendar.getInstance();
                        d.add(Calendar.DATE, i);
                        String day1 = (String) DateFormat.format("EEE", d);

                        if (day1.equalsIgnoreCase(day)) {
                            count++;
                            //String[] days = new String[4];
                            if (count <= 9){
                                String date =  spinner2.getSelectedItem().toString()+", "+(String) DateFormat.format("dd-MMMM-yyyy", d);
                                post.put("0"+Integer.toString(count)+"-day", date);
                            }
                            else {
                                String date =  spinner2.getSelectedItem().toString()+", "+(String) DateFormat.format("dd-MMMM-yyyy", d);
                                post.put(Integer.toString(count)+"-day", date);
                            }

                        }
                    }
                    count = 0;

                }
                post.put("UID", mAuth.getCurrentUser().getUid());
                post.put("Jenis", spinner.getSelectedItem().toString());
                ref.setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(inflate, "Order Berhasil Ditambahkan", Snackbar.LENGTH_LONG).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                               // Intent i=new Intent(getContext(), ((MainActivity)getActivity()).loadFragment(AktivitasFragment.class));
                                                //startActivity(i);
                                }
                            }, 1000);

                        }

                        else{
                            Snackbar.make(inflate, "FAILED", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });;
            }
        });

//        senin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        selasa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        rabu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        kamis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        jumat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        sabtu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        minggu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//                count=0;
//            }
//        });
//        senin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });
//        selasa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });
//        rabu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });
//        kamis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });
//        jumat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });
//        sabtu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });
//        minggu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000));
//            }
//        });


        return inflate;

    }

}


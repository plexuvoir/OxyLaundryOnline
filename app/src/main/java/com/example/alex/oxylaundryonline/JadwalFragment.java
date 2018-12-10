package com.example.alex.oxylaundryonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JadwalFragment extends android.support.v4.app.Fragment {
    @Nullable
//    private CheckBox senin, selasa, rabu, kamis, jumat, sabtu, minggu;
    private Spinner spinner, spinner2;
    private TextView biaya, txt_keterangan_promo;
    private Button bt_submit, bt_cek_promo;
    private EditText input_promo;
    int count=0;
    int quantity;
    String day;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    HashMap post = new HashMap();
    HashMap post1 = new HashMap();


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

        ((MainActivity)getActivity()).setActionBarTitle("Jadwal");


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
        bt_cek_promo = inflate.findViewById(R.id.btn_cek_promo);
        txt_keterangan_promo = inflate.findViewById(R.id.txt_keterangan_promo);
        input_promo = inflate.findViewById(R.id.input_promo);


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

                if (quantity == 1){
                    int count = 0;
                    for (int i = 0; i < 7; i++){
                        Calendar d = Calendar.getInstance();
                        d.add(Calendar.DATE, i);
                        String day1 = (String) DateFormat.format("EEE", d);
                        if (day1.equalsIgnoreCase(day)) {
                            count++;
                            String date = (String) DateFormat.format("dd-MMMM-yyyy", d);
                            Log.d("alex", date);
//                            post.put("UID", mAuth.getCurrentUser().getUid());
//                            post.put("Jenis", spinner.getSelectedItem().toString());
                            post1.put("0" + Integer.toString(count) + "-day", date);
                        }
                    }
                   // count = 0;

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
                            String date = (String) DateFormat.format("dd-MMMM-yyyy", d);
                            post1.put("0"+Integer.toString(count)+"-day", date);
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
                            String date =  (String) DateFormat.format("dd-MMMM-yyyy", d);
                            post1.put("0"+Integer.toString(count)+"-day", date);
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
                                String date = (String) DateFormat.format("dd-MMMM-yyyy", d);
                                post1.put("0"+Integer.toString(count)+"-day", date);
                            }
                            else {
                                String date = (String) DateFormat.format("dd-MMMM-yyyy", d);
                                post1.put(Integer.toString(count)+"-day", date);
                            }

                        }
                    }
                    count = 0;

                }
                post.put("UID", mAuth.getCurrentUser().getUid());
                post.put("Jenis", spinner.getSelectedItem().toString());
                post.put("Hari", spinner2.getSelectedItem().toString());
                post.put("Kode_Promo", input_promo.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Buat Pesanan")
                        .setMessage("Apakah Anda yakin akan memesan laundry? Pesanan tidak dapat dibatalkan")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String idPesanan = db.getReference("Pesanan").push().getKey();
                                DatabaseReference ref = db.getReference("Pesanan").child(idPesanan);
                                DatabaseReference ref2 = db.getReference("Pesanan").child(idPesanan).child("Tanggal");
                                ref.setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Snackbar.make(inflate, "Pesanan Berhasil Dibuat", Snackbar.LENGTH_LONG).show();

                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

//                                                     Intent i=new Intent(getContext(),((MainActivity)getActivity()).loadFragment(new AktivitasFragment()));
//                                                     );
//                                                    startActivity(i);
                                                    ((MainActivity)getActivity()).loadFragment(new AktivitasFragment());
                                                    ((MainActivity)getActivity()).btmNav.setSelectedItemId(R.id.navigation_aktivitas);

                                                }
                                            }, 1000);

                                        }

                                        else{
                                            Snackbar.make(inflate, "Pesanan Dibatalkan", Snackbar.LENGTH_LONG).show();
                                        }
                                    }
                                });;
                                ref2.setValue(post1);



                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }


        });

        //final String [] kode_promo = new String[]{"BXYWZA","JKLUIO","OAGFXZ"};
        bt_cek_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                for (int i = 0; i < kode_promo.length; i++) {
//                    if (kode_promo_input.equalsIgnoreCase(kode_promo[i])){
//                        txt_keterangan_promo.setText("Anda akan mendapatkan potongan harga sebesar 20%");
//                    }
//                }
                String kode_promo_input = input_promo.getText().toString();
                if (kode_promo_input.equalsIgnoreCase("AKHIRBULANMALESNYUCI")){
                    txt_keterangan_promo.setText("Anda akan mendapatkan potongan harga sebesar 20%");
                } else if (kode_promo_input.equalsIgnoreCase("TENGAHBULANASIK")){
                    txt_keterangan_promo.setText("Anda akan mendapatkan potongan harga sebesar 10%");
                } else if (kode_promo_input.equalsIgnoreCase("GRATISONGKIR")){
                    txt_keterangan_promo.setText("Anda akan mendapatkan gratis ongkos antar jemput");
                } else {
                    Toast.makeText(getContext(),"Kode Promo Tidak Sesuai",Toast.LENGTH_SHORT).show();
                }
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


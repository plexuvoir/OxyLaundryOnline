package com.example.alex.oxylaundryonline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class JadwalFragment extends android.support.v4.app.Fragment {
    @Nullable
//    private CheckBox senin, selasa, rabu, kamis, jumat, sabtu, minggu;
    private Spinner spinner, spinner2;
    private TextView biaya;
    int count=0;
    int quantity;
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

        View inflate =inflater.inflate(R.layout.fragment_jadwal,null);

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
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (spinner.getSelectedItem().toString().equalsIgnoreCase("mingguan")){
//                    //quantity=1;
//
//                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("bulanan")){
//                    //quantity=4;
//                }
//                biaya.setText("Rp "+String.valueOf(isCheckedGetCount()*10000*quantity));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

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


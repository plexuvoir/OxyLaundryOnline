package com.example.alex.oxylaundryonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class AktivitasFragment extends android.support.v4.app.Fragment {

    public List<List_Item_Aktivitas> listItems = new ArrayList<List_Item_Aktivitas>();
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    ArrayList<String> tanggalSet;
    HashMap<String, String> pesanan = new HashMap<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View inflate =inflater.inflate(R.layout.fragment_aktivitas,null);
        ((MainActivity)getActivity()).setActionBarTitle("Aktivitas");
        recyclerView = inflate.findViewById(R.id.recV_aktv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        listItems = new ArrayList<List_Item_Aktivitas>();


      db.getReference("Pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    String uid = sn.child("UID").getValue(String.class);
                    //retrieveTanggal("-LPHeF8T0evlSZofaFRa");
                    if (uid.equalsIgnoreCase(auth.getCurrentUser().getUid())) {
                        String tglAwal = sn.child("Tanggal").child("01-day").getValue(String.class);
                        String tglAkhir;
                        String jenis = sn.child("Jenis").getValue(String.class);
                        if (jenis.equals("1 Bulan")) {
                            tglAkhir = sn.child("Tanggal").child("04-day").getValue(String.class);
                        } else if (jenis.equals("2 Bulan")) {
                            tglAkhir = sn.child("Tanggal").child("08-day").getValue(String.class);
                        } else if (jenis.equals("3 Bulan")) {
                            tglAkhir = sn.child("Tanggal").child("12-day").getValue(String.class);
                        } else {
                            tglAkhir = "";
                        }

                        String next = "";
                        Date today = Calendar.getInstance().getTime();
                        Date date = null;
                        DataSnapshot dataSnapshot1 = sn.child("Tanggal");
                        for (DataSnapshot sn1 : dataSnapshot1.getChildren()) {
                            String strDate = sn1.getValue(String.class);
                            //Date date;
                            SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH);
                            try {
                                date = format.parse(strDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if (date.after(today)) {
                                next = (String) DateFormat.format("dd-MMMM-yyyy", date);
                                break;
                            }
                        }
                        Log.d("TES", next);
                        //String today = (String) DateFormat.format("EEE", d);
                        listItems.add(new List_Item_Aktivitas(sn.getKey(), jenis,
                                tglAwal, tglAkhir, next));

                    }
                    // if (sn.child("UID").getValue(String.class).equals("QvDBRRVdmNOTKWx7N15PWDPvzOB2")){

                    // }
                }

                adapter = new aktivitasAdapter(listItems, getContext(), new aktivitasAdapter.OnItemClicked() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        db.getReference("Pesanan").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                listItems.clear();
//                for (DataSnapshot sn : dataSnapshot.getChildren()){
//                    if (sn.child("UID").getValue(String.class).equalsIgnoreCase("QvDBRRVdmNOTKWx7N15PWDPvzOB2")){
//                        String noOrder = sn.getKey();
//                        String jenis = sn.child("Jenis").getValue(String.class);
//                        String tglAwal = tanggalSet.get(0);
//                        String tglAkhir = tanggalSet.get(tanggalSet.size()-1);
//
//                        listItems.add(new List_Item_Aktivitas(noOrder, jenis, tglAwal, tglAkhir, "asal"));
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });





//

        return inflate;
    }

}


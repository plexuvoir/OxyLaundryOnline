package com.example.alex.oxylaundryonline;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PromoFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "PromoFragment";
    public List<List_Item_Promo> listItems = new ArrayList<List_Item_Promo>();
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private TextView kode_promo, textview2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View inflate =inflater.inflate(R.layout.fragment_promo,null);

        ((MainActivity)getActivity()).setActionBarTitle("Promo");
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recV_promo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems.add(new List_Item_Promo("AKHIRBULANMALESNYUCI", R.drawable.poster_1));

        listItems.add(new List_Item_Promo("TENGAHBULANASIK", R.drawable.poster_2));

        listItems.add(new List_Item_Promo("GRATISONGKIR", R.drawable.poster_3));


        adapter = new promoAdapter(listItems, getActivity(), new promoAdapter.OnItemClicked() {
            @Override
            public void onItemClick(int position) {
//                ClipboardManager clipboard = (ClipboardManager) inflate.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("kode_promo", "tes");
//                clipboard.setPrimaryClip(clip);
//                Toast.makeText(inflate.getContext(),"Kode Promo Berhasil Disalin",Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return inflate;


    }


}


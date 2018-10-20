package com.example.alex.oxylaundryonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AktivitasFragment extends android.support.v4.app.Fragment {

    public List<List_Item_Promo> listItems = new ArrayList<List_Item_Promo>();
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View inflate =inflater.inflate(R.layout.fragment_aktivitas,null);

        ((MainActivity)getActivity()).setActionBarTitle("Aktivitas");
//

        return inflate;
    }

}


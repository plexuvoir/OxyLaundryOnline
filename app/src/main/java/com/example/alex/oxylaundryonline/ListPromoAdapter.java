package com.example.alex.oxylaundryonline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Alex on 10/17/2018.
 */

public class ListPromoAdapter extends RecyclerView.Adapter<ListPromoAdapter.ViewHolder> {
    private static final String TAG = "ListPromoAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public ListPromoAdapter(ArrayList<String> imageNames, ArrayList<String> images, Context context) {
        this.mImageNames = imageNames;
        this.mImages = images;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_promo, parent, false);
        ViewHolder  holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.img_promo);
        holder.txt_promo.setText(mImageNames.get(position));
        holder.btn_salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Kode Promo Berhasil Disalin",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_promo;
        TextView txt_promo;
        Button btn_salin;

        public ViewHolder(View itemView) {
            super(itemView);
            img_promo = itemView.findViewById(R.id.img_promo);
            txt_promo = itemView.findViewById(R.id.txt_promo);
            btn_salin = itemView.findViewById(R.id.btn_salin);
        }
    }
}

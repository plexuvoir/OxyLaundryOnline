package com.example.alex.oxylaundryonline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class promoAdapter extends RecyclerView.Adapter<promoAdapter.ViewHolder> {

    private List<List_Item_Promo> listItems;
    private Context context;
    private OnItemClicked mListener;

    public promoAdapter(List<List_Item_Promo> listItems, Context context, OnItemClicked listener) {
        this.listItems = listItems;
        this.context = context;
        this.mListener = listener;

    }

    @Override
    public promoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_promo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(promoAdapter.ViewHolder holder, final int position) {
        //binding value dari list item ke holder
        List_Item_Promo listItem = listItems.get(position);
        holder.kdPromo.setText(listItem.getKdPromo());
        holder.btSalin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);
            }
        });
        //Picasso.get().load(listItem.getImgSrc()).into(holder.avatar);
        Glide.with(context)
                .load(listItem.getImgSrc())
                .into(holder.imgPromo);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView kdPromo;
        public ImageView imgPromo;
        public Button btSalin;

        public ViewHolder(View itemView) {
            super(itemView);

            kdPromo = itemView.findViewById(R.id.txt_promo);
            imgPromo = itemView.findViewById(R.id.img_promo);
            btSalin = itemView.findViewById(R.id.btn_salin);


        }
    }
    public interface OnItemClicked {
        void onItemClick(int position);
    }
}




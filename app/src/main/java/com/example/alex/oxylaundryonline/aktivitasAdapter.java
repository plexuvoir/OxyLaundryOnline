package com.example.alex.oxylaundryonline;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class aktivitasAdapter extends RecyclerView.Adapter<aktivitasAdapter.ViewHolder> {

    private List<List_Item_Aktivitas> listItems;
    private Context context;
    private OnItemClicked mListener;

    public aktivitasAdapter(List<List_Item_Aktivitas> listItems, Context context, OnItemClicked listener) {
        this.listItems = listItems;
        this.context = context;
        this.mListener = listener;

    }

    @Override
    public aktivitasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final aktivitasAdapter.ViewHolder holder, final int position) {
        //binding value dari list item ke holder
        List_Item_Aktivitas listItem = listItems.get(position);
        holder.noOrder.setText(listItem.getNoOrder());
        holder.paket.setText(listItem.getJenis());
        holder.tglAwal.setText(listItem.getTglAwal());
        holder.tglAkhir.setText(listItem.getTglAkhir());
        holder.next.setText(listItem.getNext());
//        holder.kdPromo.setText(listItem.getKdPromo());
//        holder.btSalin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("kode_promo",holder.kdPromo.getText().toString());
//                clipboard.setPrimaryClip(clip);
//                Toast.makeText(context,"Kode Promo Berhasil Disalin",Toast.LENGTH_SHORT).show();
//                mListener.onItemClick(position);
//            }
//        });
        //Picasso.get().load(listItem.getImgSrc()).into(holder.avatar);
//        Glide.with(context)
//                .load(listItem.getImgSrc())
//                .into(holder.imgPromo);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView noOrder, paket, tglAwal, tglAkhir, next;

        public ViewHolder(View itemView) {
            super(itemView);

            noOrder = itemView.findViewById(R.id.tv_noOrder);
            paket = itemView.findViewById(R.id.tv_jenisPaket);
            tglAwal = itemView.findViewById(R.id.tv_tglAwal);
            tglAkhir = itemView.findViewById(R.id.tv_tglAkhir);
            next = itemView.findViewById(R.id.tv_nextPick);


        }
    }
    public interface OnItemClicked {
        void onItemClick(int position);
    }
}




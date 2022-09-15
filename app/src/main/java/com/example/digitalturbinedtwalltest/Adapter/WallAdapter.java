package com.example.digitalturbinedtwalltest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digitalturbinedtwalltest.Model.Offer;
import com.example.digitalturbinedtwalltest.R;

import java.util.ArrayList;
import java.util.List;

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.MyViewholder> {

    private final Context context;
    private List<Offer> Offers = new ArrayList<>();

    public WallAdapter(Context context, List<Offer> Offers) {
        this.context = context;
        this.Offers = Offers;
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dt, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Offer offer = Offers.get(position);
        String title = offer.getTitle();
        //OfferType offerType = offer.getOfferTypes();
//        String uri = offer.getThumbnail().getHires();
        String uri = offer.getThumbnail().getHires();
        //String uri = "https://source.unsplash.com/user/c_v_r/1900x800";
        holder.title.setText(title);
        if (uri != null) {
            Glide.with(context)
                    .load(uri)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return Offers.size() - 1;
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.getRootView().findViewById(R.id.title_object);
            imageView = itemView.getRootView().findViewById(R.id.image_object);
        }
    }
}

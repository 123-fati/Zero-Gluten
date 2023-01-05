package com.example.zerogluten;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList product_id, product_name, product_description, product_price, product_poid, product_category;
    private ArrayList product_photo;

    CustomAdapter(Activity activity, Context context, ArrayList product_id,  ArrayList product_name, ArrayList product_description, ArrayList product_price,
                  ArrayList product_poid, ArrayList product_category, ArrayList<Bitmap> product_photo){
        this.activity = activity;
        this.context = context;
        this.product_description = product_description;
        this.product_name = product_name;
        this.product_poid = product_poid;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_id = product_id;
        this.product_photo = product_photo;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        //holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        //holder.product_id_txt.setImageBitmap(BitmapFactory.decodeByteArray(product_photo.get(position), 0, product_photo.get(position).length));
        holder.product_id_txt.setImageBitmap((Bitmap) product_photo.get(position));
        holder.product_name_txt.setText(String.valueOf(product_name.get(position)));
        holder.product_description_txt.setText(String.valueOf(product_description.get(position)));
        holder.product_category_txt.setText(String.valueOf(product_category.get(position)));
        holder.product_price_txt.setText(String.valueOf(product_price.get(position)));
        holder.product_poid_txt.setText(String.valueOf(product_poid.get(position)));

    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  product_description_txt, product_name_txt, product_price_txt, product_poid_txt, product_category_txt;
        LinearLayout mainLayout;
        ImageView product_id_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_description_txt = itemView.findViewById(R.id.product_description_txt);
            product_price_txt = itemView.findViewById(R.id.product_price_txt);
            product_name_txt = itemView.findViewById(R.id.product_name_txt);
            product_poid_txt = itemView.findViewById(R.id.product_poid_txt);
            product_category_txt = itemView.findViewById(R.id.product_category_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }


}

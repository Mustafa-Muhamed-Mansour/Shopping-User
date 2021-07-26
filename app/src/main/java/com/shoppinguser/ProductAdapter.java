package com.shoppinguser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{

    ArrayList<ProductModel> productModels;

    public ProductAdapter(ArrayList<ProductModel> productModels)
    {
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position)
    {
        ProductModel model = productModels.get(position);

        holder.textProductName.setText(model.getCategory_name());
        holder.textProductPrice.setText(model.getCategory_price() + " £");
        Glide
                .with(holder.itemView.getContext())
                .load(model.getCategory_image())
                .into(holder.imageProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle sendData = new Bundle();
                sendData.putString("Id", model.getCategory_id());
                sendData.putString("product_image", model.getCategory_image());
                sendData.putString("product_name", model.getCategory_name());
                sendData.putString("product_price", model.getCategory_price());
                sendData.putString("product_description", model.getCategory_description());
                sendData.putString("choose_status", model.getChoose_status());
                sendData.putString("number_quantity", model.getNumber_quantity());
                Navigation.findNavController(v).navigate(R.id.action_productFragment_to_detailsProductFragment, sendData);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return productModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView imageProduct;
        TextView textProductName, textProductPrice;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);

            imageProduct = itemView.findViewById(R.id.item_img_product);
            textProductName = itemView.findViewById(R.id.item_name_product);
            textProductPrice = itemView.findViewById(R.id.item_price_product);
        }
    }
}

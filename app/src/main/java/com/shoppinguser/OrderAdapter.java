package com.shoppinguser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>
{
    NestedScrollView parentNestedScroll;

    public static int totalPrice = 0, productTotalPrice;

    ArrayList<OrderModel> orderModels;

    public OrderAdapter(ArrayList<OrderModel> orderModels)
    {
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        parentNestedScroll = view.findViewById(R.id.parent_nested);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position)
    {
        OrderModel model = orderModels.get(position);

        holder.textName.setText(model.getCategory_name());
        holder.textPrice.setText(model.getCategory_price() + " £");
        holder.textQuantity.setText(model.getNumber_quantity());
        Glide
                .with(holder.itemView.getContext())
                .load(model.getCategory_image())
                .into(holder.circleImage);
        holder.textDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(parentNestedScroll, "Comming Soon", Snackbar.LENGTH_SHORT).show();
            }
        });

        productTotalPrice = ((Integer.valueOf(model.getCategory_price())) * (Integer.valueOf(model.getNumber_quantity())));
//        productTotalPrice = ((Integer.parseInt(model.getCategory_price())) * (Integer.parseInt(model.getNumber_quantity())));
        totalPrice = totalPrice + productTotalPrice;
    }


    @Override
    public int getItemCount()
    {
        return orderModels.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView circleImage;
        TextView textName, textPrice, textQuantity, textDelete;

        public OrderViewHolder(@NonNull View itemView)
        {
            super(itemView);

            circleImage = itemView.findViewById(R.id.item_circle_img_product);
            textName = itemView.findViewById(R.id.item_txt_product_name);
            textPrice = itemView.findViewById(R.id.item_txt_product_price);
            textQuantity = itemView.findViewById(R.id.item_txt_product_quantity);
            textDelete = itemView.findViewById(R.id.item_txt_delete);
        }
    }
}

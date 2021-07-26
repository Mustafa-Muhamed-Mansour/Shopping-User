package com.shoppinguser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.polak.clicknumberpicker.ClickNumberPickerView;
import ren.qinc.numberbutton.NumberButton;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>
{

    ArrayList<HomeModel> homeModels;

    public HomeAdapter(ArrayList<HomeModel> homeModels)
    {
        this.homeModels = homeModels;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position)
    {
        HomeModel model = homeModels.get(position);

        holder.textHomeName.setText(model.getCategory_name());
        holder.textHomePrice.setText(model.getCategory_price() + " £");
        Glide
                .with(holder.itemView.getContext())
                .load(model.getCategory_image())
                .into(holder.homeImage);
    }

    @Override
    public int getItemCount()
    {
        return homeModels.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView homeImage;
        TextView textHomeName, textHomePrice;
        ClickNumberPickerView numberButton;

        public HomeViewHolder(@NonNull View itemView)
        {
            super(itemView);

            homeImage = itemView.findViewById(R.id.item_img_home);
            textHomeName = itemView.findViewById(R.id.item_name_home);
            textHomePrice = itemView.findViewById(R.id.item_price_home);
            numberButton = itemView.findViewById(R.id.item_number_picker_home);
        }
    }
}

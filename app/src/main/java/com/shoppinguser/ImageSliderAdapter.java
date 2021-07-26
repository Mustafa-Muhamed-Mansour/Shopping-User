package com.shoppinguser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.ImageSliderViewHolder>
{

    Context context;
    ArrayList<SliderModel> sliderModels;

    public ImageSliderAdapter(Context context, ArrayList<SliderModel> sliderModels)
    {
        this.context = context;
        this.sliderModels = sliderModels;
    }

    @Override
    public ImageSliderViewHolder onCreateViewHolder(ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, parent, false);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageSliderViewHolder viewHolder, int position)
    {
       SliderModel model = sliderModels.get(position);

        Glide
                .with(context)
                .load(model.getImage())
                .into(viewHolder.imageSlider);
    }

    @Override
    public int getCount()
    {
        return sliderModels.size();
    }

    public class ImageSliderViewHolder extends SliderViewAdapter.ViewHolder
    {
        ImageView imageSlider;

        public ImageSliderViewHolder(View itemView)
        {
            super(itemView);

            imageSlider = itemView.findViewById(R.id.item_img_slider);
        }
    }
}

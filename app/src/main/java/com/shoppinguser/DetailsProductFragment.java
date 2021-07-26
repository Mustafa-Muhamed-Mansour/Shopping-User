package com.shoppinguser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ren.qinc.numberbutton.NumberButton;

public class DetailsProductFragment extends Fragment
{

    View root;
    NavController navController;
    ImageView imageProduct, imageLoveProduct;
    TextView textProductPrice, textProductName, textProductStatus, textProductNumberQuantity, textProductDescription;
    NumberButton buttonNumberQuantity;
    Button buttonAddToCart;
    FirebaseAuth firebaseAuth;
    DatabaseReference dataReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_details_product, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        firebaseAuth = FirebaseAuth.getInstance();
        dataReference = FirebaseDatabase.getInstance().getReference();
        imageProduct = root.findViewById(R.id.img_product);
        imageLoveProduct = root.findViewById(R.id.img_love_product);
        textProductPrice = root.findViewById(R.id.txt_price_product);
        textProductName = root.findViewById(R.id.txt_name_product);
        textProductStatus = root.findViewById(R.id.txt_status_product);
        textProductNumberQuantity = root.findViewById(R.id.txt_number_quantity);
        textProductDescription = root.findViewById(R.id.txt_description_product);
        buttonNumberQuantity = root.findViewById(R.id.btn_number);
        buttonAddToCart = root.findViewById(R.id.btn_add_to_cart);

        String ID = getArguments().getString("Id");
        String imgProduct = getArguments().getString("product_image");
        String nameProduct = getArguments().getString("product_name");
        String priceProduct = getArguments().getString("product_price");
        String statusProduct = getArguments().getString("choose_status");
        String numberQuantityProduct = getArguments().getString("number_quantity");
        String descriptionProduct = getArguments().getString("product_description");

        Glide
                .with(root.getContext())
                .load(imgProduct)
                .into(imageProduct);
        textProductPrice.setText(priceProduct + " £");
        textProductName.setText(nameProduct);
        textProductStatus.setText(statusProduct);
        textProductNumberQuantity.setText(numberQuantityProduct);
        textProductDescription.setText(descriptionProduct);

        buttonNumberQuantity.setCurrentNumber(1);

        buttonAddToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Category_ID = dataReference.push().getKey();
                ProductModel productModel = new ProductModel(Category_ID, imgProduct, nameProduct, priceProduct, statusProduct, String.valueOf(buttonNumberQuantity.getNumber()), descriptionProduct);
                dataReference.child("Cart").child(firebaseAuth.getCurrentUser().getUid()).child(ID).setValue(productModel);
                Toast.makeText(root.getContext(), "Added to your order", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_detailsProductFragment_to_homeFragment);
            }
        });
    }

}
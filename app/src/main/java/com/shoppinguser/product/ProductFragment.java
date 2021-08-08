package com.shoppinguser.product;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shoppinguser.R;
import com.shoppinguser.adapter.ProductAdapter;
import com.shoppinguser.model.ProductModel;

import java.util.ArrayList;

public class ProductFragment extends Fragment
{

    View root;
    NavController navController;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<ProductModel> productModels;
    DatabaseReference retriveReference;
    String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_product, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        category = ProductFragmentArgs.fromBundle(requireArguments()).getCategory();
        recyclerView = root.findViewById(R.id.r_v);
        productModels = new ArrayList<>();
        productAdapter = new ProductAdapter(productModels);
        productModels.clear();
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        retriveReference = FirebaseDatabase.getInstance().getReference().child("Categories").child(category);
        retriveReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                productModels.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ProductModel productModel = dataSnapshot.getValue(ProductModel.class);
                    productModels.add(productModel);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
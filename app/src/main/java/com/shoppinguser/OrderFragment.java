package com.shoppinguser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderFragment extends Fragment
{

    View root;
    NavController navController;
    RecyclerView recyclerView;
    Button buttonDone;
    ArrayList<OrderModel> orderModels;
    OrderAdapter orderAdapter;
    FirebaseAuth firebaseAuth;
    DatabaseReference retriveReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_order, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        recyclerView = root.findViewById(R.id.r_v_order);
        buttonDone = root.findViewById(R.id.btn_done);
        orderModels = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderModels);
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        firebaseAuth = FirebaseAuth.getInstance();
        retriveReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(firebaseAuth.getCurrentUser().getUid());
        retriveReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                orderModels.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                    orderModels.add(orderModel);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(root.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundlePrice = new Bundle();
                bundlePrice.putString("total_price", String.valueOf(OrderAdapter.totalPrice));
                navController.navigate(R.id.action_orderFragment_to_confirmFragment, bundlePrice);
            }
        });
    }
}
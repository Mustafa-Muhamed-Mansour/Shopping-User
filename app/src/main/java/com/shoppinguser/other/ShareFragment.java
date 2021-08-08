package com.shoppinguser.other;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shoppinguser.R;

public class ShareFragment extends Fragment
{

    View root;
    NavController navController;
    TextView textShareApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_share, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        textShareApplication = root.findViewById(R.id.txt_share);
        textShareApplication.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent actionIntent = new Intent(Intent.ACTION_SEND);
                actionIntent.setType("text/plain");
                String linkApp = "Download this Application:- https://www.google.com/";
                actionIntent.putExtra(Intent.EXTRA_TEXT, linkApp);
                getActivity().startActivity(Intent.createChooser(actionIntent, "Share Using"));
            }
        });
    }

}
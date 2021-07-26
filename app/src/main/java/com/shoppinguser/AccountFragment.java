package com.shoppinguser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AccountFragment extends Fragment
{

    View root;
    NavController navController;
    LinearLayout linearLayoutGift, linearLayoutHelp, linearLayoutShare, linearLayoutCondition, linearLayoutPolicy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_account, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        linearLayoutGift = root.findViewById(R.id.linear_gift);
        linearLayoutHelp = root.findViewById(R.id.linear_help);
        linearLayoutShare = root.findViewById(R.id.linear_share);
        linearLayoutCondition = root.findViewById(R.id.linear_condition);
        linearLayoutPolicy = root.findViewById(R.id.linear_policy);

        linearLayoutGift.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_accountFragment_to_giftFragment);
            }
        });

        linearLayoutHelp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_accountFragment_to_helpFragment);
            }
        });

        linearLayoutShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_accountFragment_to_shareFragment);
            }
        });

        linearLayoutCondition.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_accountFragment_to_termAndConditionFragment);
            }
        });

        linearLayoutPolicy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_accountFragment_to_privacyPolicyFragment);
            }
        });
    }

}
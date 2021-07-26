package com.shoppinguser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment
{

    View root;
    NavController navController;
    TimerTask timerTask;

    FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_splash, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart()
    {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null)
        {
            navController.navigate(R.id.action_splashFragment_to_homeFragment);
        }
        else
        {
            timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    navController.navigate(R.id.action_splashFragment_to_phoneAuthenticationFragment);
                }
            };
            new Timer().schedule(timerTask, 2000);
        }
    }
}
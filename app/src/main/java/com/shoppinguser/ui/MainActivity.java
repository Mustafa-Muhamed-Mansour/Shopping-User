package com.shoppinguser.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shoppinguser.R;

public class MainActivity extends AppCompatActivity
{

    NavController navController;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                switch (destination.getId())
                {
                    case R.id.splashFragment:
                    case R.id.phoneAuthenticationFragment:
                    case R.id.sucessFragment:
                    case R.id.detailsProductFragment:
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    default:
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

}
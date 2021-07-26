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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{

    View root;
    NavController navController;
    SliderView sliderViewHome, sliderViewChildreen, sliderViewRequirement;
    ArrayList<SliderModel> sliderModelsHome, sliderModelsChildreen, sliderModelsRequirement;
    ImageSliderAdapter imageSliderAdapter;
    BottomNavigationView bottomNavigationView;
//    FloatingActionButton floatingActionButton;
    Button buttonAllCategory, buttonAllSelling;
    LinearLayout linearLayoutMilkAndEge, linearLayoutMeat, linearLayoutVegetable, linearLayoutBakery;
    LinearLayout linearLayoutFood, linearLayoutRequirement, linearLayoutDrink, linearLayoutChocolate;
    RecyclerView recyclerView;
    ArrayList<HomeModel> homeModels;
    HomeAdapter homeAdapter;
    DatabaseReference retriveReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        sliderViewHome = root.findViewById(R.id.img_slider_view_home);
        sliderModelsHome = new ArrayList<>();
        sliderModelsHome.add(new SliderModel(R.drawable.banner_one));
        sliderModelsHome.add(new SliderModel(R.drawable.banner_two));
        sliderModelsHome.add(new SliderModel(R.drawable.banner_three));
        sliderModelsHome.add(new SliderModel(R.drawable.banner_four));
        imageSliderAdapter = new ImageSliderAdapter(getContext(), sliderModelsHome);
        sliderViewHome.setSliderAdapter(imageSliderAdapter);

        sliderViewChildreen = root.findViewById(R.id.img_slider_view_childreen);
        sliderModelsChildreen = new ArrayList<>();
        sliderModelsChildreen.add(new SliderModel(R.drawable.banner_one_children));
        sliderModelsChildreen.add(new SliderModel(R.drawable.banner_two_children));
        sliderModelsChildreen.add(new SliderModel(R.drawable.banner_three_children));
        sliderModelsChildreen.add(new SliderModel(R.drawable.banner_four_children));
        imageSliderAdapter = new ImageSliderAdapter(getContext(), sliderModelsChildreen);
        sliderViewChildreen.setSliderAdapter(imageSliderAdapter);

        sliderViewRequirement = root.findViewById(R.id.img_slider_view_requirement);
        sliderModelsRequirement = new ArrayList<>();
        sliderModelsRequirement.add(new SliderModel(R.drawable.banner_one_home));
        sliderModelsRequirement.add(new SliderModel(R.drawable.banner_two_home));
        sliderModelsRequirement.add(new SliderModel(R.drawable.banner_three_home));
        imageSliderAdapter = new ImageSliderAdapter(getContext(), sliderModelsRequirement);
        sliderViewRequirement.setSliderAdapter(imageSliderAdapter);

        bottomNavigationView = root.findViewById(R.id.bottom_nav_view);
//        floatingActionButton = root.findViewById(R.id.fab);
        buttonAllCategory = root.findViewById(R.id.btn_all_view_categroy);
        buttonAllSelling = root.findViewById(R.id.btn_all_view_selling);
        linearLayoutMilkAndEge = root.findViewById(R.id.linear_milk_and_ege);
        linearLayoutMeat = root.findViewById(R.id.linear_meat);
        linearLayoutVegetable = root.findViewById(R.id.linear_vegetable);
        linearLayoutBakery = root.findViewById(R.id.linear_bakery);
        linearLayoutFood = root.findViewById(R.id.linear_food);
        linearLayoutRequirement = root.findViewById(R.id.linear_requirement);
        linearLayoutDrink = root.findViewById(R.id.linear_drink);
        linearLayoutChocolate = root.findViewById(R.id.linear_chocolate);

        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menu_home:
                        navController.navigate(R.id.action_homeFragment_self);
                        break;
                    case R.id.menu_requirement:
                        navController.navigate(R.id.action_homeFragment_to_orderFragment);
                        break;
                    case R.id.menu_account:
                        navController.navigate(R.id.action_homeFragment_to_accountFragment);
                        break;
                    default:
                        Toast.makeText(root.getContext(), "Soon", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        Bundle bundleData = new Bundle();

//        floatingActionButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                navController.navigate(R.id.action_homeFragment_to_requirementFragment);
//            }
//        });

        linearLayoutMilkAndEge.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String milk = "الألبان";
                bundleData.putString("Categories", milk);
                navController.navigate(R.id.action_homeFragment_to_productFragment, bundleData);
            }
        });

        linearLayoutMeat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bundleData.putString("Categories", "اللحوم");
                navController.navigate(R.id.action_homeFragment_to_productFragment, bundleData);
            }
        });

        linearLayoutVegetable.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String vegetable = "الخضراوات";
                bundleData.putString("Categories", vegetable);
                navController.navigate(R.id.action_homeFragment_to_productFragment, bundleData);
            }
        });

        linearLayoutBakery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_homeFragment_to_productFragment);
            }
        });

        linearLayoutFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_homeFragment_to_productFragment);
            }
        });

        linearLayoutRequirement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_homeFragment_to_productFragment);
            }
        });

        linearLayoutDrink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_homeFragment_to_productFragment);
            }
        });

        linearLayoutChocolate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_homeFragment_to_productFragment);
            }
        });

        buttonAllCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });

        buttonAllSelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });

        recyclerView = root.findViewById(R.id.r_v_home);
        homeModels = new ArrayList<>();
        homeAdapter = new HomeAdapter(homeModels);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        retriveReference = FirebaseDatabase.getInstance().getReference().child("Category").child("I0PKWFvABOVSWtcSHQ42EFl7FPg2");
        retriveReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                homeModels.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    HomeModel homeModel = dataSnapshot.getValue(HomeModel.class);
                    homeModels.add(homeModel);
                }
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(root.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
package com.shoppinguser.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shoppinguser.adapter.HomeAdapter;
import com.shoppinguser.adapter.ImageSliderAdapter;
import com.shoppinguser.R;
import com.shoppinguser.model.HomeModel;
import com.shoppinguser.model.SliderModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{

    View root;
    NavController navController;
    SliderView sliderViewHome, sliderViewChildreen, sliderViewRequirement;
    ArrayList<SliderModel> sliderModelsHome, sliderModelsChildreen, sliderModelsRequirement;
    ImageSliderAdapter imageSliderAdapter;
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
                String milkAndege = "الالبان والبيض";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(milkAndege);
                navController.navigate(action);
            }
        });

        linearLayoutMeat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String meat = "اللحوم";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(meat);
                navController.navigate(action);
            }
        });

        linearLayoutVegetable.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String vegetable = "الخضراوات";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(vegetable);
                navController.navigate(action);
            }
        });

        linearLayoutBakery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String bakery = "المخبز";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(bakery);
                navController.navigate(action);
            }
        });

        linearLayoutFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String food = "الطعام";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(food);
                navController.navigate(action);
            }
        });

        linearLayoutRequirement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String require = "مستلزمات";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(require);
                navController.navigate(action);
            }
        });

        linearLayoutDrink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String drink = "المشروبات";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(drink);
                navController.navigate(action);
            }
        });

        linearLayoutChocolate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String chocolata = "الشوكلاته";
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action = HomeFragmentDirections.actionHomeFragmentToProductFragment(chocolata);
                navController.navigate(action);
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
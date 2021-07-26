package com.shoppinguser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GiftFragment extends Fragment
{

    View root;
    NavController navController;
    EditText editEnterCodeGift;
    Button buttonCode;
    ProgressBar progressBarCode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_gift, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        editEnterCodeGift = root.findViewById(R.id.edit_enter_code_gift);
        buttonCode = root.findViewById(R.id.btn_code);
        progressBarCode = root.findViewById(R.id.progress_bar_code);

        buttonCode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String code = editEnterCodeGift.getText().toString();
                if (TextUtils.isEmpty(code))
                {
                    Toast.makeText(root.getContext(), "Please enter the gift Code", Toast.LENGTH_SHORT).show();
                    editEnterCodeGift.requestFocus();
                    return;
                }
                else
                {
                    progressBarCode.setVisibility(View.VISIBLE);
                    buttonCode.setVisibility(View.INVISIBLE);
                    Toast.makeText(root.getContext(), "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
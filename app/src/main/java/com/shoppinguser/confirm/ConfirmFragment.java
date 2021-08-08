package com.shoppinguser.confirm;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shoppinguser.model.ConfirmModel;
import com.shoppinguser.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConfirmFragment extends Fragment
{

    View root;
    NavController navController;
    TextView textTotalPrice;
    CircleImageView imageProfileUser;
    EditText editeUserName, editPhoneNumber, editAddress, editBuildingNumber;
    RadioButton radioButtonPaymentWhenReceiving, radioButtonPaymentUseCart;
    Button buttonDoneOrder;
    FirebaseAuth firebaseAuth;
    DatabaseReference idReference, confirmReference, deleteReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_confirm, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        firebaseAuth = FirebaseAuth.getInstance();
        idReference = FirebaseDatabase.getInstance().getReference();
        confirmReference = FirebaseDatabase.getInstance().getReference();
        deleteReference = FirebaseDatabase.getInstance().getReference();

        textTotalPrice = root.findViewById(R.id.txt_price);
        imageProfileUser = root.findViewById(R.id.img_user_profile);
        editeUserName = root.findViewById(R.id.edit_user_name);
        editPhoneNumber = root.findViewById(R.id.edit_phone_number);
        editAddress = root.findViewById(R.id.edit_address);
        editBuildingNumber = root.findViewById(R.id.edit_building_number);
        radioButtonPaymentWhenReceiving = root.findViewById(R.id.radio_pay_when_receiving);
        radioButtonPaymentUseCart = root.findViewById(R.id.radio_pay_use_cart);
        buttonDoneOrder = root.findViewById(R.id.btn_done_order);

        String price = getArguments().getString("total_price");

        textTotalPrice.setText(price + " £");

        buttonDoneOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String userName = editeUserName.getText().toString();
                String phoneNumber = editPhoneNumber.getText().toString();
                String address = editAddress.getText().toString();
                String buildingNumber = editBuildingNumber.getText().toString();

                if (TextUtils.isEmpty(userName))
                {
                    Toast.makeText(root.getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    editeUserName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber))
                {
                    Toast.makeText(root.getContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    editPhoneNumber.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address))
                {
                    Toast.makeText(root.getContext(), "Please enter your address", Toast.LENGTH_SHORT).show();
                    editAddress.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(buildingNumber))
                {
                    Toast.makeText(root.getContext(), "Please enter your building number", Toast.LENGTH_SHORT).show();
                    editBuildingNumber.requestFocus();
                    return;
                }
                else
                {
                    String Confirm_ID = idReference.push().getKey();
                    ConfirmModel confirmModel = new ConfirmModel(firebaseAuth.getCurrentUser().getUid(), Confirm_ID, price, userName, phoneNumber, address, buildingNumber);
                    confirmReference.child("Orders").child(firebaseAuth.getCurrentUser().getUid()).child(Confirm_ID).setValue(confirmModel);
                    ConfirmModel model = new ConfirmModel(Confirm_ID, price, userName, phoneNumber, address, buildingNumber);
                    confirmReference.child("orders").child(Confirm_ID).setValue(model);
                    deleteReference.child("Cart").child(firebaseAuth.getCurrentUser().getUid()).removeValue();
                    navController.navigate(R.id.action_confirmFragment_to_homeFragment);
                }
            }
        });

        imageProfileUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });
    }
}
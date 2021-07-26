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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class AuthenticationPhoneFragment extends Fragment
{

    View root;
    NavController navController;
    ImageButton imageButtonBack;
    TextView textStatementPhone, textView1, textView2, textWelcome, textDoneSent, textBeautiful, textWritePhone;
    EditText editPhone, editWritePhone, editVerification;
    CountryCodePicker countryCodePicker, cpp;
    Button buttonSentCode, buttonSentVerification;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    ScrollView parentScrollView;


    FirebaseAuth firebaseAuth;
    String verificationID;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_authentication_phone, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        imageButtonBack = root.findViewById(R.id.img_btn_back);
        imageButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_phoneAuthenticationFragment_to_splashFragment);
            }
        });

        textStatementPhone = root.findViewById(R.id.txt_statment_phone);
        textView1 = root.findViewById(R.id.txt_tasgel);
        textView2 = root.findViewById(R.id.txt_anshe2);
        textWelcome = root.findViewById(R.id.txt_welcome);
        textDoneSent = root.findViewById(R.id.txt_done_sent);
        textBeautiful = root.findViewById(R.id.text_beautiful);
        textWritePhone = root.findViewById(R.id.txt_write_phone);
        editPhone = root.findViewById(R.id.edit_phone);
        editWritePhone = root.findViewById(R.id.edit_write_phone);
        editVerification = root.findViewById(R.id.edit_verification);
        countryCodePicker = root.findViewById(R.id.country_code_picker);
        cpp = root.findViewById(R.id.cpp);
        buttonSentCode = root.findViewById(R.id.btn_sent_code);
        buttonSentVerification = root.findViewById(R.id.btn_sent_verification);
        relativeLayout = root.findViewById(R.id.relative_spp_and_edit);
        linearLayout = root.findViewById(R.id.linear_layout);
        parentScrollView = root.findViewById(R.id.parent_scroll_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        buttonSentCode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone = editWritePhone.getText().toString();
                if (TextUtils.isEmpty(phone))
                {
                    editWritePhone.setError(getString(R.string.please_enter_your_phone));
                    editWritePhone.requestFocus();
                    return;
                }
                else
                {
                    textView1.setVisibility(View.GONE);
                    textView2.setVisibility(View.GONE);
                    textWelcome.setVisibility(View.GONE);
                    textBeautiful.setVisibility(View.GONE);
                    textWritePhone.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    countryCodePicker.setVisibility(View.GONE);
                    editWritePhone.setVisibility(View.GONE);
                    buttonSentCode.setVisibility(View.GONE);
                    textStatementPhone.setVisibility(View.VISIBLE);
                    textDoneSent.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    cpp.setVisibility(View.VISIBLE);
                    editPhone.setVisibility(View.VISIBLE);
                    editVerification.setVisibility(View.VISIBLE);
                    buttonSentVerification.setVisibility(View.VISIBLE);
                    PhoneAuthOptions options =
                            PhoneAuthOptions
                                    .newBuilder(firebaseAuth)
                                    .setPhoneNumber(phone)
                                    .setTimeout(15L, TimeUnit.SECONDS)
                                    .setActivity(getActivity())
                                    .setCallbacks(callbacks)
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                    editPhone.setText(phone);
                }
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textWelcome.setVisibility(View.VISIBLE);
                textBeautiful.setVisibility(View.VISIBLE);
                textWritePhone.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                countryCodePicker.setVisibility(View.VISIBLE);
                editWritePhone.setVisibility(View.VISIBLE);
                buttonSentCode.setVisibility(View.VISIBLE);
                textStatementPhone.setVisibility(View.GONE);
                textDoneSent.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
                cpp.setVisibility(View.GONE);
                editPhone.setVisibility(View.GONE);
                editVerification.setVisibility(View.GONE);
                buttonSentVerification.setVisibility(View.GONE);

                Snackbar.make(parentScrollView, e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token)
            {
                verificationID = verificationId;
                resendingToken = token;
            }
        };

        buttonSentVerification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String code = editVerification.getText().toString();
                if (TextUtils.isEmpty(code))
                {
                    editVerification.setError(getString(R.string.please_enter_your_code));
                    editVerification.requestFocus();
                    return;
                }
                else
                {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        firebaseAuth
                .signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            String phoneNumber = editWritePhone.getText().toString();
                            UserModel userModel = new UserModel(user_id, phoneNumber);
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                            reference
                                    .child("Account an users")
                                    .child(firebaseAuth.getCurrentUser().getUid())
                                    .setValue(userModel);
                            navController.navigate(R.id.action_phoneAuthenticationFragment_to_sucessFragment);
                        }
                        else
                        {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
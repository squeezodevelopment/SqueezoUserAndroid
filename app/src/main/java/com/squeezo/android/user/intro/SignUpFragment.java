package com.squeezo.android.user.intro;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squeezo.android.user.R;

public class SignUpFragment extends android.support.v4.app.Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        final EditText editName = (EditText) view.findViewById(R.id.editTextNameRegister);
        final EditText editEmail = (EditText) view.findViewById(R.id.editTextEmailRegister);
        final EditText editPassword = (EditText) view.findViewById(R.id.editTextPasswordRegister);

        Button btnRegister = (Button) view.findViewById(R.id.buttonRegisterRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editName.getText().toString().length() == 0)
                    editName.setBackgroundResource(R.drawable.custom_edit_text_error);
                else if (editEmail.getText().toString().length() == 0)
                    editEmail.setBackgroundResource(R.drawable.custom_edit_text_error);
                else if (editPassword.getText().toString().length() == 0)
                    editPassword.setBackgroundResource(R.drawable.custom_edit_text_error);
                else
                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}

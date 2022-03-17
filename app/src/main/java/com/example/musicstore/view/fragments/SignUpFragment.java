package com.example.musicstore.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicstore.R;
import com.example.musicstore.controller.SignUpController;
import com.example.musicstore.model.Customer;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.User;

public class SignUpFragment extends Fragment {

    EditText etName;
    EditText etLastName;
    EditText etEmail;
    EditText etUsername;
    EditText etPassword;

    Button btnSignUp;

    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etName = getActivity().findViewById(R.id.etName);
        etLastName = getActivity().findViewById(R.id.etLastName);
        etEmail = getActivity().findViewById(R.id.etEmail);
        etUsername = getActivity().findViewById(R.id.etUsername);
        etPassword = getActivity().findViewById(R.id.etPassword);

        btnSignUp = getActivity().findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                dbHelper = DatabaseHelper.getInstance(getContext());
                SignUpController controller = new SignUpController(name, lastName, email, username, password, dbHelper);
                try {
                    controller.signUp();
                    Toast.makeText(getContext(), "Registro completado.", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etLastName.setText("");
                    etEmail.setText("");
                    etUsername.setText("");
                    etPassword.setText("");
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error al registrarse", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
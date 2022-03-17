package com.example.musicstore.view.fragments;

import android.content.Intent;
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
import com.example.musicstore.controller.LoginController;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.User;
import com.example.musicstore.view.activities.MainActivity;

public class LoginFragment extends Fragment {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etUsername = getView().findViewById(R.id.etUsername);
        etPassword = getView().findViewById(R.id.etPassword);
        btnLogin = getView().findViewById(R.id.btnLogin);
        btnSignUp = getView().findViewById(R.id.btnSignup);

        dbHelper = DatabaseHelper.getInstance(getContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User logged = new User(etUsername.getText().toString(), etPassword.getText().toString());
                LoginController controller = new LoginController(logged, dbHelper);
                String result = controller.login();
                if(result.equals("customer")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", logged.getUsername());
                    Intent invokeHome = new Intent(getActivity(), MainActivity.class);
                    invokeHome.putExtras(bundle);
                    startActivity(invokeHome);
                } else if(!result.equals("none")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("adminName", result);
                    Intent invokeAdminHome = new Intent(getActivity(), MainActivity.class);
                    invokeAdminHome.putExtras(bundle);
                    startActivity(invokeAdminHome);
                } else {
                    Toast.makeText(getContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new SignUpFragment())
                        .commit();
            }
        });
    }
}

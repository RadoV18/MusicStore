package com.example.musicstore.view.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.musicstore.R;
import com.example.musicstore.view.adapters.ProductModel;

public class ProductDetailsFragment extends Fragment {

    private ProductModel product;

    private ImageView ivImage;

    private TextView tvNameDetails;
    private TextView tvDescriptionDetails;
    private TextView tvPriceDetails;
    private Button btnHomeDetails;

    public ProductDetailsFragment(ProductModel product) {
        this.product = product;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNameDetails = getActivity().findViewById(R.id.tvNameDetails);
        tvNameDetails.setText(product.getName());
        tvDescriptionDetails = getActivity().findViewById(R.id.tvDescriptionDetails);
        tvDescriptionDetails.setText(product.getDescription());
        tvPriceDetails = getActivity().findViewById(R.id.tvPriceDetails);
        tvPriceDetails.setText("Precio: $" + product.getPrice());
        ivImage = getActivity().findViewById(R.id.ivImageDetails);
        ivImage.setImageBitmap(product.getImage());
        btnHomeDetails = getActivity().findViewById(R.id.btnHomeDetails);
        btnHomeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new HomeFragment())
                        .commit();
            }
        });
    }
}

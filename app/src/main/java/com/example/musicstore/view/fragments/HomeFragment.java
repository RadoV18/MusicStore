package com.example.musicstore.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicstore.R;
import com.example.musicstore.controller.ProductListController;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.view.adapters.HomeProductAdapter;
import com.example.musicstore.view.adapters.ProductListAdapter;
import com.example.musicstore.view.adapters.ProductModel;
import com.example.musicstore.view.adapters.TouchAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    ArrayList<ProductModel> list;
    HomeProductAdapter adapter;
    RecyclerView rvProducts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductListController controller = new ProductListController(DatabaseHelper.getInstance(getContext()));
        list = controller.getProductList();
        System.out.println(list.size());
        adapter = new HomeProductAdapter(list);
        rvProducts = getActivity().findViewById(R.id.rvProductsCustomer);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvProducts.addOnItemTouchListener(new TouchAdapter(getContext(), rvProducts, new TouchAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("rvn","toque simple "+list.get(position).getName());
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new ProductDetailsFragment(list.get(position)))
                        .commit();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }
}

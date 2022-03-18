package com.example.musicstore.view.fragments;

import android.os.Bundle;
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
import com.example.musicstore.view.adapters.ProductListAdapter;
import com.example.musicstore.view.adapters.ProductModel;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {

    ArrayList<ProductModel> list;
    ProductListAdapter adapter;
    RecyclerView rvProducts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductListController controller = new ProductListController(DatabaseHelper.getInstance(getContext()));
        list = controller.getProductList();
        System.out.println(list.size());
        adapter = new ProductListAdapter(list);
        rvProducts = getActivity().findViewById(R.id.rvProducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }
}

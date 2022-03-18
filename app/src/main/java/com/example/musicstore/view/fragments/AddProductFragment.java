package com.example.musicstore.view.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.musicstore.R;
import com.example.musicstore.controller.AddProductController;
import com.example.musicstore.model.DatabaseHelper;

import java.io.IOException;
import java.util.Objects;

public class AddProductFragment extends Fragment {

    private EditText etName;
    private EditText etDescription;
    private EditText etBrand;
    private EditText etPrice;
    private EditText etStock;
    private Button btnGallery;
    private Button btnRestore;
    private Button btnSave;
    private ImageView ivImage;
    private Spinner categorySpinner;

    private Bitmap bitmapImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categorySpinner = getActivity().findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        etName = getActivity().findViewById(R.id.etName);
        etDescription = getActivity().findViewById(R.id.etDescription);
        etBrand = getActivity().findViewById(R.id.etBrand);
        etPrice = getActivity().findViewById(R.id.etPrice);
        etStock = getActivity().findViewById(R.id.etStock);

        ivImage = getActivity().findViewById(R.id.ivImage);
        btnGallery = getActivity().findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                } else {
                    startGallery();
                }
            }
        });

        btnRestore = getActivity().findViewById(R.id.btnRestore);
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restoreValues();
            }
        });

        btnSave = getActivity().findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AddProductController controller = new AddProductController(etName.getText().toString(),
                            etDescription.getText().toString(), etBrand.getText().toString(),
                            etPrice.getText().toString(), etStock.getText().toString(),
                            categorySpinner.getSelectedItem().toString(), bitmapImage, DatabaseHelper.getInstance(getContext()));
                    controller.insertData();
                    Toast.makeText(getContext(), "Producto agregado exitosamente.", Toast.LENGTH_SHORT).show();
                    restoreValues();
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), returnUri);
                    ivImage.setImageBitmap(bitmapImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(requestCode);
            }
        } else {
            System.out.println("not ok");
        }
    }

    public void startGallery() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, 1000);
//        startActivityForResult(galleryIntent, 1000);
    }

    public void restoreValues() {
        etName.setText("");
        etDescription.setText("");
        etBrand.setText("");
        etPrice.setText("");
        etStock.setText("");
        ivImage.setImageBitmap(null);
    }
}
package com.example.musicstore.view.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.musicstore.R;
import com.example.musicstore.controller.ModifyProductController;
import com.example.musicstore.model.Brand;
import com.example.musicstore.model.DatabaseHelper;
import com.example.musicstore.model.Instrument;
import com.google.android.flexbox.FlexboxLayout;

import java.io.IOException;

public class ModifyProductFragment extends Fragment {

    private String productId;

    private FlexboxLayout fbSearch;
    private FlexboxLayout fbDetails;
    private FlexboxLayout fbButtons;

    private EditText etProductId;
    private Button btnSearch;

    private EditText etName;
    private EditText etDescription;
    private EditText etBrand;
    private EditText etPrice;
    private EditText etStock;
    private ImageView ivImage;
    private Button btnGalleryM;

    private Button btnCancel;
    private Button btnSave;

    private Bitmap bitmapImage;

    private DatabaseHelper dbHelper;

    private Instrument foundInstrument;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modify_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fbSearch = getActivity().findViewById(R.id.fbSearch);
        fbDetails = getActivity().findViewById(R.id.fbDetails);
        fbButtons = getActivity().findViewById(R.id.fbButtons);

        dbHelper = DatabaseHelper.getInstance(getContext());

        etProductId = getActivity().findViewById(R.id.etProductId);
        btnSearch = getActivity().findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etProductId.getText().toString();
                productId = id;
                ModifyProductController controller = new ModifyProductController(id, dbHelper);
                Instrument found = controller.findProductById();
                if(found != null) {
                    Brand searchBrand = new Brand(Long.parseLong(id));
                    searchBrand.findById(dbHelper.getReadableDatabase());
                    foundInstrument = found;
                    fbSearch.setVisibility(View.GONE);

                    etName.setText(found.getName());
                    etDescription.setText(found.getDescription());
                    etBrand.setText(searchBrand.getName());
                    etPrice.setText(String.valueOf(found.getPrice()));
                    etStock.setText(String.valueOf(found.getStock()));

                    Bitmap image = BitmapFactory.decodeByteArray(found.getImage(), 0, found.getImage().length);
                    bitmapImage = image;
                    System.out.println(image);
                    ivImage.setImageBitmap(image);

                    fbDetails.setVisibility(View.VISIBLE);
                    fbButtons.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "Producto no encontrado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etName = getActivity().findViewById(R.id.etNameM);
        etDescription = getActivity().findViewById(R.id.etDescriptionM);
        etBrand = getActivity().findViewById(R.id.etBrandM);
        etPrice = getActivity().findViewById(R.id.etPriceM);
        etStock = getActivity().findViewById(R.id.etStockM);
        ivImage = getActivity().findViewById(R.id.ivImageM);

        btnGalleryM = getActivity().findViewById(R.id.btnGalleryM);
        btnGalleryM.setOnClickListener(new View.OnClickListener() {
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

        btnCancel = getActivity().findViewById(R.id.btnCancelM);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restore();
            }
        });

        btnSave = getActivity().findViewById(R.id.btnSaveM);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                String brand = etBrand.getText().toString();
                double price = Double.parseDouble(etPrice.getText().toString());
                int stock = Integer.parseInt(etStock.getText().toString());
                ModifyProductController controller = new ModifyProductController(productId, name, description,
                        brand, price, stock, foundInstrument.getInstrumentTypeId(), bitmapImage, DatabaseHelper.getInstance(getContext()));
                controller.updateProduct();
                Toast.makeText(getContext(), "Pedido modificado correctamente.", Toast.LENGTH_SHORT).show();
                restore();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                    ivImage.setImageBitmap(bitmapImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 1000);
    }

    public void restore() {
        etName.setText("");
        etDescription.setText("");
        etProductId.setText("");
        etBrand.setText("");
        etPrice.setText("");
        etStock.setText("");
        ivImage.setImageBitmap(null);
        fbSearch.setVisibility(View.VISIBLE);
        fbDetails.setVisibility(View.GONE);
        fbButtons.setVisibility(View.GONE);
    }
}

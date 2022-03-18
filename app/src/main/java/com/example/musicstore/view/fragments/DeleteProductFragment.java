package com.example.musicstore.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.musicstore.controller.DeleteProductController;
import com.example.musicstore.model.DatabaseHelper;

public class DeleteProductFragment extends Fragment {
    
    private EditText etProductIdD;
    private Button btnSearchD;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delete_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etProductIdD = getActivity().findViewById(R.id.etProductIdD);
        btnSearchD = getActivity().findViewById(R.id.btnSearchD);
        btnSearchD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etProductIdD.getText().toString();
                DeleteProductController controller = new DeleteProductController(id, DatabaseHelper.getInstance(getContext()));
                if(controller.findProduct()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Advertencia");
                    alert.setMessage("¿Está seguro que desea eliminar el producto?");
                    alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            controller.deleteProduct();
                            Toast.makeText(getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
                            restore();
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.setCancelable(false);
                    alert.show();
                } else {
                    Toast.makeText(getContext(), "Producto no encontrado.", Toast.LENGTH_SHORT).show();
                    restore();
                }
            }
        });
    }

    public void restore() {
        etProductIdD.setText("");
    }
}

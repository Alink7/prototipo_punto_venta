package com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Producto;

/**
 * Created by alumno.desarrollo1 on 22/07/2016.
 */
public class FragmentDetalleProducto extends Fragment {

    private Producto producto;

    public FragmentDetalleProducto(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey("PRODUCTO")) {
            // Cargar modelo según el identificador
            producto = (getArguments().getParcelable("PRODUCTO"));

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_producto, container);

        if(producto != null) {
            TextView prueba = (TextView) v.findViewById(R.id.txtPrueba);
            prueba.setText(producto.getNombre());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

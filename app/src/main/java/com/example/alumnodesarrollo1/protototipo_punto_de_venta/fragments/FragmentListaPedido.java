package com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;

/**
 * Created by alumno.desarrollo1 on 21/07/2016.
 */
public class FragmentListaPedido extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lista_pedido, container, false);
        return v;
    }


}

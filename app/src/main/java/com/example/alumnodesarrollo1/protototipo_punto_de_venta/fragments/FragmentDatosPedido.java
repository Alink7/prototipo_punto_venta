package com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;

/**
 * Created by alumno.desarrollo1 on 21/07/2016.
 */
public class FragmentDatosPedido extends Fragment {

    private AutoCompleteTextView txtCliente;
    private AutoCompleteTextView txtBusquedaProducto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_datos_pedido, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarClientes();
        cargarProductos();
    }

    public void cargarClientes(){
        txtCliente = (AutoCompleteTextView) getView().findViewById(R.id.txtCliente);
        String[] clientes = {"cliente1", "cliente2", "cliente3", "cliente4", "cliente5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, clientes);
        txtCliente.setAdapter(adapter);
    }

    public void cargarProductos(){
        txtBusquedaProducto = (AutoCompleteTextView) getView().findViewById(R.id.txtBusquedaProducto);
        String[] productos = {"producto1", "producto2", "producto3", "producto4", "producto5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, productos);
        txtBusquedaProducto.setAdapter(adapter);
    }
}

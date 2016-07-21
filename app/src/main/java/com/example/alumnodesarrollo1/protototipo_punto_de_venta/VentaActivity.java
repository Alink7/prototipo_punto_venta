package com.example.alumnodesarrollo1.protototipo_punto_de_venta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters.ListaPedidoAdapter;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.bundles.PedidoDataBundle;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments.FragmentDatosPedido;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments.FragmentListaPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VentaActivity extends AppCompatActivity {

    private AutoCompleteTextView txtCliente;
    private AutoCompleteTextView txtBusquedaProducto;
    private List<String> listaProductos;
    private ListaPedidoAdapter adapter;
    private HashMap<String, List<String>> data;
    private boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        listarProductos();
        cargarClientes();
        cargarProductos();
        data = new PedidoDataBundle().getData();

        final ExpandableListView lista = (ExpandableListView)findViewById(R.id.listaProductosPedido);
        adapter = new ListaPedidoAdapter(this, data, listaProductos);
        lista.setAdapter(adapter);
    }

    public void listarProductos(){
        listaProductos = new ArrayList<>();
        listaProductos.add("producto1");
        listaProductos.add("producto2");
        listaProductos.add("producto3");
    }

    public void cargarClientes(){
        txtCliente = (AutoCompleteTextView) findViewById(R.id.txtCliente);
        String[] clientes = {"cliente1", "cliente2", "cliente3", "cliente4", "cliente5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        txtCliente.setAdapter(adapter);
    }

    public void cargarProductos(){
        txtBusquedaProducto = (AutoCompleteTextView) findViewById(R.id.txtBusquedaProducto);
        String[] productos = {"producto1", "producto2", "producto3", "producto4", "producto5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productos);
        txtBusquedaProducto.setAdapter(adapter);
    }

    public void addItem(View v){
        String producto = txtBusquedaProducto.getText().toString();
        txtBusquedaProducto.setText("");
        List<String> array = new ArrayList<>();
        array.add(producto);
        adapter.addItem(producto, array);
    }
}

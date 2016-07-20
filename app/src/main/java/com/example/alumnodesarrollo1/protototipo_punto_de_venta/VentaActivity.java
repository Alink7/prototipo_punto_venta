package com.example.alumnodesarrollo1.protototipo_punto_de_venta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters.ListaPedidoAdapter;

import java.util.ArrayList;
import java.util.List;

public class VentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        List<String> listaProductos = new ArrayList<>();
        listaProductos.add("Producto1");
        listaProductos.add("Producto2");
        listaProductos.add("Producto3");
        listaProductos.add("Producto4");
        listaProductos.add("Producto5");
        listaProductos.add("Producto6");
        listaProductos.add("Producto7");
        listaProductos.add("Producto8");
        listaProductos.add("Producto9");
        listaProductos.add("Producto10");
        ExpandableListView lista = (ExpandableListView)findViewById(R.id.listaProductosPedido);
        final ListaPedidoAdapter adapter = new ListaPedidoAdapter(this, null, listaProductos);
        lista.setAdapter(adapter);
        lista.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getBaseContext(), "aaaa", Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
        lista.setClickable(true);
        lista.setFocusable(true);
    }
}

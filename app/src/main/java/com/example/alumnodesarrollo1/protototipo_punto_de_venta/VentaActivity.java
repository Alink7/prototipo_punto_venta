package com.example.alumnodesarrollo1.protototipo_punto_de_venta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters.ListaPedidoAdapter;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.bundles.PedidoDataBundle;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments.FragmentDetalleProducto;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Producto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class VentaActivity extends AppCompatActivity {

    private AutoCompleteTextView txtCliente;
    private AutoCompleteTextView txtBusquedaProducto;
    private List<Producto> listaProductos;
    private ListaPedidoAdapter adapter;
    private HashMap<String, List<String>> data;
    private HashMap<String, Producto> listaMaestra;
    private TextView txtSubtotal, txtIva, txtTotal;
    private boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        txtSubtotal = (TextView)findViewById(R.id.txtSubtotalValorBottom);
        txtIva = (TextView)findViewById(R.id.txtIvaValorBottom);
        txtTotal = (TextView)findViewById(R.id.txtTotalValorBottom);

        listarProductos();
        cargarClientes();
        cargarProductos();
        data = new PedidoDataBundle().getData();

        final ExpandableListView lista = (ExpandableListView)findViewById(R.id.listaProductosPedido);
        adapter = new ListaPedidoAdapter(this, listaProductos, txtSubtotal, txtIva, txtTotal);
        lista.setAdapter(adapter);
    }

    public void listarProductos(){
        listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("producto1", "18000", "descripcion1"));
        listaProductos.add(new Producto("producto2", "19000", "descripcion2"));
        listaProductos.add(new Producto("producto3", "20000", "descripcion3"));
    }

    public void cargarClientes(){
        txtCliente = (AutoCompleteTextView) findViewById(R.id.txtCliente);
        String[] clientes = {"cliente1", "cliente2", "cliente3", "cliente4", "cliente5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        txtCliente.setAdapter(adapter);
    }

    public void cargarProductos(){
        txtBusquedaProducto = (AutoCompleteTextView) findViewById(R.id.txtBusquedaProducto);
        //lista meastra de productos en bodega
        listaMaestra = cargarListaMeastra();
        final Collection<Producto> listaBusqueda = listaMaestra.values();
        final String[] productos = new String[listaBusqueda.size()];
        int i = 0;
        for(Producto p : listaBusqueda){
            productos[i++] = p.getNombre();
        }
        //este adapter debe ser uno de Productos
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productos);
        txtBusquedaProducto.setAdapter(adapter);

        txtBusquedaProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String n_produco = (adapter.getItem(position));
                //cargarFragmento(n_produco);
            }
        });
    }

    public void addItem(View v){
        String producto = txtBusquedaProducto.getText().toString();
        txtBusquedaProducto.setText("");
        List<String> array = new ArrayList<>();
        array.add(producto);

        //limpiar fragmento
        cargarFragmento(null);
        adapter.addItem(listaMaestra.get(producto));
    }

    public HashMap<String, Producto> cargarListaMeastra(){
        HashMap<String, Producto> listaMaeastra = new HashMap<>();
        listaMaeastra.put("Leche", new Producto("Leche", "500", ""));
        listaMaeastra.put("Mantequilla", new Producto("Mantequilla", "500", ""));
        listaMaeastra.put("Pan", new Producto("Pan", "500", ""));
        listaMaeastra.put("Mermelada", new Producto("Mermelada", "500", ""));
        listaMaeastra.put("Huevo", new Producto("Huevo", "500", ""));
        listaMaeastra.put("Palta", new Producto("Palta", "500", ""));
        return listaMaeastra;
    }

    public void cargarFragmento(Producto producto){
        Bundle arguments = new Bundle();
        arguments.putParcelable("PRODUCTO", producto);
        FragmentDetalleProducto fragment = new FragmentDetalleProducto();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_detalle_producto, fragment)
                .commit();
    }
}

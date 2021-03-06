package com.example.alumnodesarrollo1.protototipo_punto_de_venta;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters.ListaPedidoAdapter;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters.ListaProductosAdapter;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.bundles.PedidoDataBundle;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.fragments.FragmentDetalleProducto;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VentaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AutoCompleteTextView txtCliente;
    private AutoCompleteTextView txtBusquedaProducto;

    private List<Producto> listaProductos;

    //Adapters
    private ListaPedidoAdapter adapter;
    private ArrayAdapter<String> clientesAdapter;

    private HashMap<String, List<String>> data;
    private HashMap<String, Producto> listaMaestra;

    private TextView txtSubtotal, txtIva, txtTotal;
    private Button btnAsignar;

    private String strSubtotal, strIva, strTotal;
    private boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtSubtotal = (TextView)findViewById(R.id.txtSubtotalValorBottom);
        txtIva = (TextView)findViewById(R.id.txtIvaValorBottom);
        txtTotal = (TextView)findViewById(R.id.txtTotalValorBottom);
        btnAsignar = (Button)findViewById(R.id.btn_asginar_cliente);

        listarProductos();
        cargarClientes();
        cargarProductos();
        data = new PedidoDataBundle().getData();

        final ExpandableListView lista = (ExpandableListView)findViewById(R.id.listaProductosPedido);
        adapter = new ListaPedidoAdapter(this, listaProductos, txtSubtotal, txtIva, txtTotal);
        lista.setAdapter(adapter);

        if(savedInstanceState != null){
            strSubtotal = savedInstanceState.getString("subtotal");
            strIva = savedInstanceState.getString("iva");
            strTotal = savedInstanceState.getString("total");

            System.out.println("SUBTOTAL " + strSubtotal);
            System.out.println("IVA " + strIva);
            System.out.println("TOTAL " + strTotal);
        }
    }

    public void listarProductos(){
        listaProductos = new ArrayList<>();
       /* listaProductos.add(new Producto("producto1", "18000", "descripcion1"));
        listaProductos.add(new Producto("producto2", "19000", "descripcion2"));
        listaProductos.add(new Producto("producto3", "20000", "descripcion3"));*/
    }

    public void cargarClientes(){
        txtCliente = (AutoCompleteTextView) findViewById(R.id.txtCliente);
        String[] clientes = {"cliente1", "cliente2", "cliente3", "cliente4", "cliente5"};
        clientesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        txtCliente.setAdapter(clientesAdapter);
    }

    public void cargarProductos(){
        txtBusquedaProducto = (AutoCompleteTextView) findViewById(R.id.txtBusquedaProducto);
        //lista meastra de productos en bodega
        listaMaestra = cargarListaMeastra();
        final ListaProductosAdapter adapter = new ListaProductosAdapter(this, listaMaestra);

        txtBusquedaProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtBusquedaProducto.setText(adapter.getItem(position).getNombre());
            }
        });
        txtBusquedaProducto.setAdapter(adapter);
    }

    //acciones de botones
    public void addItem(View v){
        String producto = txtBusquedaProducto.getText().toString();
        txtBusquedaProducto.setText("");
        //limpiar fragmento
        cargarFragmento(new Producto("", "","","", 0));
        adapter.addItem(listaMaestra.get(producto));
    }

    public void asignarCliente(View v){
        txtCliente.setEnabled(false);
        btnAsignar.setText("Editar");
        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCliente.setEnabled(true);
                btnAsignar.setText("Asignar");
                btnAsignar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        asignarCliente(v);
                    }
                });
            }
        });
        Toast.makeText(VentaActivity.this, "El pedido se asignó a " + txtCliente.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    
    public void realizarPedido(View v){
        View focus = null;
        boolean cancelar = false;

        //validar cliente
        String cliente = txtCliente.getText().toString();
        if(TextUtils.isEmpty(cliente)){
            txtCliente.setError("Este campo es obligatorio");
            focus = txtCliente;
            cancelar = true;
        }
        if(!esClienteValido(cliente)){
            txtCliente.setError("El cliente no es válido");
            focus = txtCliente;
            cancelar = true;
        }
        //validar lista

        //guardar pedido
        if(cancelar){
            focus.requestFocus();
        }else {
            int size = adapter.getGroupCount();
            List<Producto> productosEnPedido = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                productosEnPedido.add((Producto) adapter.getGroup(i));
                System.out.println(productosEnPedido.get(i).getNombre());
            }
        }

    }

    //Valida al cliente
    private boolean esClienteValido(String nCliente){
        if(clientesAdapter.getPosition(nCliente) > -1)
            return true;
        return false;
    }

    public HashMap<String, Producto> cargarListaMeastra(){
        HashMap<String, Producto> listaMaeastra = new HashMap<>();
        listaMaeastra.put("Leche" ,new Producto("0001", "Leche", "500", "", R.mipmap.leche));
        listaMaeastra.put("Mantequilla", new Producto("0002", "Mantequilla", "500", "", R.mipmap.mantequilla));
        listaMaeastra.put("Pan", new Producto("0003", "Pan", "500", "", R.mipmap.pan));
        listaMaeastra.put("Mermelada", new Producto("0004", "Mermelada", "500", "", R.mipmap.mermelada));
        listaMaeastra.put("Huevo" ,new Producto("0005", "Huevo", "500", "", R.mipmap.huevo));
        listaMaeastra.put("Palta", new Producto("0006", "Palta", "2000", "", R.mipmap.palta));

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        strSubtotal = txtSubtotal.getText().toString();
        strIva = txtIva.getText().toString();
        strTotal = txtTotal.getText().toString();

        outState.putString("subtotal", strSubtotal);
        outState.putString("iva", strIva);
        outState.putString("total", strTotal);

        System.out.println("SUBTOTAL " + strSubtotal);
        System.out.println("IVA " + strIva);
        System.out.println("TOTAL " + strTotal);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        strSubtotal = savedInstanceState.getString("subtotal");
        strIva = savedInstanceState.getString("iva");
        strTotal = savedInstanceState.getString("total");

        System.out.println("SUBTOTAL " + strSubtotal);
        System.out.println("IVA " + strIva);
        System.out.println("TOTAL " + strTotal);

        txtSubtotal.setText(strSubtotal);
        txtIva.setText(strIva);
        txtTotal.setText(strTotal);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_venta) {
            Intent i = new Intent(this, VentaActivity.class);
            onPause();
            startActivity(i);
        } else if (id == R.id.nav_clientes) {
            Intent i = new Intent(this, ClienteListActivity.class);
            onPause();
            startActivity(i);
        } else if (id == R.id.nav_historial) {

        } else if (id == R.id.nav_salir) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

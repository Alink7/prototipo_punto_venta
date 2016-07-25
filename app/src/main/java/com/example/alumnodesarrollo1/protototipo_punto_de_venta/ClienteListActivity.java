package com.example.alumnodesarrollo1.protototipo_punto_de_venta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Clientes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ClienteDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ClienteListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clientes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_clientes);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        cargarClientes();
        View recyclerView = findViewById(R.id.cliente_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.cliente_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_clientes);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_clientes);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cargarClientes(){
        clientes = new ArrayList<>();
        clientes.add(new Cliente("cliente1", "11222333-5", "12345678", "cliente1@gmail.com", "Direccion 1", 10, 0));
        clientes.add(new Cliente("cliente2", "11222333-6", "12345678", "cliente2@gmail.com", "Direccion 2", 100, 0));
        clientes.add(new Cliente("cliente3", "11222333-7", "12345678", "cliente3@gmail.com", "Direccion 3", 1000, 0));
        clientes.add(new Cliente("cliente4", "11222333-8", "12345678", "cliente4@gmail.com", "Direccion 4", 10000, 0));
        clientes.add(new Cliente("cliente5", "11222333-8", "12345678", "cliente4@gmail.com", "Direccion 5", 100000, 0));
        clientes.add(new Cliente("cliente6", "11222333-8", "12345678", "cliente4@gmail.com", "Direccion 6", 100000, 0));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(clientes));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Cliente> clientes;


        public SimpleItemRecyclerViewAdapter(List<Cliente> clientes) {
            this.clientes = clientes;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cliente_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = clientes.get(position);
            holder.listaNombreCliente.setText(clientes.get(position).getNombre());
            holder.listaDireccionCliente.setText(clientes.get(position).getDireccion());
            //holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putParcelable(ClienteDetailFragment.ARG_ITEM_ID, holder.mItem);
                        ClienteDetailFragment fragment = new ClienteDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.cliente_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ClienteDetailActivity.class);
                        intent.putExtra(ClienteDetailFragment.ARG_ITEM_ID, holder.mItem);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return clientes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView listaNombreCliente;
            public final TextView listaDireccionCliente;
            //public final TextView mContentView;
            public Cliente mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                listaNombreCliente = (TextView) view.findViewById(R.id.lista_nombre_cliente);
                listaDireccionCliente = (TextView) view.findViewById(R.id.lista_direccion_cliente);
               // mContentView = (TextView) view.findViewById(R.id.content);
            }

            /*@Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }*/
        }
    }
}

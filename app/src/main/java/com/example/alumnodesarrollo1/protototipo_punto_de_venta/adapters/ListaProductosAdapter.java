package com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by alumno.desarrollo1 on 26/07/2016.
 */
public class ListaProductosAdapter extends ArrayAdapter<Producto> implements AdapterView.OnItemClickListener {

    private final List<Producto> productos;
    private List<Producto> productosFiltrados = new ArrayList<>();

    public ListaProductosAdapter(Context context, HashMap<String, Producto> productos) {
        super(context, 0);
        this.productos = new ArrayList<>();
        Set<String> keys = productos.keySet();
        for (String key: keys) {
            this.productos.add(productos.get(key));
        }
    }


    @Override
    public int getCount() {
        return productosFiltrados.size();
    }

    @Override
    public Filter getFilter() {
        return new FiltroProducto(this, productos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item from filtered list.
        Producto producto = productosFiltrados.get(position);

        // Inflate your custom row layout as usual.
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.productos_autocomplete_layout, parent, false);

        TextView codigo = (TextView) convertView.findViewById(R.id.codigo_producto);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombre_producto);
        ImageView imagen = (ImageView) convertView.findViewById(R.id.imagen_producto);

        codigo.setText(producto.getCodigo());
        nombre.setText(producto.getNombre());
        imagen.setImageResource(producto.getImagen());

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Nombre" + productosFiltrados.get(position).getNombre());
    }

    class FiltroProducto extends Filter{

        ListaProductosAdapter adapter;
        List<Producto> listaOriginal;
        List<Producto> listaFiltrada;

        public FiltroProducto(ListaProductosAdapter adapter, List<Producto> listaOriginal) {
            super();
            this.adapter = adapter;
            this.listaOriginal = listaOriginal;
            this.listaFiltrada = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            listaFiltrada.clear();
            final FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                listaFiltrada.addAll(listaOriginal);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                // Your filtering logic goes in here
                for (final Producto p: listaOriginal) {
                    if ((p.getCodigo()+p.getNombre()).toLowerCase().contains(filterPattern)) {
                        listaFiltrada.add(p);
                    }
                }
            }
            results.values = listaFiltrada;
            results.count = listaFiltrada.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.productosFiltrados.clear();
            adapter.productosFiltrados.addAll((List) results.values);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public Producto getItem(int position) {
        return productosFiltrados.get(position);
    }
}


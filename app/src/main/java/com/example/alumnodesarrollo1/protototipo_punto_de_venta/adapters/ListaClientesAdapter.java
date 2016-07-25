package com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Cliente;

import java.util.ArrayList;

/**
 * Created by alumno.desarrollo1 on 25/07/2016.
 */
public class ListaClientesAdapter extends ArrayAdapter<Cliente> {

    private String[] colorlist = {"#e00707", "#4ac925" };
    private Activity context;
    private ArrayList<Cliente> lista;

    public ListaClientesAdapter(Activity context, ArrayList<Cliente> lista){
        super(context, R.layout.lista_clientes_item, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lista_clientes_item,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.description);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagen);

        txtTitle.setText(lista.get(position).getNombre());
        int positionColor = 0;
        if(lista.get(position).getEstado() == 1){
            positionColor = 1;
        }
        imageView.setBackgroundColor(Color.parseColor(colorlist[positionColor]));
        return rowView;
    }
}

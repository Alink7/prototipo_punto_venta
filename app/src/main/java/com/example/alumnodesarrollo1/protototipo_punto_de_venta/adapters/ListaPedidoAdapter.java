package com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alumno.desarrollo1 on 20/07/2016.
 */
public class ListaPedidoAdapter extends BaseExpandableListAdapter {

    private Activity context;
    //el map debería ser Map<String, Objeto(Producto)>
    private HashMap<String, List<String>> productoDetalle;
    private List<String> productos;

    public ListaPedidoAdapter(Activity context, HashMap<String, List<String>> productoDetalle, List<String> productos){
        this.context = context;
        this.productoDetalle = productoDetalle;
        this.productos = productos;
    }

    @Override
    public int getGroupCount() {
        return productos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return productoDetalle.get(productos.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return productos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return productoDetalle.get(productos.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,final ViewGroup parent) {
        String nombreProducto = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pedido_item, null);
        }
       /* EditText cantidad_producto = (EditText) convertView.findViewById(R.id.cantidad_producto);
        cantidad_producto.setFocusable(false);
        cantidad_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setFocusable(true);
            }
        });*/
        ImageButton eliminar_producto = (ImageButton) convertView.findViewById(R.id.eliminar_producto);
        eliminar_producto.setFocusable(false);
        eliminar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.eliminar_producto){
                    eliminarItemLista(groupPosition);
                }
            }
        });
        TextView nombre_producto = (TextView) convertView.findViewById(R.id.lista_nombre_producto);
        nombre_producto.setText(nombreProducto);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String detalle = (String)getChild(groupPosition, childPosition);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.pedido_item_detalle,parent, false);
        TextView nombre = (TextView) convertView.findViewById(R.id.detalle_nombre_producto_label);
        nombre.setText(detalle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean removeGroup(int groupPosition){
        productos.remove(groupPosition);
        notifyDataSetChanged();
        return true;
    }

    public void addItem(String groupTitle, List<String> data){
        productos.add(0, groupTitle);
        productoDetalle.put(groupTitle, data);
        notifyDataSetChanged();
    }

    public void eliminarItemLista(final int groupPosition){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.dialog_eliminar_item_title)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeGroup(groupPosition);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }
}

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
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Producto;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alumno.desarrollo1 on 20/07/2016.
 */
public class ListaPedidoAdapter extends BaseExpandableListAdapter {

    private Activity context;
    //el map debería ser Map<String, Objeto(Producto)>
    //private HashMap<String, List<Producto>> productoDetalle;
    private List<Producto> productos;
    private TextView subtotal, iva, total;

    public ListaPedidoAdapter(Activity context, List<Producto> productos, TextView subtotal,
                              TextView iva, TextView total){
        this.context = context;
        //this.productoDetalle = productoDetalle;
        this.productos = productos;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    @Override
    public int getGroupCount() {
        return productos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return 1;
      //  return productoDetalle.get(productos.get(groupPosition).getNombre()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return productos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
       return null;
        // return productoDetalle.get(productos.get(groupPosition).getNombre()).get(childPosition);
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
        String nombreProducto = (String) ((Producto)getGroup(groupPosition)).getNombre();
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
        String nombre = ((Producto)getGroup(groupPosition)).getNombre();
        String precio = ((Producto)getGroup(groupPosition)).getPrecio();
        String descripcion = ((Producto)getGroup(groupPosition)).getDescripcion();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.pedido_item_detalle,parent, false);

        TextView txtNombre = (TextView) convertView.findViewById(R.id.detalle_nombre_producto_label);
        txtNombre.setText(nombre);
        TextView txtPrecio = (TextView) convertView.findViewById(R.id.detalle_precio_producto);
        txtPrecio.setText("$ " + precio);
        TextView txtDescripcion = (TextView) convertView.findViewById(R.id.detalle_descripcion_producto);
        txtDescripcion.setText(descripcion);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean removeGroup(int groupPosition){
        String precio = "-"+productos.get(groupPosition).getPrecio();
        actualizarValores(precio);
        productos.remove(groupPosition);
        notifyDataSetChanged();
        return true;
    }

    public void addItem(Producto groupTitle){
        productos.add(0, groupTitle);
        actualizarValores(groupTitle.getPrecio());
        //productoDetalle.put(groupTitle.getNombre(), data);

        notifyDataSetChanged();
    }

    private void actualizarValores( String nuevo){

        int precio = Integer.parseInt(nuevo);
        int vSub = Integer.parseInt(subtotal.getText().toString()) + precio;
        int vTotal = Integer.parseInt(total.getText().toString()) + precio;
        int vIva = (int)(vTotal*0.19);

        subtotal.setText(""+(vSub));
        total.setText(""+(vTotal));
        iva.setText(""+vIva);
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

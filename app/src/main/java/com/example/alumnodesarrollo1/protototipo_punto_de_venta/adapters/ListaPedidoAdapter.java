package com.example.alumnodesarrollo1.protototipo_punto_de_venta.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.R;

import java.util.List;
import java.util.Map;

/**
 * Created by alumno.desarrollo1 on 20/07/2016.
 */
public class ListaPedidoAdapter extends BaseExpandableListAdapter {

    private Activity context;
    //el map debería ser Map<String, Objeto(Producto)>
    private Map<String, String> productoDetalle;
    private List<String> productos;

    public ListaPedidoAdapter(Activity context, Map<String, String> productoDetalle, List<String> productos){
        this.context = context;
        this.productoDetalle = productoDetalle;
        this.productos = productos;
    }

    @Override
    public int getGroupCount() {
        return productos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return productos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return productoDetalle.get(productos.get(groupPosition));
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String nombreProducto = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pedido_item, null);
        }
        TextView nombre_producto = (TextView) convertView.findViewById(R.id.lista_nombre_producto);
        nombre_producto.setText(nombreProducto);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       LayoutInflater inflater = context.getLayoutInflater();
       if(convertView == null){
           convertView = inflater.inflate(R.layout.pedido_item_detalle, null);
       }
       return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

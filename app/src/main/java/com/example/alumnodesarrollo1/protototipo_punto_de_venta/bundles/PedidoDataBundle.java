package com.example.alumnodesarrollo1.protototipo_punto_de_venta.bundles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alumno.desarrollo1 on 21/07/2016.
 */
public class PedidoDataBundle {

    public HashMap<String, List<String>> getData(){
        HashMap<String, List<String>> data = new HashMap<String, List<String>>();

        List<String> lista1 = new ArrayList<>();
        lista1.add("Detalles1");

        List<String> lista2 = new ArrayList<>();
        lista2.add("Detalles1.2");

        List<String> lista3 = new ArrayList<>();
        lista3.add("Detalles1.3");

        data.put("producto1", lista1);
        data.put("producto2", lista2);
        data.put("producto3", lista3);

        return data;
    }
}

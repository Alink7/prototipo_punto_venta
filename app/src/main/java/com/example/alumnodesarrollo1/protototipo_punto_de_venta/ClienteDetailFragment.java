package com.example.alumnodesarrollo1.protototipo_punto_de_venta;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alumnodesarrollo1.protototipo_punto_de_venta.dummy.DummyContent;
import com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos.Cliente;

/**
 * A fragment representing a single Cliente detail screen.
 * This fragment is either contained in a {@link ClienteListActivity}
 * in two-pane mode (on tablets) or a {@link ClienteDetailActivity}
 * on handsets.
 */
public class ClienteDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Cliente cliente;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClienteDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            cliente = (Cliente) getArguments().get(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(cliente.getNombre());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cliente_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (cliente != null) {
            ((TextView) rootView.findViewById(R.id.cliente_nombre)).setText(cliente.getNombre());
            ((TextView) rootView.findViewById(R.id.cliente_rut)).setText(cliente.getRut());
            ((TextView) rootView.findViewById(R.id.cliente_direccion)).setText(cliente.getDireccion());
            ((TextView) rootView.findViewById(R.id.cliente_telefono)).setText(cliente.getTelefono());
            ((TextView) rootView.findViewById(R.id.cliente_correo)).setText(cliente.getCorreo());
            ((TextView) rootView.findViewById(R.id.cliente_cupo)).setText(cliente.getCupo()+"");
        }

        return rootView;
    }
}

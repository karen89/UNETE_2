package com.unete.kvalenzuela.unete_2.itemListDetail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unete.kvalenzuela.unete_2.R;
import com.unete.kvalenzuela.unete_2.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_NAME = "item_name";
    public static final String ARG_ITEM_DESC = "item_desc";
    public static final String ARG_ITEM_RESP = "item_resp";
    public static final String ARG_ITEM_RESPCEL = "item_respcel";
    public static final String ARG_ITEM_RESPMAIL = "item_respmail";
    public static final String ARG_ITEM_CALLE = "item_calle";
    public static final String ARG_ITEM_COLONIA = "item_col";
    public static final String ARG_ITEM_CP = "item_cp";
    public static final String ARG_ITEM_LOCALIDAD = "item_localidad";

    /**
     * The dummy nombreAC this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle reicieveParams = getArguments();
        String nameac = reicieveParams.getString("nombreac");
        /*if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy nombreAC specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load nombreAC from a nombreAC provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));*/

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(nameac);
            }
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        Bundle reicieveParams = getArguments();
        String nameac = reicieveParams.getString("nombreac");
        String descripcion = reicieveParams.getString("descripcion");
        String responsable = reicieveParams.getString("responsable");
        String correo =reicieveParams.getString("email");
        String tel = reicieveParams.getString("celular");
        String calle = reicieveParams.getString("calle");
        String numero = reicieveParams.getString("numero");
        String colonia = reicieveParams.getString("colonia");
        String cp = reicieveParams.get("cp").toString();
        String localidad = reicieveParams.getString("localidad");
        String cuenta = reicieveParams.get("cuenta").toString();

        // Show the dummy nombreAC as text in a TextView.
        /*if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.txtnombreAC)).setText(mItem.nombreAC);
            ((TextView) rootView.findViewById(R.id.txtresponsable)).setText(mItem.responsable);
            ((TextView) rootView.findViewById(R.id.txtcorreo)).setText(mItem.email);
            ((TextView) rootView.findViewById(R.id.txttelefono)).setText(mItem.celular);
            ((TextView) rootView.findViewById(R.id.txtdescripcion)).setText(mItem.descripcion);
            ((TextView) rootView.findViewById(R.id.txtCuentaBancaria)).setText(mItem.cuentaBancaria);
        }*/

        ((TextView) rootView.findViewById(R.id.txtnombreAC)).setText(nameac);
        ((TextView) rootView.findViewById(R.id.txtresponsable)).setText(responsable);
        ((TextView) rootView.findViewById(R.id.txtcorreo)).setText(correo);
        ((TextView) rootView.findViewById(R.id.txttelefono)).setText(tel);
        ((TextView) rootView.findViewById(R.id.txtdescripcion)).setText(descripcion);
        ((TextView) rootView.findViewById(R.id.txtcalle)).setText(calle);
        ((TextView) rootView.findViewById(R.id.txtcolonia)).setText(colonia);
        ((TextView) rootView.findViewById(R.id.txtcp)).setText(cp);
        ((TextView) rootView.findViewById(R.id.txtlocalidad)).setText(localidad);
        ((TextView) rootView.findViewById(R.id.txtCuentaBancaria)).setText(cuenta);

        return rootView;
    }


}

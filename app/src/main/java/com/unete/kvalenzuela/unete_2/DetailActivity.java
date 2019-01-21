package com.unete.kvalenzuela.unete_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

// Capturamos los objetos gráficos que vamos a usar
    TextView descripcion;
    TextView responsable;
    TextView correo;
    TextView tel;
    TextView calle;
    TextView colonia;
    TextView cp;
    TextView localidad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        descripcion = (TextView) findViewById(R.id.txtdescripcion);
        responsable = (TextView) findViewById(R.id.txtresponsable);
        correo = (TextView) findViewById(R.id.txtcorreo);
        tel = (TextView) findViewById(R.id.txttelefono);
        calle = (TextView) findViewById(R.id.txtcalle);
        colonia = (TextView) findViewById(R.id.txtcolonia);
        cp = (TextView) findViewById(R.id.txtcp);
        localidad = (TextView) findViewById(R.id.txtlocalidad);

        Bundle reicieveParams = getIntent().getExtras();

        descripcion.setText(reicieveParams.getString("descripcion"));
        responsable.setText(reicieveParams.getString("responsable"));
        correo.setText(reicieveParams.getString("email"));
        tel.setText(reicieveParams.getString("celular"));
        calle.setText(reicieveParams.getString("calle"));
        colonia.setText(reicieveParams.getString("colonia"));
        cp.setText(reicieveParams.get("cp").toString());
        localidad.setText(reicieveParams.getString("localidad"));

    }
}

package com.unete.kvalenzuela.unete_2.api.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.unete.kvalenzuela.unete_2.api.model.Asociacion;

import java.util.HashMap;

public class SessionPrefs {
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;

    public static final String PREFS_NAME = "UNETE_PREFS";
    public static final String PREF_AC_ID = "PREF_AC_ID";
    public static final String PREF_AC_RAZONSOCIAL = "PREF_AC_RAZONSOCIAL";
    public static final String PREF_AC_DESCRIPCION = "PREF_AC_DESCRIPCION";

    public static final String PREF_CALLE = "PREF_CALLE";
    public static final String PREF_NUMERO = "PREF_NUMERO";
    public static final String PREF_CRUZAM_1 = "PREF_CRUZAM_1";
    public static final String PREF_CRUZAM_2 = "PREF_CRUZAM_2";
    public static final String PREF_COLONIA = "PREF_COLONIA";
    public static final String PREF_CP = "PREF_CP";
    public static final String PREF_MUNICIPIO = "PREF_MUNICIPIO";
    public static final String PREF_ESTADO = "PREF_ESTADO";

    public static final String PREF_AC_REPNOMBRE = "PREF_AC_REPNOMBRE";
    public static final String PREF_AC_REPCEL = "PREF_AC_REPCEL";
    public static final String PREF_AC_REPCORREO = "PREF_AC_REPCORREO";

    public static final String PREF_AC_LOGOTIPO = "PREF_AC_LOGOTIPO";
    public static final String PREF_AC_ESTATUS = "PREF_AC_ESTATUS";
    public static final String PREF_AC_CUENTABANCARIA = "PREF_AC_CUENTABANCARIA";
    public static final String PREF_AC_SUBCATEGORIA_ID = "PREF_AC_SUBCATEGORIA_ID";
    public static final String PREF_AC_TOKEN = "PREF_AC_TOKEN";

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    public SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = mPrefs.edit();
        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_AC_TOKEN, null));
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    /**Save AC at Prefs* */
    public void createSession(Asociacion affiliate) {
        if (affiliate != null) {
            editor.putInt(PREF_AC_ID, affiliate.getId_AC());
            editor.putString(PREF_AC_RAZONSOCIAL, affiliate.getRazon_Social());
            editor.putString(PREF_AC_DESCRIPCION, affiliate.getDescripcion());

            editor.putString(PREF_CALLE, affiliate.getCalle());
            editor.putString(PREF_NUMERO, affiliate.getNumero());
            editor.putString(PREF_CRUZAM_1, affiliate.getCruzam_1());
            editor.putString(PREF_CRUZAM_2, affiliate.getCruzam_2());
            editor.putString(PREF_COLONIA, affiliate.getColonia());
            editor.putInt(PREF_CP, affiliate.getCP());
            editor.putString(PREF_MUNICIPIO, affiliate.getMunicipio());
            editor.putString(PREF_ESTADO, affiliate.getEstado());

            editor.putString(PREF_AC_REPNOMBRE, affiliate.getRep_Nombre());
            editor.putString(PREF_AC_REPCEL, affiliate.getRep_Cel());
            editor.putString(PREF_AC_REPCORREO, affiliate.getRep_Correo());

            editor.putString(PREF_AC_LOGOTIPO, affiliate.getLogotipo());
            editor.putString(PREF_AC_ESTATUS, affiliate.getEstatus());
            editor.putInt(PREF_AC_CUENTABANCARIA, affiliate.getCuentaBancaria());
            editor.putInt(PREF_AC_SUBCATEGORIA_ID, affiliate.getSubcategoria_Id_SubCat());
            editor.putString(PREF_AC_TOKEN, affiliate.getToken());
            editor.apply();

            mIsLoggedIn = true;
        }
    }
    
    /**Get stored session data* */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(PREF_AC_RAZONSOCIAL, mPrefs.getString(PREF_AC_RAZONSOCIAL, null));
        user.put(PREF_AC_DESCRIPCION, mPrefs.getString(PREF_AC_DESCRIPCION, null));

        user.put(PREF_CALLE, mPrefs.getString(PREF_CALLE, null));
        user.put(PREF_NUMERO, mPrefs.getString(PREF_NUMERO, null));
        user.put(PREF_CRUZAM_1, mPrefs.getString(PREF_CRUZAM_1, null));
        user.put(PREF_CRUZAM_2, mPrefs.getString(PREF_CRUZAM_2, null));
        user.put(PREF_COLONIA, mPrefs.getString(PREF_COLONIA, null));
        user.put(PREF_CP, String.valueOf(mPrefs.getInt(PREF_CP, 0)));
        user.put(PREF_MUNICIPIO, mPrefs.getString(PREF_MUNICIPIO, null));
        user.put(PREF_ESTADO, mPrefs.getString(PREF_ESTADO, null));

        user.put(PREF_AC_REPNOMBRE, mPrefs.getString(PREF_AC_REPNOMBRE, null));
        user.put(PREF_AC_REPCEL, mPrefs.getString(PREF_AC_REPCEL, null));
        user.put(PREF_AC_REPCORREO, mPrefs.getString(PREF_AC_REPCORREO, null));

        user.put(PREF_AC_CUENTABANCARIA, String.valueOf(mPrefs.getInt(PREF_AC_CUENTABANCARIA, 0)));
        user.put(PREF_AC_SUBCATEGORIA_ID, String.valueOf(mPrefs.getInt(PREF_AC_SUBCATEGORIA_ID, 0)));
        user.put(PREF_AC_TOKEN, mPrefs.getString(PREF_AC_TOKEN, null));

        return user;
    }

    /**Clear session details* */
    public void logOut(){
        mIsLoggedIn = false;
        editor.clear();

        /*editor.putString(PREF_AC_ID, null);
        editor.putString(PREF_AC_RAZONSOCIAL, null);
        editor.putString(PREF_AC_DESCRIPCION, null);

        editor.putString(PREF_CALLE, null);
        editor.putString(PREF_NUMERO, null);
        editor.putString(PREF_CRUZAM_1, null);
        editor.putString(PREF_CRUZAM_2, null);
        editor.putString(PREF_COLONIA, null);
        editor.putString(PREF_CP, null);
        editor.putString(PREF_MUNICIPIO, null);
        editor.putString(PREF_ESTADO, null);

        editor.putString(PREF_AC_REPNOMBRE, null);
        editor.putString(PREF_AC_REPCEL, null);
        editor.putString(PREF_AC_REPCORREO, null);

        editor.putString(PREF_AC_LOGOTIPO, null);
        editor.putString(PREF_AC_ESTATUS, null);
        editor.putInt(PREF_AC_CUENTABANCARIA, 0);
        editor.putInt(PREF_AC_SUBCATEGORIA_ID, 0);
        editor.putString(PREF_AC_TOKEN, null);*/

        editor.apply();
    }

}

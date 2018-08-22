package com.unete.kvalenzuela.unete_2.api.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.unete.kvalenzuela.unete_2.api.Asociacion;

public class SessionPrefs {
    public static final String PREFS_NAME = "UNETE_PREFS";
    public static final String PREF_AC_ID = "PREF_USER_ID";
    public static final String PREF_AC_RAZONSOCIAL = "PREF_AC_RAZONSOCIAL";
    public static final String PREF_AC_REPNOMBRE = "PREF_AC_REPNOMBRE";
    public static final String PREF_AC_REPCEL = "PREF_AC_REPCEL";
    public static final String PREF_AC_REPCORREO = "PREF_AC_REPCORREO";
    public static final String PREF_AC_USUARIO = "PREF_AC_USUARIO";
    public static final String PREF_AC_SLOGAN = "PREF_AC_SLOGAN";
    public static final String PREF_AC_DESCRIPCION = "PREF_AC_DESCRIPCION";
    public static final String PREF_AC_LOGOTIPO = "PREF_AC_LOGOTIPO";
    public static final String PREF_AC_ESTADO = "PREF_AC_ESTADO";
    public static final String PREF_AC_CUENTABANCARIA = "PREF_AC_CUENTABANCARIA";
    public static final String PREF_AC_SUBCATEGORIA_ID = "PREF_AC_SUBCATEGORIA_ID";
    public static final String PREF_AC_TOKEN = "PREF_AC_TOKEN";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_AC_TOKEN, null));
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void saveAffiliate(Asociacion affiliate) {
        if (affiliate != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(PREF_AC_ID, affiliate.getId_AC());
            editor.putString(PREF_AC_RAZONSOCIAL, affiliate.getRazon_Social());
            editor.putString(PREF_AC_REPNOMBRE, affiliate.getRep_Nombre());
            editor.putString(PREF_AC_REPCEL, affiliate.getRep_Cel());
            editor.putString(PREF_AC_REPCORREO, affiliate.getRep_Correo());
            editor.putString(PREF_AC_USUARIO, affiliate.getUsuario());
            editor.putString(PREF_AC_SLOGAN, affiliate.getSlogan());
            editor.putString(PREF_AC_DESCRIPCION, affiliate.getDescripcion());
            editor.putString(PREF_AC_LOGOTIPO, affiliate.getLogotipo());
            editor.putString(PREF_AC_ESTADO, affiliate.getEstado());
            editor.putInt(PREF_AC_CUENTABANCARIA, affiliate.getCuentaBancaria());
            editor.putInt(PREF_AC_SUBCATEGORIA_ID, affiliate.getSubcategoria_Id_SubCat());
            editor.putString(PREF_AC_TOKEN, affiliate.getToken());
            editor.apply();

            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putString(PREF_AC_ID, null);
        editor.putString(PREF_AC_RAZONSOCIAL, null);
        editor.putString(PREF_AC_REPNOMBRE, null);
        editor.putString(PREF_AC_REPCEL, null);
        editor.putString(PREF_AC_REPCORREO, null);
        editor.putString(PREF_AC_USUARIO, null);
        editor.putString(PREF_AC_SLOGAN, null);
        editor.putString(PREF_AC_DESCRIPCION, null);
        editor.putString(PREF_AC_LOGOTIPO, null);
        editor.putString(PREF_AC_ESTADO, null);
        editor.putInt(PREF_AC_CUENTABANCARIA, 0);
        editor.putInt(PREF_AC_SUBCATEGORIA_ID, 0);
        editor.putString(PREF_AC_TOKEN, null);
        editor.apply();
    }

}

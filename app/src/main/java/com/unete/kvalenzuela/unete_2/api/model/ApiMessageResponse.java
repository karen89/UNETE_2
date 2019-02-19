package com.unete.kvalenzuela.unete_2.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * POJO para respuestas de la API de tipo mensaje
 */

public class ApiMessageResponse {

    @SerializedName("Estatus")
    private String mStatus;
    @SerializedName("message")
    private String mMessage;

    public ApiMessageResponse(String status, String message) {
        mStatus = status;
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getMessage() {
        return mMessage;
    }

}

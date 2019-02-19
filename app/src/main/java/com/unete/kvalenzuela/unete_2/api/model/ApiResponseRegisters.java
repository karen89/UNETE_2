package com.unete.kvalenzuela.unete_2.api.model;

import java.util.List;

/**
 * Entidad de dominio para recibir respuesta de la API en el recurso "appointments"
 */

public class ApiResponseRegisters {

    private List<RegistersDisplayList> results;

    public ApiResponseRegisters(List<RegistersDisplayList> results) {
        this.results = results;
    }

    public List<RegistersDisplayList> getResults() {
        return results;
    }

}

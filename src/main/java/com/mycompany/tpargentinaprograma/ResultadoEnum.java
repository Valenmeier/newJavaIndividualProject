package com.mycompany.tpargentinaprograma;

public enum ResultadoEnum {
    GANADOR,
    EMPATE,
    PERDEDOR;

    public static ResultadoEnum fromCsvString(String gana1, String empata, String gana2) {
        if ("X".equals(gana1)) {
            return GANADOR;
        } else if ("X".equals(empata)) {
            return EMPATE;
        } else {
            return PERDEDOR;
        }
    }
}

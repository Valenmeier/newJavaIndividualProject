package com.mycompany.tpargentinaprograma;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PronosticoTest {

    @Test
    public void testPuntosCalculation() {
        Equipo equipo1 = new Equipo("Equipo1");
        Equipo equipo2 = new Equipo("Equipo2");

        // Creación de partidos de prueba
        Partido partido1 = new Partido(1, 1, equipo1, 2, equipo2, 1);
        Partido partido2 = new Partido(2, 1, equipo1, 1, equipo2, 1);

        // Creación de pronósticos de prueba
        Pronostico pronostico1 = new Pronostico("Participante1", partido1, ResultadoEnum.GANADOR);
        Pronostico pronostico2 = new Pronostico("Participante1", partido2, ResultadoEnum.EMPATE);

        // Verificación de puntos
        int puntosPronostico1 = pronostico1.puntos();
        int puntosPronostico2 = pronostico2.puntos();

        assertEquals(1, puntosPronostico1, "Los puntos del pronóstico 1 deben ser 1");
        assertEquals(6, puntosPronostico2, "Los puntos del pronóstico 2 deben ser 6");
    }
    
     @Test
    public void roundAndFasesCalculation() {
        Equipo equipo1 = new Equipo("Equipo1");
        Equipo equipo2 = new Equipo("Equipo2");

        // Creación de partidos de prueba
        Partido partido1 = new Partido(1, 1, equipo1, 2, equipo2, 1);
        Partido partido2 = new Partido(2, 1, equipo1, 1, equipo2, 1);
        Partido partido3 = new Partido(3, 2, equipo1, 0, equipo2, 0);
        Partido partido4 = new Partido(4, 2, equipo1, 3, equipo2, 2);

        // Creación de pronósticos de prueba
        Pronostico pronostico1 = new Pronostico("Participante1", partido1, ResultadoEnum.GANADOR);
        Pronostico pronostico2 = new Pronostico("Participante1", partido2, ResultadoEnum.EMPATE);
        Pronostico pronostico3 = new Pronostico("Participante1", partido3, ResultadoEnum.EMPATE);
        Pronostico pronostico4 = new Pronostico("Participante1", partido4, ResultadoEnum.GANADOR);

        // Verificación de puntos
        int puntosPronostico1 = pronostico1.puntos();
        int puntosPronostico2 = pronostico2.puntos();
        int puntosPronostico3 = pronostico3.puntos();
        int puntosPronostico4 = pronostico4.puntos();

        assertEquals(1, puntosPronostico1, "Los puntos del pronóstico 1 deben ser 1");
        assertEquals(6, puntosPronostico2, "Los puntos del pronóstico 2 deben ser 6 (1 + 5)");
        assertEquals(1, puntosPronostico3, "Los puntos del pronóstico 3 deben ser 1");
        assertEquals(16, puntosPronostico4, "Los puntos del pronóstico 4 deben ser 16 (1 + 5 + 10)");
    }
}

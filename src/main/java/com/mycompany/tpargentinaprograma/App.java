package com.mycompany.tpargentinaprograma;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try (Connection connection = Database.getConnection()) {
            List<Partido> partidos = Partido.fetchFromDatabase(connection);
            List<Pronostico> pronosticos = Pronostico.fetchFromDatabase(connection, partidos);

            Map<String, Integer> puntosPorParticipante = new HashMap<>();

            for (Pronostico pronostico : pronosticos) {
                String participante = pronostico.getParticipante();
                int puntos = pronostico.puntos();
                
                puntosPorParticipante.put(participante, puntosPorParticipante.getOrDefault(participante, 0) + puntos);
            }
            for (Map.Entry<String, Integer> entry : puntosPorParticipante.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }
}

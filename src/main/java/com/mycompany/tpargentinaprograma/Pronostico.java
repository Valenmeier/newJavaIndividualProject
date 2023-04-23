package com.mycompany.tpargentinaprograma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pronostico {

    private int idPartido;
    private final String participante;
    private final Partido partido;
    private final ResultadoEnum resultado;
    private int ronda;
    private static int rondaActual = 0;
    private static int totalAciertosRonda = 0;
    private static int totalAciertosFases = 0;

    public Pronostico(String participante, Partido partido, ResultadoEnum resultado) {
        this.participante = participante;
        this.partido = partido;
        this.resultado = resultado;
        this.idPartido = idPartido;
        this.ronda = ronda;
    }

    public String getParticipante() {
        return participante;
    }

    public Partido getPartido() {
        return partido;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public int getRonda() {
        return ronda;
    }

    public int puntos() {
        int aciertoPuntos = 1;
        int cantidadDePartidosRonda = 2;
        int cantidadDeRondasFase = 2;
        int recompensaRonda = 5;
        int recompensaFases = 10;

        if (partido.getRonda() != rondaActual) {
            totalAciertosRonda = 0;
            rondaActual = partido.getRonda();
            if (rondaActual == 1) {
                totalAciertosFases = 0;
            }
        }

        if (partido.resultado(partido.getEquipo1()) == resultado) {
            totalAciertosRonda++;
            if (totalAciertosRonda == cantidadDePartidosRonda) {
                totalAciertosFases++;
                if (totalAciertosFases % cantidadDeRondasFase == 0) {
                    return aciertoPuntos + recompensaRonda + recompensaFases;
                }
                return aciertoPuntos + recompensaRonda;
            } else {
                return aciertoPuntos;
            }
        } 
        return 0;
    }

    public static List<Pronostico> fetchFromDatabase(Connection connection, List<Partido> partidos) throws SQLException {
        List<Pronostico> pronosticos = new ArrayList<>();
        String query = "SELECT * FROM tpargentinaprograma.pronosticos";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String participante = resultSet.getString("Participante");
                int id_partido = resultSet.getInt("Id_Partido");
                Partido partido = partidos.stream()
                        .filter(p -> p.getId() == id_partido)
                        .findFirst()
                        .orElseThrow(() -> new SQLException("Partido no encontrado con ID " + id_partido));
                String gana1 = resultSet.getString("Gana_1");
                String empata = resultSet.getString("Empata");
                String gana2 = resultSet.getString("Gana_2");

                ResultadoEnum resultado = ResultadoEnum.fromCsvString(gana1, empata, gana2);
                
                
                pronosticos.add(new Pronostico(participante, partido, resultado));
            }
        }
        return pronosticos;
    }
}

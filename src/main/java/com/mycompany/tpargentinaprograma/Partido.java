package com.mycompany.tpargentinaprograma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Partido {

    private final int id;
    private final int ronda;
    private final Equipo equipo1;
    private final Equipo equipo2;
    private final int golesEquipo1;
    private final int golesEquipo2;

   public Partido(int id, int ronda, Equipo equipo1, int golesEquipo1, Equipo equipo2, int golesEquipo2) {
    this.id = id;
    this.equipo1 = equipo1;
    this.equipo2 = equipo2;
    this.golesEquipo1 = golesEquipo1;
    this.golesEquipo2 = golesEquipo2;
    this.ronda = ronda;
}


    public int getId() {
        return id;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public int getRonda() {
        return ronda;
    }

    public ResultadoEnum resultado(Equipo equipo) {
        if (golesEquipo1 == golesEquipo2) {
            return ResultadoEnum.EMPATE;
        } else if (equipo.equals(equipo1)) {
            return golesEquipo1 > golesEquipo2 ? ResultadoEnum.GANADOR : ResultadoEnum.PERDEDOR;
        } else {
            return golesEquipo1 < golesEquipo2 ? ResultadoEnum.GANADOR : ResultadoEnum.PERDEDOR;
        }
    }


    public static List<Partido> fetchFromDatabase(Connection connection) throws SQLException {
        List<Partido> partidos = new ArrayList<>();
        String query = "SELECT * FROM tpargentinaprograma.resultados";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int ronda = resultSet.getInt("Ronda");
                Equipo equipo1 = new Equipo(resultSet.getString("Equipo_1"));
                int goles_equipo1 = resultSet.getInt("Cant_Goles_1");
                int goles_equipo2 = resultSet.getInt("Cant_Goles_2");
                Equipo equipo2 = new Equipo(resultSet.getString("Equipo_2"));
                partidos.add(new Partido(id, ronda, equipo1, goles_equipo1, equipo2, goles_equipo2));
            }
        }
        return partidos;
    }
}

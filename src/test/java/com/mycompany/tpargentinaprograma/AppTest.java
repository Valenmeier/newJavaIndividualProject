package com.mycompany.tpargentinaprograma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = Database.getConnection();
    }

    @Test
    public void testFetchPartidosFromDatabase() throws SQLException {
        List<Partido> partidos = Partido.fetchFromDatabase(connection);

        String query = "SELECT COUNT(*) FROM tpargentinaprograma.resultados";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(count, partidos.size(), "El tamaño de la lista de partidos debe coincidir con la cantidad de registros en la base de datos");
        }
    }

    @Test
    public void testFetchPronosticosFromDatabase() throws SQLException {
        List<Partido> partidos = Partido.fetchFromDatabase(connection);
        List<Pronostico> pronosticos = Pronostico.fetchFromDatabase(connection, partidos);

        String query = "SELECT COUNT(*) FROM tpargentinaprograma.pronosticos";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(count, pronosticos.size(), "El tamaño de la lista de pronósticos debe coincidir con la cantidad de registros en la base de datos");
        }
    }
}

package com.mycompany.tpargentinaprograma;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            List<ResultadoCsv> resultadosCsv = new CsvToBeanBuilder<ResultadoCsv>(new FileReader("src/main/resources/resultados.csv"))
                    .withType(ResultadoCsv.class).withSeparator(';').build().parse();
            List<PronosticoCsv> pronosticosCsv = new CsvToBeanBuilder<PronosticoCsv>(new FileReader("src/main/resources/pronosticos.csv"))
                    .withType(PronosticoCsv.class).withSeparator(';').build().parse();

            List<Partido> partidos = Partido.createFromCsvList(resultadosCsv);
            List<Pronostico> pronosticos = Pronostico.createFromCsvList(pronosticosCsv, partidos);

            Map<String, Integer> puntosPorParticipante = new HashMap<>();
            for (Pronostico pronostico : pronosticos) {
                String participante = pronostico.getParticipante();
                int puntos = pronostico.puntos();
                puntosPorParticipante.put(participante, puntosPorParticipante.getOrDefault(participante, 0) + puntos);
            }

            for (Map.Entry<String, Integer> entry : puntosPorParticipante.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivos CSV: " + e.getMessage());
        }
    }
}
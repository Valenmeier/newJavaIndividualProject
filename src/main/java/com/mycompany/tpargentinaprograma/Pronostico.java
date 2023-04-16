package com.mycompany.tpargentinaprograma;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pronostico {
    private int idPartido;
    private String participante;
    private Partido partido;
    private ResultadoEnum resultado;

    public Pronostico(String participante, Partido partido, ResultadoEnum resultado, int idPartido) {
        this.participante = participante;
        this.partido = partido;
        this.resultado = resultado;
        this.idPartido = idPartido;
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

    public int puntos() {
        return partido.resultado(partido.getEquipo1()) == resultado ? 1 : 0;
    }

    public static List<Pronostico> createFromCsvList(List<PronosticoCsv> pronosticosCsv, List<Partido> partidos) {
        Map<Integer, Partido> partidoById = partidos.stream().collect(Collectors.toMap(Partido::getId, p -> p));

        List<Pronostico> pronosticos = new ArrayList<>();
        for (PronosticoCsv pronosticoCsv : pronosticosCsv) {
            Partido partido = partidoById.get(pronosticoCsv.getIdPartido());
            ResultadoEnum resultado = ResultadoEnum.fromCsvString(pronosticoCsv.getGana1(), pronosticoCsv.getEmpata(), pronosticoCsv.getGana2());
            Pronostico pronostico = new Pronostico(pronosticoCsv.getParticipante(), partido, resultado, pronosticoCsv.getIdPartido());
            pronosticos.add(pronostico);
        }
        return pronosticos;
    }
}
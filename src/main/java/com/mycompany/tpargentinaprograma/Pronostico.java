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
    private int ronda;
    private static int rondaActual = 0;
    private static int totalAciertosRonda = 0;
    private static int totalAciertosFases=0;

    public Pronostico(String participante, Partido partido, ResultadoEnum resultado, int idPartido, int ronda) {
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
        int cantidadDeRondasFase=2;
        int recompensaRonda = 5;
        int recompensaFases = 10;

        if (partido.getRonda() != rondaActual) {
            totalAciertosRonda = 0;
            rondaActual = partido.getRonda();
            if(rondaActual==1){
                totalAciertosFases=0;
            }
        }
        
        if (partido.resultado(partido.getEquipo1()) == resultado) {
            totalAciertosRonda++;
            if (totalAciertosRonda == cantidadDePartidosRonda) {
                totalAciertosFases++;
                if(totalAciertosFases%cantidadDeRondasFase==0){
                    return aciertoPuntos + recompensaRonda + recompensaFases;
                }
                return aciertoPuntos + recompensaRonda;
            }else{
                return aciertoPuntos;
            }
        }
        return 0;
    }

    public static List<Pronostico> createFromCsvList(List<PronosticoCsv> pronosticosCsv, List<Partido> partidos) {
        Map<Integer, Partido> partidoById = partidos.stream().collect(Collectors.toMap(Partido::getId, p -> p));

        List<Pronostico> pronosticos = new ArrayList<>();
        for (PronosticoCsv pronosticoCsv : pronosticosCsv) {
            Partido partido = partidoById.get(pronosticoCsv.getIdPartido());
            ResultadoEnum resultado = ResultadoEnum.fromCsvString(pronosticoCsv.getGana1(), pronosticoCsv.getEmpata(), pronosticoCsv.getGana2());
            Pronostico pronostico = new Pronostico(pronosticoCsv.getParticipante(), partido, resultado, pronosticoCsv.getIdPartido(), partido.getRonda());
            pronosticos.add(pronostico);
        }
        return pronosticos;
    }
}

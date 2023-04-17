package com.mycompany.tpargentinaprograma;

import java.util.ArrayList;
import java.util.List;

public class Partido {
    private int id;
    private int ronda;
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(int id,Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2, int ronda) {
        this.id = id;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.ronda=ronda;
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
    public int getRonda(){
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

    public static List<Partido> createFromCsvList(List<ResultadoCsv> resultadosCsv) {
        List<Partido> partidos = new ArrayList<>();
        for (ResultadoCsv resultadoCsv : resultadosCsv) {
            Equipo equipo1 = new Equipo(resultadoCsv.getEquipo1());
            Equipo equipo2 = new Equipo(resultadoCsv.getEquipo2());
            Partido partido = new Partido(resultadoCsv.getId(), equipo1, equipo2, resultadoCsv.getCantGoles1(), resultadoCsv.getCantGoles2(),resultadoCsv.getRonda());
            partidos.add(partido);
        }
        return partidos;
    }
}
package com.mycompany.tpargentinaprograma;import com.opencsv.bean.CsvBindByName;public class ResultadoCsv {    @CsvBindByName(column = "ID")    private int id;    @CsvBindByName(column = "Ronda")    private int ronda;    @CsvBindByName(column = "Equipo 1")    private String equipo1;    @CsvBindByName(column = "Cant. Goles 1")    private int cantGoles1;    @CsvBindByName(column = "Cant. Goles 2")    private int cantGoles2;    @CsvBindByName(column = "Equipo 2")    private String equipo2;    public int getId() {        return id;    }    public void setId(int id) {        this.id = id;    }    public int getRonda() {        return ronda;    }    public void setRonda(int ronda) {        this.ronda = ronda;    }    public String getEquipo1() {        return equipo1;    }    public void setEquipo1(String equipo1) {        this.equipo1 = equipo1;    }    public int getCantGoles1() {        return cantGoles1;    }    public void setCantGoles1(int cantGoles1) {        this.cantGoles1 = cantGoles1;    }    public int getCantGoles2() {        return cantGoles2;    }    public void setCantGoles2(int cantGoles2) {        this.cantGoles2 = cantGoles2;    }    public String getEquipo2() {        return equipo2;    }    public void setEquipo2(String equipo2) {        this.equipo2 = equipo2;    }}
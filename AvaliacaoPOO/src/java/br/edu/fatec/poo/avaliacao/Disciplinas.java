/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatec.poo.avaliacao;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author biancasobral
 */
public class Disciplinas {
    private String name;
    private String ementa;
    private int ciclo;
    private double nota;

    public Disciplinas(String name, String ementa, int ciclo) {
        this.name = name;
        this.ementa = ementa;
        this.ciclo = ciclo;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    public static ArrayList<Disciplinas> getList(){
     ArrayList<Disciplinas> list = new ArrayList<>();
     Connection con = null;
     Statement stmt = null;
     ResultSet rs = null;
     
        try {
            con = DbListener.getConnection();
            stmt = con.createStatement();
            stmt.execute("SELECT * FROM disciplinas");
                while(rs.next()){
                    list.add(new Disciplinas(
                            rs.getString("nome"),
                            rs.getString("ementa"), 
                            rs.getInt("ciclo")));
                }
        } catch (Exception e) {
            DbListener.exception = e;
        } finally {
            try {con.close();} catch (Exception e) {}
            try {stmt.close();} catch (Exception e) {}
        }
    
     return list;
    }
    
    public static String getCreateStatement(){
    return "CREATE TABLE IF NOT EXISTS disciplinas ("
                + "nome VARCHAR(200) PRIMARY KEY,"
                + "ementa VARCHAR(200),"
                + "ciclo INTEGER "
                +  ")";
    }
    
    public static void insertDisciplina(String nome, String ementa, int ciclo) {
        try {
            Connection con = DbListener.getConnection();
            Statement stmt = con.createStatement();
            stmt.execute("insert into disciplinas values('"+nome+"','"+ementa+"',"+ciclo+""
            + ")" );
        } catch (Exception e) {
        }
    }
    
}

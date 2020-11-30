/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatec.poo.avaliacao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

/**
 * Web application lifecycle listener.
 *
 * @author biancasobral
 */
public class DbListener implements ServletContextListener {
    public static String DRIVER_CLASS = "org.sqlite.JDBC";
    public static String DATABASE_URL = "jdbc:sqlite:avaliacaoP2.db ";
    
    public static Exception exception = null;
    
    public static Connection getConnection() throws Exception{
    return DriverManager.getConnection(DATABASE_URL);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection con = null;
        Statement stmt = null;
        
        try {
            Class.forName(DRIVER_CLASS);
            con = getConnection();
            stmt = con.createStatement();
            stmt.execute(Disciplinas.getCreateStatement());
            if (Disciplinas.getList() == null){
                stmt.execute("INSERT INTO diciplinas VALUES("
                        + "'Programacao Orientada a Objeto'"
                        + "'Ementa blabla'"
                        + "4"
                        + ")");
                        stmt.execute("INSERT INTO diciplinas VALUES("
                        + "'Gestao de Projetos'"
                        + "'Ementa blabla'"
                        + "6"
                        + ")");
            }
            con.close();
            stmt.close();
        } catch (Exception e) {
        } finally {
            try {con.close();} catch (Exception e) {}
            try {stmt.close();} catch (Exception e) {}
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

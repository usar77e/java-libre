package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestManejoPersonas {
    public static void main(String[] args) {
        
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
        
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            
            Persona cambioPersona = new Persona();
            cambioPersona.setIdPersona(2);
            cambioPersona.setNombre("Karla");
            cambioPersona.setApellido("Riquelme");
            cambioPersona.setEmail("maca.r@micorreo.cl");
            cambioPersona.setTelefono("'933298648'");
            personaDAO.actualizar(cambioPersona);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Carlos");
            nuevaPersona.setApellido("Ramireza");
            nuevaPersona.setEmail("cramirez@micorreo.cl");
            nuevaPersona.setTelefono("933478641");
            personaDAO.insertar(nuevaPersona);
            
            conexion.commit();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback ");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}

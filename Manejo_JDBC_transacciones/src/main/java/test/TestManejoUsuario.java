package test;

import datos.Conexion;
import datos.UsuarioDAO;
import domain.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TestManejoUsuario {
    public static void main(String[] args) {
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            
            Usuario cambiarUsuario = new Usuario();
            cambiarUsuario.setIdUsuario(2);
            cambiarUsuario.setNombre("Administrativob");
            cambiarUsuario.setPassword("12345");
            usuarioDAO.actualizar(cambiarUsuario);
            
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("Supervisor");
            nuevoUsuario.setPassword("abc123");
            usuarioDAO.insertar(nuevoUsuario);
            
            conexion.commit();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al Rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        
        
    }
}

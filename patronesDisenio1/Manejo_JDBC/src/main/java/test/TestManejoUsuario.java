package test;

import datos.Conexion;
import datos.UsuarioDAO;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;
import java.sql.*;
import java.util.List;
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
            
            UsuarioDAO usuarioDao = new UsuarioDaoJDBC(conexion);
            List<UsuarioDTO> usuarios = usuarioDao.seleccionar();
            
            for(UsuarioDTO usuario: usuarios){
                System.out.println("Usuario DTO = " + usuario);
            }
            
            
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

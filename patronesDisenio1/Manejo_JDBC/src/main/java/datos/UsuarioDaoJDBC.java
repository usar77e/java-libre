package datos;

import domain.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDaoJDBC implements UsuarioDAO{
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT id_usuario, nombre, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    
    public UsuarioDaoJDBC(){}
    
    public UsuarioDaoJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<UsuarioDTO> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO usuario = null;
        List<UsuarioDTO> usuarios = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                usuario = new UsuarioDTO(idUsuario, nombre, password);
                usuarios.add(usuario);
            }
        //para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return usuarios;
    }

    @Override
    public int insertar(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            
            registros = stmt.executeUpdate();
        /* para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);*/
        }
        finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }

    @Override
    public int actualizar(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
        /* para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);*/
        }
        finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }

    @Override
    public int eliminar(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
        /* para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);*/
        }
        finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }
 
    
}

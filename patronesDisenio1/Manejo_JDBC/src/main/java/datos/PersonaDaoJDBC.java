package datos;

import static datos.Conexion.*;
import domain.PersonaDTO;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDaoJDBC implements PersonaDAO{
    
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, mail, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, mail, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, mail = ?, telefono = ? WHERE id_persona= ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona= ?";
    
    public PersonaDaoJDBC(){}
    
    public PersonaDaoJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<PersonaDTO> select() {
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String mail = rs.getString("mail");
                String telefono = rs.getString("telefono");
                persona = new PersonaDTO(idPersona, nombre, apellido, mail, telefono);
                personas.add(persona);
            }
         
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
        finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }

    @Override
    public int insert(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            
            registros = stmt.executeUpdate();
        /* para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);*/
        }
        finally{
            try {
                Conexion.close(stmt);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }

    @Override
    public int update(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            
            registros = stmt.executeUpdate();
        
        /* para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);*/
        }
        finally{
            try {
                Conexion.close(stmt);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }

    @Override
    public int delete(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            
            registros = stmt.executeUpdate();
        /* para uso en el roolback - en caso de des-comentar(? sacar el throws SQLException        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);*/
        }
        finally{
            try {
                Conexion.close(stmt);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }

    
    
}

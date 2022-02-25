package domain;

public class UsuarioDTO {
    private int idUsuario;
    private String nombre;
    private String password;

    public UsuarioDTO(int idUsuario, String nombre, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
    }

    public UsuarioDTO(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public UsuarioDTO(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public UsuarioDTO() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", password=" + password + '}';
    }

    
    
}

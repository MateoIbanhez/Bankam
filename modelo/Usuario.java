package modelo;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String password;
    private String fechaNacimiento;
    private String numeroTel;
    private String fechaCreacion;
    private String genero;
    private String estado;
    

    public Usuario(int idUsuario, String nombre, String primerApellido, String segundoApellido, String password, String fechaNacimiento, String numeroTel, String fechaCreacion, String genero, String estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroTel = numeroTel;
        this.fechaCreacion = fechaCreacion;
        this.genero = genero;
        this.estado = estado;
    }
    public Usuario() {
        this.idUsuario = 0;
        this.nombre = "";
        this.primerApellido = "";
        this.segundoApellido = "";
        this.password = "";
        this.fechaNacimiento = "";
        this.numeroTel = "";
        this.fechaCreacion = "";
        this.genero = "";
        this.estado = "";
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

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}

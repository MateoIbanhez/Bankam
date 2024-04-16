package modelo;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String password;
    private String documentoIdentificacion;
    private String fechaNacimiento;
    private String numeroTel;
    private String correo;
    private String calle;
    private int numeroCalle;
    private int piso;
    private String letra;
    private String fechaCreacion;
    private int genero;
    private int estado;

    public Usuario(int idUsuario, String nombre, String primerApellido, String segundoApellido, String password, String documentoIdentificacion, String fechaNacimiento, String numeroTel, String correo, String calle, int numeroCalle, int piso,String letra, String fechaCreacion, int genero, int estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.documentoIdentificacion  = documentoIdentificacion ;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroTel = numeroTel;
        this.correo = correo;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.piso = piso;
        this.letra = letra;
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
        this.documentoIdentificacion = "";
        this.fechaNacimiento = "";
        this.numeroTel = "";
        this.correo = "";
        this.calle = "";
        this.numeroCalle = 0;
        this.piso = 0;
        this.letra = "";
        this.fechaCreacion = "";
        this.genero = 0;
        this.estado = 0;
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

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    
    

    
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}

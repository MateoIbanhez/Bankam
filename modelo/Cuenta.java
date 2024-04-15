package modelo;

public class Cuenta {
    private int idCuenta;
    private String titularCuenta;
    private String pais;
    private int codControlPais;
    private String entidad;
    private String oficina;
    private int codControlCuenta;
    private int numeroCuenta;
    private double saldo;
    private String tipoMoneda;
    private int idTarjeta;

    
    public Cuenta(int idCuenta, String titularCuenta, String pais, int codControlPais, String entidad, String oficina, int codControlCuenta, int numeroCuenta, double saldo, String tipoMoneda, int idTarjeta) {
        this.idCuenta = idCuenta;
        this.titularCuenta = titularCuenta;
        this.pais = pais;
        this.codControlPais = codControlPais;
        this.entidad = entidad;
        this.oficina = oficina;
        this.codControlCuenta = codControlCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoMoneda = tipoMoneda;
        this.idTarjeta = idTarjeta;
    }

    public Cuenta() {
        this.idCuenta = 0;
        this.titularCuenta = "";
        this.pais = "";
        this.codControlPais = 0;
        this.entidad = "";
        this.oficina = "";
        this.codControlCuenta = 0;
        this.numeroCuenta = 0;
        this.saldo = 0.00;
        this.tipoMoneda = "";
        this.idTarjeta = 0;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCodControlPais() {
        return codControlPais;
    }

    public void setCodControlPais(int codControlPais) {
        this.codControlPais = codControlPais;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public int getCodControlCuenta() {
        return codControlCuenta;
    }

    public void setCodControlCuenta(int codControlCuenta) {
        this.codControlCuenta = codControlCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }
    
    
}

package modelo;

public class Cuenta {
    private int idCuenta;
    private int titularCuenta;
    private String pais;
    private String codControlPais;
    private String entidad;
    private String oficina;
    private String codControlCuenta;
    private String numeroCuenta;
    private double saldo;
    private String tipoMoneda;
    private int idTarjeta;

    
    public Cuenta(int idCuenta, int titularCuenta, String pais, String codControlPais, String entidad, String oficina, String codControlCuenta, String numeroCuenta, double saldo, String tipoMoneda, int idTarjeta) {
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
        this.titularCuenta = 0;
        this.pais = "";
        this.codControlPais = "";
        this.entidad = "";
        this.oficina = "";
        this.codControlCuenta = "";
        this.numeroCuenta = "";
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

    public int getTitularCuenta() {
        return titularCuenta;
    }

    public void setTitularCuenta(int titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodControlPais() {
        return codControlPais;
    }

    public void setCodControlPais(String codControlPais) {
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

    public String getCodControlCuenta() {
        return codControlCuenta;
    }

    public void setCodControlCuenta(String codControlCuenta) {
        this.codControlCuenta = codControlCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
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

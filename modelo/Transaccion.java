package modelo;

public class Transaccion {
    private int idTransaccion;
    private String clienteSalida;
    private String clienteEntrada;
    private double cantidad;
    private String tipoTransaccion;
    private String estado;
    private String error;
    private String codigoSPEI;

    public Transaccion(int idTransaccion, String clienteSalida, String clienteEntrada, double cantidad, String tipoTransaccion, String estado, String error, String codigoSPEI) {
        this.idTransaccion = idTransaccion;
        this.clienteSalida = clienteSalida;
        this.clienteEntrada = clienteEntrada;
        this.cantidad = cantidad;
        this.tipoTransaccion = tipoTransaccion;
        this.estado = estado;
        this.error = error;
        this.codigoSPEI = codigoSPEI;
    } 
    
    public Transaccion() {
        this.idTransaccion = 0;
        this.clienteSalida = "";
        this.clienteEntrada = "";
        this.cantidad = 0.00;
        this.tipoTransaccion = "";
        this.estado = "";
        this.error = "";
        this.codigoSPEI = "";
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getClienteSalida() {
        return clienteSalida;
    }

    public void setClienteSalida(String clienteSalida) {
        this.clienteSalida = clienteSalida;
    }

    public String getClienteEntrada() {
        return clienteEntrada;
    }

    public void setClienteEntrada(String clienteEntrada) {
        this.clienteEntrada = clienteEntrada;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCodigoSPEI() {
        return codigoSPEI;
    }

    public void setCodigoSPEI(String codigoSPEI) {
        this.codigoSPEI = codigoSPEI;
    }
    
    
    
}

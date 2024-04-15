package modelo;

public class Tarjeta {
    private int idTarjeta;
    private String estadoTarjeta;
    private String tipoTarjeta;
    private String marcaTarjeta;
    private int numeroTarjeta;

    public Tarjeta(int idTarjeta, String estadoTarjeta, String tipoTarjeta, String marcaTarjeta, int numeroTarjeta) {
        this.idTarjeta = idTarjeta;
        this.estadoTarjeta = estadoTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.marcaTarjeta = marcaTarjeta;
        this.numeroTarjeta = numeroTarjeta;
    }
    

    public Tarjeta() {
        
        this.idTarjeta = 0;
        this.estadoTarjeta = "";
        this.tipoTarjeta = "";
        this.marcaTarjeta = "";
        this.numeroTarjeta = 0;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    
    
}

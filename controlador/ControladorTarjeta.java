package controlador;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorTarjeta {


    // metodo para crear una nueva tarjeta
    public static boolean crearTarjeta(String estadoTarjeta, String tipoTarjeta, String marcaTarjeta, String numeroTarjeta) {
        boolean respuesta = false;
        String sql = "insert into tb_tarjeta (estadoTarjeta, tipoTarjeta, marcaTarjeta, numeroTarjeta)"
        +" values(?, ?, ?, ?);";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                consulta.setString(1, estadoTarjeta);
                consulta.setString(2, tipoTarjeta);
                consulta.setString(3, marcaTarjeta);
                consulta.setString(4, numeroTarjeta);

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {

                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al abrir la tarjeta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
/*
    // metodo para guardar los datoss de una tarjeta
    public boolean guardar(Tarjeta objeto) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement("",
                        Statement.RETURN_GENERATED_KEYS);
                consulta.setInt(1, objeto.getIdTarjeta());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {

                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al guardar la tarjeta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
*/
    
    // metodo para eliminar una tarjeta
    public boolean eliminar(int idTarjeta) {
        boolean respuesta = false;
        String sql = "delete * from tb_tarjeta where idTarjeta = '"+idTarjeta+"';";
                
                
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(
                        sql);
                consulta.executeUpdate();

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al eliminar la tarjeta: " + e);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    // metodo para crear el numero de tarjeta random
    public String crearNumeroTarjeta() {
        int numTarjeta = 0;
        String numeroTarjeta = "";

        int longitud = 16;
        Random rnd = new Random();
        for (int i = 1; i <= longitud; i++) {
            numTarjeta = rnd.nextInt();
        }
        if (numTarjeta < 0) {
            numTarjeta = numTarjeta * (-1);
        }
        numeroTarjeta = Integer.toString(numTarjeta);

        return numeroTarjeta;
    }

    // metodo para comprobar que no existe el numero de tarjeta creado
    public boolean existeNumeroTarjeta(String numeroTarjeta) throws ClassNotFoundException {

        boolean respuesta = false;
        String sql = "select * from tb_tarjeta where numeroTarjeta = '" + numeroTarjeta + "';";
        Statement st;
        Connection cn = null;
        try {
            cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }
        return respuesta;
    }

    public int obtenerIdTarjeta(String numeroTarjeta) {
        String sql = "select idTarjeta where numeroTarjeta = '" + numeroTarjeta + "';";
        int id = 0;
        try {
            Connection cn = Conexion.conectar();

            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getInt("idTarjeta");
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al eliminar la tarjeta: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    // comprobar que la tarjeta está activa
    public boolean comprobarEstado(int idTarjeta) throws SQLException {
        String nombre = "";
        boolean resp = false;
        
        String sql = "select nombre from tb_estadotarjeta inner join tb_tarjeta on tb_tarjeta.idEstadoTarjeta = tb_estadotarjeta.idEstado "
                +
                "where tb_tarjeta.idTarjeta  = '" + idTarjeta + "';";

        try {
            Connection cn = Conexion.conectar();

            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nombre = rs.getString("nombre");
                }
                if(nombre == "Activa"){
                    resp = true;
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al eliminar la tarjeta: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;

    }
}

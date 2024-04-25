package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Tarjeta;

public class ControladorTarjeta {

    //metodo para crear una nueva tarjeta
    public boolean crearTarjeta(Tarjeta objeto) {
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
                System.out.println("Error al abrir la tarjeta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para guardar los datoss de una tarjeta
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

    //metodo para actualizar una cuenta
    public boolean actualizar(Tarjeta objeto, int idTarjeta) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            String sql = "";
            PreparedStatement consulta = null;
            try {

                consulta = cn.prepareStatement(sql);
                consulta.setInt(1, objeto.getIdTarjeta());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al actualizar la tarjeta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para eliminar una tarjeta
    public boolean eliminar(int idCuenta) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(
                        "");
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

    //metodo para crear el numero de tarjeta random
    /**
     * @return
     */
    public int crearNumeroTarjeta() {
        int numCuenta = 0;
        @SuppressWarnings("unused")
        int longitud = 16;

        return numCuenta;

    }

    //metodo para comprobar que no existe el numero de tarjeta creado
    public boolean existeNumeroCuenta(int numeroTarjeta) {

        boolean respuesta = false;
        String sql = ""; 
        Statement st;
        try {
            Connection cn = null;
            try {
                cn = Conexion.conectar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorTarjeta.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public int obtenerIdTarjeta(int numeroTarjeta) {

        return 0;

    }

    //comprobar que la tarjeta está activa
    public boolean comprobarEstado(int idTarjeta) {
        boolean respuesta = false;
        String sql = ""; 
        Statement st;
        try {
            Connection cn = null;
            try {
                cn = Conexion.conectar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorTarjeta.class.getName()).log(Level.SEVERE, null, ex);
            }
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
}

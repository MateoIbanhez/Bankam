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
import modelo.Cuenta;

public class ControladorCuenta {

    //metodo para abrir una cuenta desde cero
    public boolean abrirCuenta(Cuenta objeto) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement("",
                        Statement.RETURN_GENERATED_KEYS);
                consulta.setInt(1, objeto.getIdCuenta());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {

                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al abrir la cuenta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para guardar los datoss de una cuenta
    public boolean guardar(Cuenta objeto) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement("",
                        Statement.RETURN_GENERATED_KEYS);
                consulta.setInt(1, objeto.getIdCuenta());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {

                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al guardar cabecera de venta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para actualizar una cuenta
    public boolean actualizar(Cuenta objeto, int idCuenta) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            String sql = "";
            PreparedStatement consulta = null;
            try {

                consulta = cn.prepareStatement(sql);
                consulta.setInt(1, objeto.getIdCuenta());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al actualizar la cuenta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para eliminar una cuenta
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
                System.out.println("Error al eliminar la cuenta: " + e);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para crear el numero de cuenta random
    public int crearNumeroCuenta() {
        int numCuenta = 0;
        int longitud = 10;
        Random rnd = new Random();
        for (int i = 1; i <= longitud; i++) {
            System.out.println(rnd.nextInt());
        }

        return numCuenta;

    }

    //metodo para comprobar que no existe el numero de cuenta creado
    public boolean existeNumeroCuenta(int numeroCuenta) {

        boolean respuesta = false;
        String sql = "";
        Statement st;
        try {
            Connection cn = null;
            try {
                cn = Conexion.conectar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar numero de cuenta: " + e);
        }
        return respuesta;
    }
    
    public void main(String args[]){
        
    }

}

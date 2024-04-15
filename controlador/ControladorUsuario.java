package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Usuario;


public class ControladorUsuario {
    public boolean guardar(Usuario objeto) throws ClassNotFoundException {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "";
        PreparedStatement consulta = null;
        try {
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }

    public boolean existeUsuario(String usuario) throws ClassNotFoundException {
        boolean respuesta = false;
        String sql = "";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
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

    public boolean loginUser(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = null;
        try {
            cn = (Connection) Conexion.conectar();
            String sql = "";

            Statement st = null;

            try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    respuesta = true;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            String sql = "";
            PreparedStatement consulta = null;
            try {

                consulta = cn.prepareStatement(sql);
                consulta.setString(1, objeto.getNombre());
                

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al actualizar usuario: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public boolean eliminar(int idUsuario) {
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
                System.out.println("Error al eliminar usuario: " + e);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}

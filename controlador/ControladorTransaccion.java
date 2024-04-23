package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Transaccion;
import modelo.Cuenta;

public class ControladorTransaccion {

    //metodo para hacer la transaccion
    public boolean realizarTransaccion(int idCliente, Cuenta entrada, int cantidad) {
        boolean respuesta = false;
        String sql = "insert into tb_transaccion () values(?, ?, ?, ?, ?, ?)";
        if (comporbarSaldo(cantidad, idCliente)) {

        }

        return respuesta;
    }

    //metodo para actualizar en las diferentes cuentas el saldo
    public boolean actualizarSaldo(double cantidad, String numeroCuenta, String tipo) {
        boolean respuesta = false;
        double saldoActualizado = 0.00;
        double saldoActual = 0.00;
        String sqlSelect = "select saldo from tb_cuentas where numCuenta = '" + numeroCuenta + "';";

        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sqlSelect,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    saldoActual = rs.getDouble("saldo");
                }
                if (tipo == "entrada") {
                    saldoActualizado = saldoActual + cantidad;
                } else if (tipo == "salida") {
                    saldoActualizado = saldoActual - cantidad;
                } else {
                    System.out.println("Hubo un error");
                }
                try {
                    String sqlUpdate = "update table tb_cuentas set saldo = ? where numCuenta = ?;";
                    consulta = cn.prepareStatement(sqlUpdate);
                    consulta.setDouble(1, saldoActualizado);
                    consulta.setString(1, numeroCuenta);

                    if (consulta.executeUpdate() > 0) {
                        respuesta = true;
                    }
                    cn.close();

                } catch (SQLException e) {
                    System.out.println("Error al actualizar el saldo: " + e);
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener el saldo de la cuenta: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;

    }

    //metodo para comprobar que la cuenta de salida de la operacion tiene el dinero suficiente
    public boolean comporbarSaldo(double dineroOperacion, int idUsuario) {
        boolean respuesta = false;
        double saldo = 0.00;

        String sql = "select saldo from tb_cuentas where idTitular = '" + idUsuario + "';";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    saldo = rs.getDouble("saldo");
                }

                if (saldo > dineroOperacion) {
                    respuesta = true;
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener cod pais: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    //metodo para ver el estado de una transaccion
    public String comprobarEstado(int idTransaccion) {
        String nombreEstado = "";

        String sql = "select tb_estadotransaccion.nombre from tb_estadotransaccion inner join tb_estadotransaccion.idEstado = tb_transaccion.idEstado where idTransaccion = '" + idTransaccion + "';";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    nombreEstado = rs.getString("nombre");
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener cod pais: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreEstado;
    }

    //metodo para ver si hay errores en la transaccion
    public String obtenerError(int idTransaccion) {
        String nombreError = "";

        String sql = "select tb_tipoerror.nombre from tb_tipoerror inner join tb_tipoerror.idTipoError = tb_transaccion.idTipoError where tb_transaccion.idTransaccion = '" + idTransaccion + "';";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    nombreError = rs.getString("nombre");
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener cod pais: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreError;
    }

    //metodo para consultar los movimientos de una cuenta
    @SuppressWarnings("empty-statement")
    public List<String>[] consultarMovimientos(int idCuenta) {
        boolean respuesta = false;
        List<String> entrada = new ArrayList<>();
        List<String> salida = new ArrayList<>();
        List<String>[] arrayOfList = new List[2];

        int idTransaccion;
        String numeroCuenta;
        double cantidad;
        String estado;
        String tipoError;

        String sqlEntradas = "select tb_transaccion.idTransaccion, tb_cuentas.numCuenta as numeroCuenta, tb_transaccion.cantidad, "
                + " tb_estadotransaccion.nombre as estadoNombre, tb_tipoerror.nombre as tipoErrorNombre from tb_transaccion "
                + "left join tb_cuentas on  tb_transaccion.idClienteSalida =  tb_cuentas.idCuenta"
                + "left join tb_estadotransaccion on  tb_transaccion.idEstado =  tb_estadotransaccion.idEstado "
                + "left join tb_tipoerror on  tb_transaccion.idTipoError =  tb_tipoerror.idTipoError"
                + "where idBanco = '" + idCuenta + "';";
        String sqlSalidas = "select tb_transaccion.idTransaccion, tb_cuentas.numCuenta as numeroCuenta, tb_transaccion.cantidad, "
                + " tb_estadotransaccion.nombre as estadoNombre, tb_tipoerror.nombre as tipoErrorNombre from tb_transaccion "
                + "left join tb_cuentas on  tb_transaccion.idBanco =  tb_cuentas.idCuenta"
                + "left join tb_estadotransaccion on  tb_transaccion.idEstado =  tb_estadotransaccion.idEstado "
                + "left join tb_tipoerror on  tb_transaccion.idTipoError =  tb_tipoerror.idTipoError"
                + "where idClienteSalida = '" + idCuenta + "';";

        try {
            Connection cn = Conexion.conectar();
            //obtener las entradas de la cuenta
            try {
                PreparedStatement consulta = cn.prepareStatement(sqlEntradas,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    idTransaccion = rs.getInt("idTransaccion");
                    numeroCuenta = rs.getString("numeroCuenta");
                    cantidad = rs.getDouble("cantidad");
                    estado = rs.getString("estadoNombre");
                    tipoError = rs.getString("tipoErrorNombre");
                    entrada.add(Integer.toString(idTransaccion));
                    entrada.add(numeroCuenta);
                    entrada.add(Double.toString(cantidad));
                    entrada.add(estado);
                    entrada.add(tipoError);
                }
                arrayOfList[0] = entrada;
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error obteniendo las entradas: " + e);
            }
            //obtener las salidas de la cuenta
            try {
                PreparedStatement consulta = cn.prepareStatement(sqlSalidas,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    idTransaccion = rs.getInt("idTransaccion");
                    numeroCuenta = rs.getString("numeroCuenta");
                    cantidad = rs.getDouble("cantidad");
                    estado = rs.getString("estadoNombre");
                    tipoError = rs.getString("tipoErrorNombre");
                    salida.add(Integer.toString(idTransaccion));
                    salida.add(numeroCuenta);
                    salida.add(Double.toString(cantidad));
                    salida.add(estado);
                    salida.add(tipoError);
                }
                arrayOfList[1] = salida;
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error obteniendo las salidas: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayOfList;
    }

    //metodo para cancelar movimiento
    public boolean cancelarMovimientos(Cuenta objeto) {
        boolean respuesta = false;

        return respuesta;
    }

}

package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cuenta;

public class ControladorTransaccion {

    // metodo para hacer la transaccion
    /**
     * @param nombre
     * @param idCliente
     * @param iban
     * @param concepto
     * @param cantidad
     * @return
     * @throws ParseException
     */
    public boolean realizarTransaccion(String nombre, int idCliente, String iban, String concepto, double cantidad)
            throws ParseException {

        int idBanco = obtenerIDCliente(iban);

        boolean respuesta = false;
        LocalDate date;
        date = LocalDate.now();

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String fechaStr = df.format(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date fecha = (Date) formatter.parse(fechaStr);

        String sql = "insert into tb_transaccion (idClienteSalida, idBanco, cantidad, idEstado, idTipoError, concepto, fecha) values(?, ?, ?, ?, ?, ?, ?)";
        if (comporbarSaldo(cantidad, idCliente)) {
            try {

                Connection cn = Conexion.conectar();
                try {
                    PreparedStatement consulta = cn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    consulta.setInt(1, idCliente);
                    consulta.setInt(2, idBanco);
                    consulta.setDouble(3, cantidad);
                    consulta.setInt(4, 1);
                    consulta.setInt(5, 7);
                    consulta.setString(6, concepto);
                    consulta.setDate(7, fecha);

                    if (consulta.executeUpdate() > 0) {
                        respuesta = true;
                    }

                    ResultSet rs = consulta.getGeneratedKeys();
                    while (rs.next()) {
                        respuesta = true;
                    }

                    cn.close();
                } catch (SQLException e) {
                    System.out.println("Error al obtener el saldo de la cuenta: " + e);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return false;
        }

        return respuesta;
    }

    // metodo para actualizar en las diferentes cuentas el saldo
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
                    consulta.setString(2, numeroCuenta);

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

    // metodo para comprobar que la cuenta de salida de la operacion tiene el dinero
    // suficiente
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
                System.out.println("Error al obtener el saldo de la cuenta: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    // metodo para ver el estado de una transaccion
    public String comprobarEstado(int idTransaccion) {
        String nombreEstado = "";

        String sql = "select tb_estadotransaccion.nombre from tb_estadotransaccion inner join tb_estadotransaccion.idEstado = tb_transaccion.idEstado where idTransaccion = '"
                + idTransaccion + "';";
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
                System.out.println("Error al comprobar el estado de la transaccion: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreEstado;
    }

    // metodo para ver el estado de una transaccion
    public boolean actualizarEstado(int idTransaccion, int idEstado) {
        boolean resp = false;

        String sql = "update table tb_transaccion set idEstado = '"+idEstado+"' where idTransaccion = '"
                + idTransaccion + "';";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    resp = true;
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al comprobar el estado de la transaccion: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    // metodo para ver si hay errores en la transaccion
    public String obtenerError(int idTransaccion) {
        String nombreError = "";

        String sql = "select tb_tipoerror.nombre from tb_tipoerror inner join tb_tipoerror.idTipoError = tb_transaccion.idTipoError where tb_transaccion.idTransaccion = '"
                + idTransaccion + "';";
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
                System.out.println("Error al comprobar si la transaccion tiene algun error: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreError;
    }

    // metodo para consultar los movimientos de una cuenta
    /**
     * @param idCuenta
     * @return
     */
    @SuppressWarnings({ "empty-statement", "unused" })
    public List<Map<String, Object>> consultarMovimientos(int idCuenta) {
        boolean respuesta = false;

        int idTransaccion;
        String numeroCuenta;
        double cantidad;
        String estado;
        String tipoError;

        String sql = "select trans.idTransaccion, cuenta.numCuenta as numeroCuenta, trans.cantidad, est.nombre as estadoNombre, "
                + "err.nombre as tipoErrorNombre, trans.idClienteSalida, trans.idBanco, trans.concepto, trans.fecha from tb_transaccion trans "
                + "left join tb_cuentas cuenta on trans.idClienteSalida = cuenta.idCuenta or trans.idBanco = cuenta.idCuenta "
                + "left join tb_estadotransaccion est on trans.idEstado = est.idEstado "
                + "left join tb_tipoerror err on trans.idTipoError = err.idTipoError"
                + "where idBanco = '" + idCuenta + "' or idClienteSalida = '" + idCuenta + "';";
        List<Map<String, Object>> resultados = null;
        try {
            resultados = obtenerResultados(sql, idCuenta);
            try {
                Connection cn = Conexion.conectar();
                // obtener las entradas de la cuenta
                try {
                    PreparedStatement consulta = cn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    ResultSet rs = consulta.executeQuery();
                    while (rs.next()) {

                    }

                    cn.close();
                } catch (SQLException e) {
                    System.out.println("Error obteniendo las entradas de los movimientos: " + e);
                }
                // obtener las salidas de la cuenta

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    /**
     * @param sql
     * @param idCuenta
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> obtenerResultados(String sql, int idCuenta) throws Exception {
        List<Map<String, Object>> resultados = new ArrayList<>();
        try {
            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    fila.put("idTransaccion", rs.getInt("idTransaccion"));
                    fila.put("numeroCuenta", rs.getString("numeroCuenta"));
                    fila.put("cantidad", rs.getDouble("cantidad"));
                    fila.put("estadoNombre", rs.getString("estadoNombre"));
                    fila.put("tipoErrorNombre", rs.getString("tipoErrorNombre"));
                    fila.put("idClienteSalida", rs.getInt("idClienteSalida"));
                    fila.put("idBanco", rs.getInt("idBanco"));
                    fila.put("concepto", rs.getString("concepto"));
                    fila.put("fecha", rs.getDate("fecha"));
                    resultados.add(fila);
                }

                try {
                    Map<String, Object> filaIdClienteSalida = resultados.get(5);
                    Object valorIdClienteSalida = filaIdClienteSalida.get("idClienteSalida");
                    String numCuentaClienteSalida = obtenerNumCuenta((int) valorIdClienteSalida);
                    filaIdClienteSalida.put("idClienteSalida", numCuentaClienteSalida);
                    Map<String, Object> filaCantidad = resultados.get(2);
                    Object cantidad = filaCantidad.get("cantidad");
                    Map<String, Object> filaIdBanco = resultados.get(6);
                    Object valorIdBanco = filaIdClienteSalida.get("idBanco");
                    String numCuentaIdBanco = obtenerNumCuenta((int) valorIdBanco);
                    filaIdBanco.put("idBanco", numCuentaIdBanco);
                    if ((int) valorIdBanco == idCuenta) {
                        cantidad = (String) "-" + cantidad;
                        filaCantidad.put("cantidad", cantidad);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Error al cancelar: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultados;
    }

    @SuppressWarnings("unused")
    public static List<Map<String, Object>> obtenerDetalleMovimiento(int idMovimiento, int idUsuario) {
        List<Map<String, Object>> detalles = new ArrayList<>();

        int idCS = 0;
        int idB = 0;
        double cant = 0.00;
        String nombreEst = "";
        String nombreErr = "";
        String concepto = "";
        Date fecha = null;

        String sql = "select tb_transaccion.idClienteSalida, tb_transaccion.idBanco, tb_transaccion.cantidad ,tb_estadotransaccion.nombre as nombreEst , "
        +"tb_tipoerror.nombre as nombreErr, tb_transaccion.concepto, tb_transaccion.fecha from tb_transaccion "
        +"inner join tb_estadotransaccion on tb_transaccion.idEstado = tb_estadotransaccion.idEstado "
        +"inner join tb_tipoerror ON tb_transaccion.idTipoError = tb_tipoerror.idTipoError where tb_transaccion.idTransaccion = '"
                + idMovimiento + "';";
        try {
            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {
                    idCS = rs.getInt("idClienteSalida ");
                    idB = rs.getInt("idBanco ");


                    if (idCS != idUsuario) {
                        // Agregar la fila a la lista de resultados
                        Map<String, Object> fila = new HashMap<>();
                        fila.put("salida", idB);
                        fila.put("entrada", idCS);
                        fila.put("cantidad", rs.getDouble("cantidad"));
                        fila.put("nombreEst", rs.getString("nombreEst"));
                        fila.put("nombreErr", rs.getString("nombreErr"));
                        fila.put("concepto", rs.getString("concepto"));
                        fila.put("fecha", rs.getDate("fecha"));
                        detalles.add(fila);
                    }
    
                    // Verificar si el idBanco es diferente al idUsuario
                    if (idB != idUsuario) {
                        // Agregar la fila a la lista de resultados
                        Map<String, Object> fila = new HashMap<>();
                        fila.put("salida", idCS);
                        fila.put("entrada", idB);
                        fila.put("cantidad", rs.getDouble("cantidad"));
                        fila.put("estado", rs.getString("nombreEst"));
                        fila.put("error", rs.getString("nombreErr"));
                        fila.put("concepto", rs.getString("concepto"));
                        fila.put("fecha", rs.getDate("fecha"));
                        detalles.add(fila);
                    }
                    
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener el saldo de la cuenta: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        //en el cliente debe comprobar si idUsuario es igual al id de salida o entrada de dinero
        //si el idUsuario es igual al id de salida---> cantidad en negativo (sale dinero de la cuenta)
        //y obtener el numCuenta del id de entrada/salida (el que no es del usuario) pasando el idUsuario
        
        return detalles;
    }

    public static String obtenerNumCuenta(int idCliente) {
        String numCuenta = "";
        String sql = "select numCuenta from tb_cuentas  where idCliente = '" + idCliente + "';";

        try {
            Connection cn = Conexion.conectar();
            // obtener las entradas de la cuenta
            try {
                PreparedStatement consulta = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    numCuenta = rs.getString("numCuenta");
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error obteniendo las entradas: " + e);
            }
            // obtener las salidas de la cuenta

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numCuenta;
    }

    // metodo para cancelar movimiento
    public boolean cancelarMovimientos(Cuenta objeto, int idTransaccion) {
        boolean respuesta = false;
        int intCancel = 5;
        String sql = "update tb_transaccion set idEstado = '" + intCancel + "' where idTransaccion = '"+idTransaccion+"'';";
        try {
            Connection cn = Conexion.conectar();
            // obtener las entradas de la cuenta
            try {
                PreparedStatement consulta = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    respuesta = true;
                }
                if (respuesta == true) {
                    try {
                        String sqlSelectSaldo = "select tb_transaccion.saldo, tb_cuentas.numCuenta as numCuenta from tb_transaccion "
                                + "inner join tb_transaccion on tb_transaccion.idBanco = tb_cuentas.idCuenta where idTransaccion = '"
                                + idTransaccion + "';";
                        double saldo = 0.00;
                        String numCuenta = "";

                        PreparedStatement consultaSaldo = cn.prepareStatement(sqlSelectSaldo,
                                Statement.RETURN_GENERATED_KEYS);
                        ResultSet rsSaldo = consultaSaldo.executeQuery();
                        while (rsSaldo.next()) {
                            saldo = rsSaldo.getDouble("saldo");
                            numCuenta = rs.getString("numCuenta");
                            
                        }
                        try {
                            boolean actSE = actualizarSaldo(saldo, objeto.getNumeroCuenta(), "entrada");
                            boolean actSS = actualizarSaldo(saldo, numCuenta, "salida");
                            if(actSE == true && actSS == true){
                                actualizarEstado(idTransaccion, 5);
                            }
                            
                        } catch (Exception e) {
                            System.out.println("Error al actualizar los saldos y estado: " + e);
                        }

                        cn.close();
                    } catch (SQLException e) {
                        System.out.println("Error al seleccionar los datos de la cuenta : " + e);
                    }
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cancelar: " + e);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public int obtenerIDCliente(String iban) {

        String[] ibanSplit;

        ibanSplit = iban.split(" ");
        String numCuenta = ibanSplit[3];
        int id = 0;
        String sql = "select idCliente where numCuenta = '" + numCuenta + "'";
        try {
            Connection cn = Conexion.conectar();
            // obtener las entradas de la cuenta
            try {
                PreparedStatement consulta = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("idCliente");
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener el id del cliente: " + e);
            }
            // obtener las salidas de la cuenta

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    /*
     * public String obtenerIdNombreCliente(int idCleinte) {
     * String nombre = "";
     * return nombre;
     * }
     */
}

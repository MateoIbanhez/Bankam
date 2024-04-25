package controlador;

import conexion.Conexion;
import java.math.BigInteger;
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
/*
    public static void main() {
        String numCuenta = "1258689557";
        String entidad = "0418";
        String oficina = "2100";
        String codPais = obtenerCodPais("ES");
        String digControlPais = calcularDigitoControlPais(entidad, numCuenta, codPais);
        String digControlNC = calcularDigitoControlNumCuenta(entidad, oficina, numCuenta);
        Cuenta objeto = new Cuenta(0, 0, "España", digControlPais, entidad, oficina, digControlNC, numCuenta, 0.00, "Euro", 0);
        int idUsuario = 2;
        do {

            numCuenta = crearNumeroCuenta();
            System.out.println("numero de cuenta: " + numCuenta);
        } while (existeNumeroCuenta(numCuenta) == true);
        if (existeNumeroCuenta(numCuenta)) {
            abrirCuenta(objeto, idUsuario);
        }
    }
*/
    //metodo para abrir una cuenta desde cero
    public static boolean abrirCuenta(Cuenta objeto, int idUsuario) {
        boolean respuesta = false;
        String codPais = obtenerCodPais(objeto.getPais());
        String ent = String.valueOf(objeto.getEntidad());
        String numC = String.valueOf(objeto.getNumeroCuenta());
        String digitoControlPais = calcularDigitoControlPais(ent, numC, codPais);
        String sql = "insert into tb_cuentas (idTitular, codPais, digitoControlPais, entidad, oficina, digitoControlCuenta, numCuenta, saldo, tipoMoneda, idTarjeta) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                consulta.setInt(1, idUsuario);
                consulta.setString(2, codPais);
                consulta.setString(3, objeto.getCodControlPais());
                consulta.setString(4, objeto.getEntidad());
                consulta.setString(5, objeto.getOficina());
                consulta.setString(6, objeto.getCodControlCuenta());
                consulta.setString(7, objeto.getNumeroCuenta());
                consulta.setDouble(8, objeto.getSaldo());
                consulta.setString(8, objeto.getTipoMoneda());
                consulta.setInt(8, objeto.getIdTarjeta());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {
                    respuesta = true;
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al abrir la cuenta: " + e);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public static String obtenerCodPais(String isoPais) {
        int respuesta = 0;
        String sql = "select codPais from tb_paises where iso = '" + isoPais + "';";
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    respuesta = rs.getInt("codPais");
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al obtener cod pais: " + e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString(respuesta);
    }

    //metodo para actualizar una cuenta
    public static boolean actualizar(Cuenta objeto, int idCuenta) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            String sql = "update tb_cuentas set idTitular= ? ,oficina= ? ,saldo = ? ,idTarjeta = ? where idCuenta = ?";
            PreparedStatement consulta = null;
            try {

                consulta = cn.prepareStatement(sql);
                consulta.setInt(1, objeto.getTitularCuenta());
                consulta.setString(2, objeto.getOficina());
                consulta.setDouble(3, objeto.getSaldo());
                consulta.setInt(4, objeto.getIdTarjeta());
                consulta.setInt(5, idCuenta);

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al actualizar la cuenta: " + e);
            }
            return respuesta;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para eliminar una cuenta
    public static boolean eliminar(int idCuenta) {
        boolean respuesta = false;
        String sql = "delete * from tb_cuentas where idCuenta = '" + idCuenta + "'";
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
                System.out.println("Error al eliminar la cuenta: " + e);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    //metodo para crear el numero de cuenta random
    public static String crearNumeroCuenta() {
        int numCuenta = 0;
        String numCuentaSt = "";
        int longitud = 10;
        Random rnd = new Random();
        for (int i = 1; i <= longitud; i++) {
            numCuenta = rnd.nextInt();
        }
        if (numCuenta < 0) {
            numCuenta = numCuenta * (-1);
        }
        numCuentaSt = Integer.toString(numCuenta);

        return numCuentaSt;

    }

    //metodo para comprobar que no existe el numero de cuenta creado
    public static boolean existeNumeroCuenta(String numeroCuenta) {

        boolean respuesta = false;
        String sql = "select * from tb_cuentas where numCuenta = '" + numeroCuenta + "'";
        Statement st;
        try {
            Connection cn = null;
            try {
                cn = Conexion.conectar();
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    respuesta = true;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar numero de cuenta: " + e);
        }
        System.out.println(respuesta);
        return respuesta;
    }

    private static int calcularIBAN() {
        long numCuenta = 1234567812;
        int aux = 142800;

        int codEntidad = 1533;
        String numeroCompuesto = String.valueOf(codEntidad) + String.valueOf(numCuenta) + String.valueOf(aux);
        BigInteger numeroC = new BigInteger(numeroCompuesto);
        BigInteger noventaYSiete = new BigInteger("97");
        numeroC = numeroC.mod(noventaYSiete);
        int dcP = numeroC.intValue();
        dcP = 98 - dcP;

        return dcP;
    }

    private static String calcularDigitoControlPais(String codEnt, String numeroCuenta, String codPais) {
        numeroCuenta = "1326543644";
        //solo actuamos en españa por lo que es ES00
        //valor E = 14 |||| valor S = 28
        codEnt = "0128";

        String numeroCompuesto = codEnt + numeroCuenta + codPais;
        BigInteger numeroC = new BigInteger(numeroCompuesto);
        BigInteger noventaYSiete = new BigInteger("97");
        numeroC = numeroC.mod(noventaYSiete);
        int dcP = numeroC.intValue();
        dcP = 98 - dcP;
        return Integer.toString(dcP);

    }

    /**
     * @param codEnt
     * @param oficina
     * @param numeroCuenta
     * @return
     */
    @SuppressWarnings("unused")
    private static String calcularDigitoControlNumCuenta(String codEnt, String oficina, String numeroCuenta) {
        String dCNC = "";
        //calculo del primer digito

        //solo actuamos en españa por lo que es ES00
        //valor E = 14 |||| valor S = 28
        String aux = "142800";
        codEnt = "0128";
        oficina = "1458";
        int pCCC;
        int restoPDC;

        int primerNO = (int) oficina.charAt(0) * 4;
        int segundoNO = (int) oficina.charAt(1) * 8;
        int tercerNO = (int) oficina.charAt(2) * 5;
        int cuartoNO = (int) oficina.charAt(3) * 10;

        int primerNE = (int) oficina.charAt(0) * 9;
        int segundoNE = (int) oficina.charAt(1) * 7;
        int tercerNE = (int) oficina.charAt(2) * 3;
        int cuartoNE = (int) oficina.charAt(3) * 6;

        int suma = primerNE + primerNO + segundoNE + segundoNO + tercerNE + tercerNO + cuartoNE + cuartoNO;
        restoPDC = suma%11;

        if (restoPDC == 10) {
            pCCC = 1;
        } else if (restoPDC == 11) {
            pCCC = 0;
        } else {
            pCCC = restoPDC;
        }

        //calcular segundo digito
        numeroCuenta = "1326543644";
        int sCCC;
        int restoSDC;
        int cont = 0;
        for (int i = 0; i < numeroCuenta.length(); i++) {
            int cifra = (int) numeroCuenta.charAt(i);
            cont += cifra;
        }
        restoSDC = cont%11;

        if (restoSDC == 10) {
            sCCC = 1;
        } else if (restoSDC == 11) {
            sCCC = 0;
        } else {
            sCCC = restoSDC;
        }
        dCNC = String.valueOf(pCCC) + String.valueOf(sCCC);

        return dCNC;

    }

    

}

package controlador;

import conexion.Conexion;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import modelo.Usuario;

public class ControladorUsuario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String nombre;
            String primerApe;
            String segundoApe;
            String password;
            String docIdent;
            int diaNac;
            int mesNac;
            int anoNac;
            String tel;
            String correo;
            String calle;
            int numCalle;
            int piso;
            String letra;
            String genero;
            do {
                System.out.println("Introduce un nombre: ");
                nombre = sc.nextLine();
                System.out.println("Introduce un primer apellido: ");
                primerApe = sc.nextLine();
                System.out.println("Introduce un segundo apellido: ");
                segundoApe = sc.nextLine();
                System.out.println("Introduce una password: ");
                password = sc.nextLine();
                System.out.println("Introduce tu documentoIdentificacion: ");
                docIdent = sc.nextLine();
                System.out.println("Introduce tu dia de nacimiento: ");
                diaNac = sc.nextInt();
                System.out.println("Introduce tu mes de nacimiento: ");
                mesNac = sc.nextInt();
                System.out.println("Introduce tu año de nacimiento: ");
                anoNac = sc.nextInt();
                System.out.println("Introduce un telefono: ");
                tel = sc.nextLine();
                System.out.println("Introduce un telefono: ");
                tel = sc.nextLine();
                System.out.println("Introduce un correo: ");
                correo = sc.nextLine();
                System.out.println("Introduce una calle: ");
                calle = sc.nextLine();
                System.out.println("Introduce un numero de calle: ");
                numCalle = sc.nextInt();
                System.out.println("Introduce un piso: ");
                piso = sc.nextInt();
                System.out.println("Introduce una letra: ");
                letra = sc.nextLine();
                System.out.println("Introduce un genero(H/M): ");
                genero = sc.nextLine();
            } while (nombre.isEmpty() || primerApe.isEmpty() || genero.isEmpty() || segundoApe.isEmpty() || password.isEmpty() || diaNac == 0 || mesNac == 0 || anoNac == 0
                    || tel.isEmpty() || correo.isEmpty() || calle.isEmpty() || numCalle == 0);

            String fechaNac = "";

            //hacemos algunas validaciones 
            //validarEmail
            if (validarEmail(correo)) {
                System.out.println("Validacion de email correcta");
            } else {
                System.out.println("Validacion de email INCORRECTA");
                correo = "";
            }
            //validar documento de identidad (DNI)
            if (validarDocumentoIdentificacion(docIdent)) {
                System.out.println("Validacion de documento de identidad correcta");

            } else {
                System.out.println("Validacion de documento de identidad INCORRECTA");
                System.exit(1);
            }
            //validar telefono
            if (validarTelefono(tel)) {
                System.out.println("Validacion de telefono correcta");

            } else {
                System.out.println("Validacion de telefono INCORRECTA");
                System.exit(1);
            }
            //validar genero
            int generoInt = validarGenero(genero);
            System.out.println(genero + " _ " + generoInt +"\n");
            
            System.out.println(generoInt  + " genero \n");
            
            if (generoInt == 0) {
                System.out.println("Hubo un error al asignar un genero");
            } else {
                System.out.println(generoInt + "\n");
            }
            //validar la letra, si hay
            if (!validarLetra(letra)) {
                letra = "";
            }
            System.out.println(diaNac + "\t");
            System.out.println(mesNac + "\t");
            System.out.println(anoNac + "\n");
            //validar fecha de nacimiento
            fechaNac = validarFecha(diaNac, mesNac, anoNac);
            
            fechaNac = anoNac+"/"+ mesNac + "/" + diaNac;
            
            System.out.println(fechaNac + "\n");
            //obtenemos la fecha actual para insertar en la fecha de creacion
            String fechaCreacion = obtenerFechaActual();
            System.out.println(fechaCreacion + "\n");
            //encriptar la contraseña
            //byte[] passwordEncriptada = cifrarPass(password);
            //System.out.println(passwordEncriptada + "\n");

            Usuario usu = new Usuario();
            usu.setNombre(nombre);
            usu.setPrimerApellido(primerApe);
            usu.setSegundoApellido(segundoApe);
            usu.setPassword(password);
            usu.setDocumentoIdentificacion(docIdent);
            usu.setFechaNacimiento(fechaNac);
            usu.setNumeroTel(tel);
            usu.setCorreo(correo);
            usu.setCalle(calle);
            usu.setNumeroCalle(numCalle);
            usu.setPiso(piso);
            usu.setLetra(letra);
            usu.setFechaCreacion(fechaCreacion);
            usu.setGenero(generoInt);
            usu.setEstado(1);
            //comprobamos que el usuario que queremos registrar no existe en la base de datos
            if (existeUsuario(docIdent)) {
                System.out.println("Intentado ver si el usuario existe...");
                JOptionPane.showMessageDialog(null, "El usuario introducido ya existe en la base de datos");
            } else {
                System.out.println("El usuario todavia no existe...vamos a crearlo...");
                registrarUsuario(usu);
            }

        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean registrarUsuario(Usuario objeto) throws ClassNotFoundException {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "insert into tb_clientes (nombre, primerApellido, segundoApellido, password, documentoIdentificacion, fechaNacimiento, telefono, correo, calle, numeroCalle, piso, letra, fechaCreacion, genero, estado) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement consulta = null;
        try {
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getPrimerApellido());
            consulta.setString(3, objeto.getSegundoApellido());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getDocumentoIdentificacion());
            consulta.setString(6, objeto.getFechaNacimiento());
            consulta.setString(7, objeto.getNumeroTel());
            consulta.setString(8, objeto.getCorreo());
            consulta.setString(9, objeto.getCalle());
            consulta.setInt(10, objeto.getNumeroCalle());
            consulta.setInt(11, objeto.getPiso());
            consulta.setString(12, objeto.getLetra());
            consulta.setString(13, objeto.getFechaCreacion());
            consulta.setInt(14, objeto.getGenero());
            consulta.setInt(15, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }

    public static boolean existeUsuario(String documentoIdentificacion) throws ClassNotFoundException {
        boolean respuesta = false;
        String sql = "select * from tb_clientes where documentoIdentificacion = '" + documentoIdentificacion + "';";
        Statement st;
        Connection cn  = null;
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

    public boolean loginUser(String documento) {
        boolean respuesta = false;
        Connection cn = null;
        try {
            cn = (Connection) Conexion.conectar();
            String sql = "select password from tb_clientes where documentoIdentificacion = '" + documento + "';";

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

    //metodo pra eliminar el usuario
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement(
                        "delete * from tb_clientes where idUsuario = '" + idUsuario + "'");
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

    public boolean subirDocumentos() {
        boolean respuesta = false;

        return respuesta;
    }

    //metodo para encriptar la contraseña
    public static byte[] cifrarPass(String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    //metodo para desencriptar la contraseña
    public static String descifrarPass(byte[] cifrado) throws Exception {
        final Cipher aes = obtieneCipher(false);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    //metodo que obtiene la clave para cifrar/descifrar
    private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "vhsdfsjhbvcxsbVCSVACBSAJBDbqabwdajbsjabcksancbBJHScjsalskaidwoq_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }

    //metodo para validar algunos datos
    public static boolean validarEmail(String email) {
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = patron.matcher(email);
        return mat.find();
    }

    //validacion documentoIdentificacion 
    public static boolean validarDocumentoIdentificacion(String documentoIdentificacion) {
        final String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";
        boolean respuesta = false;
        String intPartDNI = documentoIdentificacion.trim().replaceAll(" ", "").substring(0, 8);
        char ltrDNI = documentoIdentificacion.charAt(8);
        int valNumDni = Integer.parseInt(intPartDNI) % 23;
        System.out.println(valNumDni + "\n\n");

        System.out.println(ltrDNI + "\n\n");
        System.out.println(intPartDNI + "\n\n");
        respuesta = !(documentoIdentificacion.length() != 9 || isStringNumeric(intPartDNI) == false || dniChars.charAt(valNumDni) != ltrDNI);

        System.out.println(respuesta + "\n\n");
        return respuesta;
    }

    public static boolean isStringNumeric(String number) {
        boolean isNumeric;
        if (number == null) {
            isNumeric = false;
        } else {
            try {
                Double num = Double.parseDouble(number);
                isNumeric = true;
            } catch (NumberFormatException e) {
                isNumeric = false;
            }
        }
        return isNumeric;
    }

    //validamos que el documento sean solo numeros
//    public static boolean soloNumeros(String documentoIdentificacion) {
//        String cadenaSinLetraFinal = documentoIdentificacion.substring(0, documentoIdentificacion.length() - 1);
//
//        String numero = "";
//        String dni = "";
//        String[] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
//        for (int i = 0; i < cadenaSinLetraFinal.length(); i++) {
//            numero = cadenaSinLetraFinal.substring(i, i + 1);
//            for (int j = 0; i < numeros.length; j++) {
//                if (numero.equals(numeros[j])) {
//                    dni = numeros[j];
//                }
//            }
//        }
//        return dni.length() == 8;
//    }
    //validamos que haya una letra en el dni
//    private static String letraDocumentoIdentificacion(String documentoIdentificacion) {
//        int miDNI = Integer.parseInt(documentoIdentificacion.substring(0, 8));
//        int resto = 0;
//        String miLetra = "";
//        String[] asignacionLetra = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
//
//        resto = miDNI % 26;
//
//        miLetra = asignacionLetra[resto];
//
//        return miLetra;
//    }
    //validamos que las cadenas sean solo de letras(nombre, apellidos, calle)
//    public static boolean validarSoloLetras(String cadena) {
//        for (int i = 0; i < cadena.length(); i++) {
//            if (!Character.isAlphabetic((cadena.charAt(i)))) {
//                return false;
//            }
//        }
//        return true;
//    }
    //validamos que el numero de telefono cumpla con la longitud y que sean solo numeros
    public static boolean validarTelefono(String cadena) {
        boolean respuesta = false;
        if (cadena.length() != 9) {
            respuesta = false;
        } else {
            String intPartDNI = cadena.trim().replaceAll(" ", "").substring(0, 9);

            if (isStringNumeric(intPartDNI) == false) {
                respuesta = false;
            } else {
                respuesta = true;
            }

        }
        return respuesta;
    }

    //validamos que la fecha de nacimiento esté bien introducida y le damos formato 
    public static String validarFecha(int dia, int mes, int ano) throws ParseException {
        
        //validar lod dias disponibles por cada mes, 
        
        
        //validar que los meses van de 1 a 12
        
        
        //validar que el año está comprendido entre 1910 y el año actual menos 16 años
        
        

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            
            return formatoFecha.parse(dia+"/"+ mes + "/" + ano).toString();
    }

    //validamos que el genero sea 'H' o 'M'
    public static int validarGenero(String genero) {
        
        String gen = genero.toUpperCase();
        int generoInt = 0;
        if (gen.equals("H") ) {
            generoInt = 1;
        } else if (gen.equals("M") ) {
            generoInt = 2;
        }
        return generoInt;
    }

    public static boolean validarLetra(String cadena) {
        boolean respuesta = false;
        if (cadena.length() == 1) {
            respuesta = true;
        }
        return respuesta;

    }

    public static String obtenerFechaActual() {
        Date diaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaActual = sdf.format(diaActual);
        return fechaActual;

    }
}

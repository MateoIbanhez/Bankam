package controlador;

import conexion.Conexion;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import modelo.Usuario;

public class ControladorUsuario {
    /*
     * public static void main(String[] args) {
     * Scanner sc = new Scanner(System.in);
     * int op;
     * try {
     * 
     * String password;
     * String docIdent;
     * 
     * System.out.println("Elija una opcion: \n "
     * + "1- registrar...\n"
     * + "2- login..."
     * + "3 - actualizar datos"
     * + "4 - eliminar usuario");
     * op = sc.nextInt();
     * if (op == 1) {
     * String nombre;
     * String primerApe;
     * String segundoApe;
     * int diaNac;
     * int mesNac;
     * int anoNac;
     * String tel;
     * String correo;
     * String calle;
     * int numCalle;
     * int piso;
     * String letra;
     * String genero;
     * do {
     * 
     * System.out.println("Introduce un nombre: ");
     * nombre = sc.nextLine();
     * System.out.println("Introduce un primer apellido: ");
     * primerApe = sc.nextLine();
     * System.out.println("Introduce un segundo apellido: ");
     * segundoApe = sc.nextLine();
     * System.out.println("Introduce una password: ");
     * password = sc.nextLine();
     * System.out.println("Introduce tu documentoIdentificacion: ");
     * docIdent = sc.nextLine();
     * System.out.println("Introduce tu dia de nacimiento: ");
     * diaNac = sc.nextInt();
     * System.out.println("Introduce tu mes de nacimiento: ");
     * mesNac = sc.nextInt();
     * System.out.println("Introduce tu año de nacimiento: ");
     * anoNac = sc.nextInt();
     * System.out.println("Introduce un telefono: ");
     * tel = sc.nextLine();
     * System.out.println("Introduce un telefono: ");
     * tel = sc.nextLine();
     * System.out.println("Introduce un correo: ");
     * correo = sc.nextLine();
     * System.out.println("Introduce una calle: ");
     * calle = sc.nextLine();
     * System.out.println("Introduce un numero de calle: ");
     * numCalle = sc.nextInt();
     * System.out.println("Introduce un piso: ");
     * piso = sc.nextInt();
     * System.out.println("Introduce una letra: ");
     * letra = sc.nextLine();
     * System.out.println("Introduce un genero(H/M): ");
     * genero = sc.nextLine();
     * 
     * String fechaNac = "";
     * 
     * //hacemos algunas validaciones
     * //validarEmail
     * if (validarEmail(correo)) {
     * System.out.println("Validacion de email correcta");
     * } else {
     * System.out.println("Validacion de email INCORRECTA");
     * correo = "";
     * }
     * //validar documento de identidad (DNI)
     * if (validarDocumentoIdentificacion(docIdent)) {
     * System.out.println("Validacion de documento de identidad correcta");
     * 
     * } else {
     * System.out.println("Validacion de documento de identidad INCORRECTA");
     * System.exit(1);
     * }
     * //validar telefono
     * if (validarTelefono(tel)) {
     * System.out.println("Validacion de telefono correcta");
     * 
     * } else {
     * System.out.println("Validacion de telefono INCORRECTA");
     * System.exit(1);
     * }
     * //validar genero
     * int generoInt = validarGenero(genero);
     * System.out.println(genero + " _ " + generoInt + "\n");
     * 
     * System.out.println(generoInt + " genero \n");
     * 
     * if (generoInt == 0) {
     * System.out.println("Hubo un error al asignar un genero");
     * } else {
     * System.out.println(generoInt + "\n");
     * }
     * //validar la letra, si hay
     * if (!validarLetra(letra)) {
     * letra = "";
     * }
     * System.out.println(diaNac + "\t");
     * System.out.println(mesNac + "\t");
     * System.out.println(anoNac + "\n");
     * //validar fecha de nacimiento
     * fechaNac = validarFecha(diaNac, mesNac, anoNac);
     * 
     * fechaNac = anoNac + "/" + mesNac + "/" + diaNac;
     * 
     * System.out.println(fechaNac + "\n");
     * //obtenemos la fecha actual para insertar en la fecha de creacion
     * String fechaCreacion = obtenerFechaActual();
     * System.out.println(fechaCreacion + "\n");
     * //encriptar la contraseña
     * byte[] passwordEncriptada = cifrarPass(password);
     * System.out.println(passwordEncriptada + "\n");
     * 
     * Usuario usu = new Usuario();
     * usu.setNombre(nombre);
     * usu.setPrimerApellido(primerApe);
     * usu.setSegundoApellido(segundoApe);
     * usu.setPassword(passwordEncriptada);
     * usu.setDocumentoIdentificacion(docIdent);
     * usu.setFechaNacimiento(fechaNac);
     * usu.setNumeroTel(tel);
     * usu.setCorreo(correo);
     * usu.setCalle(calle);
     * usu.setNumeroCalle(numCalle);
     * usu.setPiso(piso);
     * usu.setLetra(letra);
     * usu.setFechaCreacion(fechaCreacion);
     * usu.setGenero(generoInt);
     * usu.setEstado(1);
     * //comprobamos que el usuario que queremos registrar no existe en la base de
     * datos
     * if (existeUsuario(docIdent)) {
     * System.out.println("Intentado ver si el usuario existe...");
     * JOptionPane.showMessageDialog(null,
     * "El usuario introducido ya existe en la base de datos");
     * } else {
     * System.out.println("El usuario todavia no existe...vamos a crearlo...");
     * registrarUsuario(usu);
     * }
     * 
     * } while (nombre.isEmpty() || primerApe.isEmpty() || genero.isEmpty() ||
     * segundoApe.isEmpty() || password.isEmpty() || diaNac == 0 || mesNac == 0 ||
     * anoNac == 0
     * || tel.isEmpty() || correo.isEmpty() || calle.isEmpty() || numCalle == 0);
     * } else if (op == 2) {
     * System.out.println("Introduce tu documentoIdentificacion: ");
     * docIdent = sc.nextLine();
     * System.out.println("Introduce tu documentoIdentificacion: ");
     * docIdent = sc.nextLine();
     * System.out.println("Introduce una password: ");
     * password = sc.nextLine();
     * 
     * Usuario usu = new Usuario();
     * usu.setDocumentoIdentificacion(docIdent);
     * int usuId = loginUser(usu.getDocumentoIdentificacion(), password);
     * if (usuId == 0) {
     * //enviar datos
     * System.out.println("contraseña o el documento son incorrectos");
     * } else {
     * System.out.println("Contraseña y documento correcto");
     * Usuario objUsu = new Usuario();
     * objUsu = obtenerDatos(docIdent);
     * objUsu.getIdUsuario();
     * objUsu.getNombre();
     * //enviar al home, junto con el id
     * }
     * } else if (op == 3) {
     * System.out.
     * println("Elige el usuario que actualizar (documento) + contraseña:");
     * System.out.println("documento");
     * docIdent = sc.nextLine();
     * System.out.println("documento");
     * docIdent = sc.nextLine();
     * System.out.println("contra");
     * password = sc.nextLine();
     * Usuario usuario = new Usuario();
     * usuario = obtenerDatos(docIdent);
     * 
     * 
     * System.out.println("Introduce un telefono: ");
     * String tel = sc.nextLine();
     * System.out.println("Introduce un correo: ");
     * String correo = sc.nextLine();
     * System.out.println("Introduce una calle: ");
     * String calle = sc.nextLine();
     * System.out.println("Introduce un numero de calle: ");
     * int numCalle = sc.nextInt();
     * System.out.println("Introduce un piso: ");
     * int piso = sc.nextInt();
     * System.out.println("Introduce una letra: ");
     * String letra = sc.nextLine();
     * System.out.println("Introduce una letra: ");
     * letra = sc.nextLine();
     * 
     * usuario.setNumeroTel(tel);
     * usuario.setCorreo(correo);
     * usuario.setCalle(calle);
     * usuario.setNumeroCalle(numCalle);
     * usuario.setPiso(piso);
     * usuario.setLetra(letra);
     * 
     * actualizar(usuario, usuario.getIdUsuario());
     * 
     * 
     * } else if (op == 4) {
     * 
     * }
     * 
     * } catch (Exception ex) {
     * Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null,
     * ex);
     * }
     * }
     * 
     * public static boolean registrarUsuario(Usuario objeto) throws
     * ClassNotFoundException {
     * boolean respuesta = false;
     * Connection cn = Conexion.conectar();
     * String sql =
     * "insert into tb_clientes (nombre, primerApellido, segundoApellido, password, documentoIdentificacion, fechaNacimiento, telefono, correo, calle, numeroCalle, piso, letra, fechaCreacion, genero, estado) "
     * + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
     * PreparedStatement consulta = null;
     * try {
     * consulta = cn.prepareStatement(sql);
     * consulta.setString(1, objeto.getNombre());
     * consulta.setString(2, objeto.getPrimerApellido());
     * consulta.setString(3, objeto.getSegundoApellido());
     * consulta.setBytes(4, objeto.getPassword());
     * consulta.setString(5, objeto.getDocumentoIdentificacion());
     * consulta.setString(6, objeto.getFechaNacimiento());
     * consulta.setString(7, objeto.getNumeroTel());
     * consulta.setString(8, objeto.getCorreo());
     * consulta.setString(9, objeto.getCalle());
     * consulta.setInt(10, objeto.getNumeroCalle());
     * consulta.setInt(11, objeto.getPiso());
     * consulta.setString(12, objeto.getLetra());
     * consulta.setString(13, objeto.getFechaCreacion());
     * consulta.setInt(14, objeto.getGenero());
     * consulta.setInt(15, objeto.getEstado());
     * 
     * if (consulta.executeUpdate() > 0) {
     * respuesta = true;
     * }
     * cn.close();
     * } catch (SQLException e) {
     * System.out.println("Error al guardar usuario: " + e);
     * }
     * return respuesta;
     * }
     */
    public static boolean existeUsuario(String documentoIdentificacion) throws ClassNotFoundException {
        boolean respuesta = false;
        String sql = "select * from tb_clientes where documentoIdentificacion = '" + documentoIdentificacion + "';";
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

    public static int loginUser(String documento, String pass) throws Exception {

        int id = 0;
        byte[] passw = null;

        Connection cn = null;
        try {
            cn = (Connection) Conexion.conectar();
            String sql = "select idCliente, password from tb_clientes where documentoIdentificacion = '" + documento
                    + "';";

            Statement st = null;

            try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    id = rs.getInt(1);
                    passw = rs.getBytes(2);
                }

                // desecnriptamos passw
                String passdesencriptada = descifrarPass(passw);
                if (!passdesencriptada.equals(pass)) {
                    id = 0;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    public static Usuario obtenerDatos(String documentoIdentificacion) {
        System.out.println("Dentro de obtener datos");
        Connection cn = null;
        Usuario obj = new Usuario();
        String nombre;
        String pApe;
        String sApe;
        int idUsuario;
        String fechaNacimiento;
        String numeroTel;
        String correo;
        String calle;
        int numeroCalle;
        int piso = 0;
        String letra;
        String fechaCreacion;
        int genero;
        @SuppressWarnings("unused")
        int estado = 0;
        try {
            cn = (Connection) Conexion.conectar();
            String sql = "select nombre, primerApellido, segundoApellido, idCliente, fechaNacimiento, telefono, correo, calle, numeroCalle, piso, letra, fechaCreacion, "
            +" genero, estado from tb_clientes where documentoIdentificacion = '"
                    + documentoIdentificacion + "';";

            Statement st = null;

            try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                System.out.println("consulta: " + sql);
                System.out.println("realizando consulta");
                while (rs.next()) {

                    nombre = rs.getString("nombre");
                    System.out.println(nombre);
                    obj.setNombre(nombre);
                    pApe = rs.getString("primerApellido");
                    System.out.println(pApe);
                    obj.setPrimerApellido(pApe);
                    sApe = rs.getString("segundoApellido");
                    System.out.println(sApe);
                    obj.setSegundoApellido(sApe);
                    idUsuario = rs.getInt("idCliente");
                    System.out.println(idUsuario);
                    obj.setIdUsuario(idUsuario);
                    fechaNacimiento = rs.getString("fechaNacimiento");
                    System.out.println(fechaNacimiento);
                    obj.setFechaNacimiento(fechaNacimiento);
                    numeroTel = rs.getString("telefono");
                    System.out.println(numeroTel);
                    obj.setNumeroTel(numeroTel);
                    correo = rs.getString("correo");
                    System.out.println(correo);
                    obj.setCorreo(correo);
                    calle = rs.getString("calle");
                    System.out.println(calle);
                    obj.setCalle(calle);
                    numeroCalle = rs.getInt("numeroCalle");
                    System.out.println(numeroCalle);
                    obj.setNumeroCalle(numeroCalle);
                    piso = rs.getInt("piso");
                    System.out.println(piso);
                    obj.setPiso(piso);
                    letra = rs.getString("letra");
                    System.out.println(letra);
                    obj.setLetra(letra);
                    fechaCreacion = rs.getString("fechaCreacion");
                    System.out.println(fechaCreacion);
                    obj.setFechaCreacion(fechaCreacion);
                    genero = rs.getInt("genero");
                    System.out.println(genero);
                    obj.setGenero(genero);

                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;

    }
    //metodo para actualizar
    public static boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        try {

            Connection cn = Conexion.conectar();

            String sql = "update tb_clientes set telefono = ?, correo = ?, calle = ?, numeroCalle = ?, piso = ?, letra = ? where idCliente = ?;";
            PreparedStatement consulta = null;
            try {

                consulta = cn.prepareStatement(sql);
                consulta.setString(1, objeto.getNumeroTel());
                consulta.setString(2, objeto.getCorreo());
                consulta.setString(3, objeto.getCalle());
                consulta.setInt(4, objeto.getNumeroCalle());
                consulta.setInt(5, objeto.getPiso());
                consulta.setString(6, objeto.getLetra());
                consulta.setInt(7, idUsuario);

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

    // metodo para eliminar el usuario
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

    // metodo para encriptar la contraseña
    public static byte[] cifrarPass(String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    // metodo para desencriptar la contraseña
    public static String descifrarPass(byte[] cifrado) throws Exception {
        final Cipher aes = obtieneCipher(false);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    // metodo que obtiene la clave para cifrar/descifrar
    private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "vhsdfsjhbvcxsbVCSVACBSAJBDbqabwdajbsjabcksancbBJHScjsalskaidwoq_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
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

    public static String obtenerFechaActual() {

        Date diaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaActual = sdf.format(diaActual);
        return fechaActual;

    }

    /**
     * @param rutaImg
     * @param idUsuario
     * @return
     */
    public static boolean insertarImg(String rutaImg, int idUsuario)
            throws FileNotFoundException {
        String rutaControlador = System.getProperty("user.dir");
        String rutaImagenes = Paths.get(rutaControlador, "img", "perfil").toString();
        String nombre = "fotoPerfil_" + idUsuario + ".jpg";
        boolean resp = false;

        String sql = "insert into tb_imgPerfil (nombre, imagen, idUsuario) values (?, ?, ?);";
        try {
            Connection cn = Conexion.conectar();
            try {
                FileInputStream imagen = new FileInputStream(rutaImagenes);
                try {
                    PreparedStatement consulta = cn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    consulta.setString(1, nombre);
                    consulta.setBinaryStream(2, imagen);
                    consulta.setInt(3, idUsuario);
                    consulta.executeUpdate();
                    Path origen = Paths.get(rutaImg);
                    Path destino = Paths.get(rutaImagenes, nombre);
                    Path copia = Files.copy(origen, destino);
                    if (copia != null) {
                        resp = true;
                    }
                    cn.close();
                } catch (SQLException e) {
                    System.out.println("Error al insertar imagen: " + e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public static String recuperarImagen(int idUsuario) throws IOException, ClassNotFoundException{
        String rutaImagenRecuperada = "";
        String sql = "select imagen from tb_imgPerfil where idUsuario = '" + idUsuario + "';";
        String rutaActual = System.getProperty("user.dir");
        String rutaImagenes = Paths.get(rutaActual, "img", "perfil").toString();
        try {
            Connection cn = Conexion.conectar();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("imagen");
                rutaImagenRecuperada = Paths.get(rutaImagenes, "fotoPerfil_" + idUsuario + ".jpg")
                        .toString();
                FileOutputStream fos = new FileOutputStream(rutaImagenRecuperada);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0) {
                    fos.write(buffer);
                }
                fos.close();
                is.close();
                System.out.println("Imagen recuperada y guardada correctamente.");
                return rutaImagenRecuperada;
            } else {
                System.out.println("No se encontró ninguna imagen para el usuario especificado.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }

        return rutaImagenRecuperada;
    }




    /*
    // metodo para validar algunos datos
    public static boolean validarEmail(String email) {
        Pattern patron = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = patron.matcher(email);
        return mat.find();
    }

    // validacion documentoIdentificacion
    public static boolean validarDocumentoIdentificacion(String documentoIdentificacion) {
        final String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";
        boolean respuesta = false;
        String intPartDNI = documentoIdentificacion.trim().replaceAll(" ", "").substring(0, 8);
        char ltrDNI = documentoIdentificacion.charAt(8);
        int valNumDni = Integer.parseInt(intPartDNI) % 23;
        System.out.println(valNumDni + "\n\n");

        System.out.println(ltrDNI + "\n\n");
        System.out.println(intPartDNI + "\n\n");
        respuesta = !(documentoIdentificacion.length() != 9 || isStringNumeric(intPartDNI) == false
                || dniChars.charAt(valNumDni) != ltrDNI);

        System.out.println(respuesta + "\n\n");
        return respuesta;
    }

    public static boolean isStringNumeric(String number) {
        boolean isNumeric;
        if (number == null) {
            isNumeric = false;
        } else {
            try {
                @SuppressWarnings("unused")
                Double num = Double.parseDouble(number);
                isNumeric = true;
            } catch (NumberFormatException e) {
                isNumeric = false;
            }
        }
        return isNumeric;
    }

    // validamos que el documento sean solo numeros
    // public static boolean soloNumeros(String documentoIdentificacion) {
    // String cadenaSinLetraFinal = documentoIdentificacion.substring(0,
    // documentoIdentificacion.length() - 1);
    //
    // String numero = "";
    // String dni = "";
    // String[] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    // for (int i = 0; i < cadenaSinLetraFinal.length(); i++) {
    // numero = cadenaSinLetraFinal.substring(i, i + 1);
    // for (int j = 0; i < numeros.length; j++) {
    // if (numero.equals(numeros[j])) {
    // dni = numeros[j];
    // }
    // }
    // }
    // return dni.length() == 8;
    // }
    // validamos que haya una letra en el dni
    // private static String letraDocumentoIdentificacion(String
    // documentoIdentificacion) {
    // int miDNI = Integer.parseInt(documentoIdentificacion.substring(0, 8));
    // int resto = 0;
    // String miLetra = "";
    // String[] asignacionLetra = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
    // "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
    // "Z"};
    //
    // resto = miDNI % 26;
    //
    // miLetra = asignacionLetra[resto];
    //
    // return miLetra;
    // }
    // validamos que las cadenas sean solo de letras(nombre, apellidos, calle)
    // public static boolean validarSoloLetras(String cadena) {
    // for (int i = 0; i < cadena.length(); i++) {
    // if (!Character.isAlphabetic((cadena.charAt(i)))) {
    // return false;
    // }
    // }
    // return true;
    // }
    // validamos que el numero de telefono cumpla con la longitud y que sean solo
    // numeros
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

    // validamos que la fecha de nacimiento esté bien introducida y le damos formato
    public static String validarFecha(int dia, int mes, int ano) throws ParseException {

        // validar lod dias disponibles por cada mes,
        // validar que los meses van de 1 a 12
        // validar que el año está comprendido entre 1910 y el año actual menos 16 años
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        return formatoFecha.parse(dia + "/" + mes + "/" + ano).toString();
    }

    // validamos que el genero sea 'H' o 'M'
    public static int validarGenero(String genero) {

        String gen = genero.toUpperCase();
        int generoInt = 0;
        if (gen.equals("H")) {
            generoInt = 1;
        } else if (gen.equals("M")) {
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
    */
}

package controlador;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.omg.CORBA.portable.InputStream;
import modelo.Cuenta;
import modelo.Usuario;

public class ControladorHTTP extends HttpServlet {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/usuario/loginUser", new LoginHandler());
        server.createContext("/usuario/deleteUser", new DeleteUserHandler());
        server.createContext("/usuario/subirFoto", new SubirFotoHadler());
        server.createContext("/usuario/actualizarUsuario", new ActualizarUsuarioHandler());
        server.createContext("/cuenta/abrirCuenta", new AbrirCuentaHandler());
        server.createContext("/cuenta/actualizarCuenta", new ActualizarCuentaHandler());
        server.createContext("/cuenta/deleteCuenta", new DeletecuentaHandler());
        server.createContext("/transaccion/realizarTransaccion", new RealizarTransaccionHandler());
        server.createContext("/transaccion/consultarTransaccion", new ConsultarTransaccionHandler());
        server.createContext("/transaccion/consultarDetalles", new ConsultarDetallesMovimientosHandler());
        server.createContext("/transaccion/cancelarMovimiento", new CancelarMovimientosHandler());
        server.createContext("/tarjeta/crearTarjeta", new CrearTarjetaHandler());
        server.createContext("/tarjeta/eliminarTarjeta b", new EliminarTarjetaHandler());

        server.start();
    }

    public static String leerCuerpoSolicitud(HttpExchange exchange) throws IOException {
        // Obtener el cuerpo de la solicitud
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }
    // Otros métodos del servlet

    // usuario
    public static int handleHTTPRequestLogin(InetSocketAddress inetSocketAddress) throws Exception {

        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Aquí llamas a la función Login del controlador de usuario
        // Asumiendo que tienes el documento y la contraseña en el cuerpo de la
        // solicitud HTTP
        String documento = null;
        String password = null;
        String[] params = requestBody.toString().split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                if (keyValue[0].equals("documento")) {
                    documento = keyValue[1];
                } else if (keyValue[0].equals("password")) {
                    password = keyValue[1];
                }
            }
        }

        if (documento != null && password != null) {
            // Llama a la función Login del controlador de usuario
            ControladorUsuario controladorUsuario = new ControladorUsuario();
            return controladorUsuario.loginUser(documento, password);
        } else {
            // Manejar el caso en que no se proporcionaron el documento o la contraseña en
            // la solicitud HTTP
            return 0;
        }
    }

    // usuario
    public static int handleHTTPRequestDeleteUser(InetSocketAddress inetSocketAddress, int idUsuario) throws Exception {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Aquí llamas a la función Login del controlador de usuario
        // Asumiendo que tienes el documento y la contraseña en el cuerpo de la
        // solicitud HTTP
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        boolean check = controladorUsuario.eliminar(idUsuario);
        if (check == true) {
            return 1;
        } else {
            return 0;
        }

    }

    // usuario
    public static String handleHTTPRequestSubirFoto(InetSocketAddress inetSocketAddress, String rutaImg, int idUsuario, byte[] imagenBytes)
            throws Exception {

        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }
        boolean insercionExitosa = false;
        try {
            insercionExitosa = ControladorUsuario.insertarImg(rutaImg, idUsuario, imagenBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error al subir la imagen: Archivo no encontrado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al subir la imagen: " + e.getMessage();
        }

        if (insercionExitosa) {
            return "Imagen subida correctamente.";
        } else {
            return "Error al subir la imagen.";
        }
    }

    // usuario
    public static String handleHTTPRequestActualizarUsuario(InetSocketAddress inetSocketAddress, Usuario usuario)
            throws Exception {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        boolean actualizacionExitosa = ControladorUsuario.actualizar(usuario, usuario.getIdUsuario());

        if (actualizacionExitosa) {
            return "Usuario actualizado correctamente.";
        } else {
            return "Error al actualizar el usuario.";
        }
    }

    // cuenta
    public static String handleHTTPRequestAbrirCuenta(InetSocketAddress inetSocketAddress, Cuenta cuenta, int idUsuario)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función abrirCuenta del controlador de cuenta
        boolean aperturaExitosa = ControladorCuenta.abrirCuenta(cuenta, idUsuario);

        if (aperturaExitosa) {
            return "Cuenta abierta correctamente.";
        } else {
            return "Error al abrir la cuenta.";
        }
    }

    // cuenta
    public static String handleHTTPRequestActualizarCuenta(InetSocketAddress inetSocketAddress, Cuenta cuenta)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función actualizar del controlador de cuenta
        boolean actualizacionExitosa = ControladorCuenta.actualizar(cuenta, cuenta.getIdCuenta());

        if (actualizacionExitosa) {
            return "Cuenta actualizada correctamente.";
        } else {
            return "Error al actualizar la cuenta.";
        }
    }

    // cuenta
    public static String handleHTTPRequestEliminarCuenta(InetSocketAddress inetSocketAddress, int idCuenta)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función eliminar del controlador de cuenta
        boolean eliminacionExitosa = ControladorCuenta.eliminar(idCuenta);

        if (eliminacionExitosa) {
            return "Cuenta eliminada correctamente.";
        } else {
            return "Error al eliminar la cuenta.";
        }
    }

    // transacciones
    public static String handleHTTPRequestRealizarTransaccion(InetSocketAddress inetSocketAddress, String nombre,
            int idCliente,
            String iban, String concepto, double cantidad) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función realizarTransaccion del controlador de transaccion
        boolean transaccionExitosa;
        try {
            transaccionExitosa = ControladorTransaccion.realizarTransaccion(nombre, idCliente, iban, concepto,
                    cantidad);
        } catch (ParseException e) {
            return "Error al realizar la transacción: " + e.getMessage();
        }

        if (transaccionExitosa) {
            return "Transacción realizada correctamente.";
        } else {
            return "Error al realizar la transacción.";
        }
    }

    // transacciones
    public static String handleHTTPRequestConsultarTransacciones(InetSocketAddress inetSocketAddress, int idCuenta)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función consultarMovimientos del controlador de cuenta
        ControladorTransaccion controladortTransaccion = new ControladorTransaccion();
        List<Map<String, Object>> movimientos = controladortTransaccion.consultarMovimientos(idCuenta);

        if (movimientos != null && !movimientos.isEmpty()) {
            // Procesar los resultados obtenidos y construir el JSON manualmente
            StringBuilder jsonResponse = new StringBuilder("[");
            for (Map<String, Object> movimiento : movimientos) {
                jsonResponse.append("{");
                for (Map.Entry<String, Object> entry : movimiento.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    jsonResponse.append("\"").append(key).append("\":\"");
                    if (value instanceof Number) {
                        jsonResponse.append(value);
                    } else {
                        jsonResponse.append(value.toString().replace("\"", "\\\""));
                    }
                    jsonResponse.append("\",");
                }
                // Eliminar la coma final
                jsonResponse.deleteCharAt(jsonResponse.length() - 1);
                jsonResponse.append("},");
            }
            // Eliminar la coma final y cerrar el arreglo JSON
            if (movimientos.size() > 0) {
                jsonResponse.deleteCharAt(jsonResponse.length() - 1);
            }
            jsonResponse.append("]");

            return jsonResponse.toString();
        } else {
            return "No se encontraron movimientos para la cuenta especificada.";
        }
    }

    // transacciones
    public static String handleHTTPRequestDetallesMovimientos(InetSocketAddress inetSocketAddress, int idMovimiento,
            int idUsuario)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función obtenerDetalleMovimiento del controlador de transaccion
        List<Map<String, Object>> detallesMovimientos = ControladorTransaccion.obtenerDetalleMovimiento(idMovimiento,
                idUsuario);

        // Construir el JSON manualmente
        StringBuilder jsonResponse = new StringBuilder("[");
        for (Map<String, Object> detalle : detallesMovimientos) {
            jsonResponse.append("{");
            for (Map.Entry<String, Object> entry : detalle.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                jsonResponse.append("\"").append(key).append("\":\"");
                if (value instanceof Number) {
                    jsonResponse.append(value);
                } else {
                    jsonResponse.append(value.toString().replace("\"", "\\\""));
                }
                jsonResponse.append("\",");
            }
            // Eliminar la coma final
            jsonResponse.deleteCharAt(jsonResponse.length() - 1);
            jsonResponse.append("},");
        }
        // Eliminar la coma final y cerrar el arreglo JSON
        if (detallesMovimientos.size() > 0) {
            jsonResponse.deleteCharAt(jsonResponse.length() - 1);
        }
        jsonResponse.append("]");

        // Retornar el JSON
        return jsonResponse.toString();
    }

    // transaccion
    public static String handleHTTPRequestCancelarMovimientos(InetSocketAddress inetSocketAddress, Cuenta cuenta,
            int idTransaccion)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función cancelarMovimientos del controlador de cuenta
        ControladorTransaccion controladorTransaccion = new ControladorTransaccion();
        boolean cancelacionExitosa = controladorTransaccion.cancelarMovimientos(cuenta, idTransaccion);

        if (cancelacionExitosa) {
            return "Movimientos cancelados correctamente.";
        } else {
            return "Error al cancelar los movimientos.";
        }
    }

    // tarjeta
    public static String handleHTTPRequestcrearTarjeta(InetSocketAddress inetSocketAddress, String estadoTarjeta,
            String tipoTarjeta,
            String marcaTarjeta, String numeroTarjeta) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función crearTarjeta del controlador de tarjeta
        boolean creacionExitosa = ControladorTarjeta.crearTarjeta(estadoTarjeta, tipoTarjeta, marcaTarjeta,
                numeroTarjeta);

        if (creacionExitosa == true) {
            return "Tarjeta creada correctamente.";
        } else {
            return "Error al crear la tarjeta.";
        }
    }

    // tarjeta
    public static String handleHTTPRequestEliminarTarjeta(InetSocketAddress inetSocketAddress, int idTarjeta)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(((ServletRequest) inetSocketAddress).getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        boolean bodyStarted = false;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                // El cuerpo de la solicitud HTTP comienza después de una línea en blanco
                bodyStarted = true;
                continue;
            }
            if (bodyStarted) {
                requestBody.append(line).append("\n");
            }
        }

        // Llamada a la función eliminar del controlador de tarjeta
        ControladorTarjeta controladorTarjeta = new ControladorTarjeta();
        boolean eliminacionExitosa = controladorTarjeta.eliminar(idTarjeta);

        if (eliminacionExitosa) {
            return "Tarjeta eliminada correctamente.";
        } else {
            return "Error al eliminar la tarjeta.";
        }
    }

    public void init() throws ServletException {
        super.init();

        // Configuración de CORS
        FilterRegistration.Dynamic corsFilter = getServletContext().addFilter("CorsFilter", CorsFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");
    }

    public String toString() {
        return "ControladorHTTP []";
    }

}

class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Llamar a la función del controlador y obtener el resultado
            int resultado = ControladorHTTP.handleHTTPRequestLogin(exchange.getRemoteAddress());

            // Enviar la respuesta al cliente
            String response = Integer.toString(resultado);
            HTTPHandler.enviarRespuesta(exchange, response);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    @Override
    public String toString() {
        return "LoginHandler []";
    }

}

class DeleteUserHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            int idUsuario = parsearIdUsuarioDesdeSolicitud(exchange);
            // Llamar a la función del controlador y obtener el resultado
            int resultado = ControladorHTTP.handleHTTPRequestDeleteUser(exchange.getRemoteAddress(), idUsuario);

            // Enviar la respuesta al cliente
            String response = Integer.toString(resultado);
            HTTPHandler.enviarRespuesta(exchange, response);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    public int parsearIdUsuarioDesdeSolicitud(HttpExchange exchange) {
        String uri = exchange.getRequestURI().toString();

        // Dividir la URL en partes separadas por '/'
        String[] partesUri = uri.split("/");

        // El ID de usuario podría estar en la última parte de la URL
        // Suponiendo que la URL tiene el formato "/usuario/{idUsuario}/abrirCuenta"
        int idUsuario = -1;
        try {
            idUsuario = Integer.parseInt(partesUri[partesUri.length - 2]);
        } catch (NumberFormatException e) {
            // Si ocurre un error al parsear el ID, mantener el valor predeterminado
            e.printStackTrace();
        }

        return idUsuario;
    }

}

class SubirFotoHadler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde el cuerpo o la URL
            String rutaImg = parsearRutaImagenDesdeSolicitud(exchange);
            int idUsuario = parsearIdUsuarioDesdeSolicitud(exchange);

            // Obtener los datos de la imagen desde el cuerpo de la solicitud
            byte[] imagenBytes = obtenerDatosImagenDesdeCuerpoSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestSubirFoto(exchange.getRemoteAddress(), rutaImg,
                    idUsuario, imagenBytes);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
            ;
        }

    }

    // Método para parsear la ruta de la imagen desde la solicitud HTTP
    public String parsearRutaImagenDesdeSolicitud(HttpExchange exchange) {
        // Implementa la lógica para obtener la ruta de la imagen desde la solicitud
        // Por ejemplo, si la ruta está en los parámetros de la URL:
        String uri = exchange.getRequestURI().toString();
        // Supongamos que la ruta de la imagen está después de la última barra en la URL
        String[] partes = uri.split("/");
        return partes[partes.length - 1];
    }

    // Método para parsear el idUsuario desde la solicitud HTTP
    public int parsearIdUsuarioDesdeSolicitud(HttpExchange exchange) {
        // Implementa la lógica para obtener el idUsuario desde la solicitud
        // Por ejemplo, si el idUsuario está en los parámetros de la URL:
        String uri = exchange.getRequestURI().toString();
        // Supongamos que el idUsuario está en la penúltima parte de la URL
        String[] partes = uri.split("/");
        return Integer.parseInt(partes[partes.length - 2]);
    }

    // Método para obtener los datos de la imagen desde el cuerpo de la solicitud HTTP
    public static byte[] obtenerDatosImagenDesdeCuerpoSolicitud(HttpExchange exchange) throws IOException {
        // Implementa la lógica para obtener los datos de la imagen desde el cuerpo de la solicitud
        // Este método ya está implementado en la clase
        InputStream inputStream = (InputStream) exchange.getRequestBody();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

}

class ActualizarUsuarioHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear el cuerpo de la solicitud para obtener el usuario
            Usuario usuario = parsearUsuarioDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestActualizarUsuario(exchange.getRemoteAddress(), usuario);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }

    }

    public Usuario parsearUsuarioDesdeSolicitud(HttpExchange exchange) throws IOException {
        String requestBody = ControladorHTTP.leerCuerpoSolicitud(exchange);
        String[] parametros = requestBody.split("&");

        // Creamos un mapa para almacenar los datos del usuario
        Map<String, String> datosUsuario = new HashMap<>();

        // Iteramos sobre los pares clave-valor y los almacenamos en el mapa
        for (String parametro : parametros) {
            String[] keyValue = parametro.split("=");
            if (keyValue.length == 2) {
                // Decodificamos los valores URL (si es necesario)
                String clave = keyValue[0];
                String valor = keyValue[1];
                datosUsuario.put(clave, valor);
            }
        }

        // Ahora, construimos el objeto Usuario usando los datos del mapa
        int idUsuario = Integer.parseInt(datosUsuario.get("idUsuario"));
        String nombre = datosUsuario.get("nombre");
        String primerApellido = datosUsuario.get("primerApellido");
        String segundoApellido = datosUsuario.get("segundoApellido");
        byte[] password = datosUsuario.get("password").getBytes(); // Suponiendo que el password se envía como texto
                                                                   // plano
        String documentoIdentificacion = datosUsuario.get("documentoIdentificacion");
        String fechaNacimiento = datosUsuario.get("fechaNacimiento");
        String numeroTel = datosUsuario.get("numeroTel");
        String correo = datosUsuario.get("correo");
        String calle = datosUsuario.get("calle");
        int numeroCalle = Integer.parseInt(datosUsuario.get("numeroCalle"));
        int piso = Integer.parseInt(datosUsuario.get("piso"));
        String letra = datosUsuario.get("letra");
        String fechaCreacion = datosUsuario.get("fechaCreacion");
        int genero = Integer.parseInt(datosUsuario.get("genero"));
        int estado = Integer.parseInt(datosUsuario.get("estado"));

        // Creamos y retornamos el objeto Usuario
        return new Usuario(idUsuario, nombre, primerApellido, segundoApellido, password, documentoIdentificacion,
                fechaNacimiento, numeroTel, correo, calle, numeroCalle, piso, letra, fechaCreacion, genero, estado);
    }

}

class AbrirCuentaHandler implements HttpHandler {
    public static final String HttpExchange = null;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            // Aquí estoy asumiendo que obtienes la cuenta y el idUsuario de la solicitud
            // HTTP
            Cuenta cuenta = parsearCuentaDesdeSolicitud(exchange);
            int idUsuario = parsearIdUsuarioDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestAbrirCuenta(exchange.getRemoteAddress(), cuenta,
                    idUsuario);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    // Método para parsear la cuenta desde la solicitud HTTP (simulado)
    public Cuenta parsearCuentaDesdeSolicitud(HttpExchange exchange) throws IOException {
        Map<String, String> datosCuenta = new HashMap<>();
        String requestBody = ControladorHTTP.leerCuerpoSolicitud(exchange);
        // Separa los datos del cuerpo de la solicitud (suponiendo que están en formato
        // de pares clave=valor separados por &)
        String[] parametros = requestBody.split("&");
        for (String parametro : parametros) {
            String[] keyValue = parametro.split("=");
            if (keyValue.length == 2) {
                // Almacena los datos en el mapa
                datosCuenta.put(keyValue[0], keyValue[1]);
            }
        }

        // Extrae los datos de la cuenta del mapa y crea un objeto de tipo Cuenta
        int idCuenta = Integer.parseInt(datosCuenta.get("idCuenta"));
        int titularCuenta = Integer.parseInt(datosCuenta.get("titularCuenta"));
        String pais = datosCuenta.get("pais");
        String codControlPais = datosCuenta.get("codControlPais");
        String entidad = datosCuenta.get("entidad");
        String oficina = datosCuenta.get("oficina");
        String codControlCuenta = datosCuenta.get("codControlCuenta");
        String numeroCuenta = datosCuenta.get("numeroCuenta");
        double saldo = Double.parseDouble(datosCuenta.get("saldo"));
        String tipoMoneda = datosCuenta.get("tipoMoneda");
        int idTarjeta = Integer.parseInt(datosCuenta.get("idTarjeta"));

        // Retorna un nuevo objeto Cuenta con los datos obtenidos
        return new Cuenta(idCuenta, titularCuenta, pais, codControlPais, entidad, oficina, codControlCuenta,
                numeroCuenta, saldo, tipoMoneda, idTarjeta);

    }

    // Método para parsear el idUsuario desde la solicitud HTTP (simulado)
    public int parsearIdUsuarioDesdeSolicitud(HttpExchange exchange) {
        // Obtener la URL de la solicitud
        String uri = exchange.getRequestURI().toString();

        // Dividir la URL en partes separadas por '/'
        String[] partesUri = uri.split("/");

        // El ID de usuario podría estar en la última parte de la URL
        // Suponiendo que la URL tiene el formato "/usuario/{idUsuario}/abrirCuenta"
        int idUsuario = -1; // Valor predeterminado en caso de que no se pueda parsear el ID

        // Intentar obtener el ID de usuario desde la última parte de la URL
        try {
            idUsuario = Integer.parseInt(partesUri[partesUri.length - 2]);
        } catch (NumberFormatException e) {
            // Si ocurre un error al parsear el ID, mantener el valor predeterminado
            e.printStackTrace();
        }

        return idUsuario;
    }

}

class ActualizarCuentaHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde el cuerpo de la
            // solicitud
            Cuenta cuenta = parsearCuentaDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestActualizarCuenta(exchange.getRemoteAddress(), cuenta);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    public Cuenta parsearCuentaDesdeSolicitud(HttpExchange exchange) throws IOException {

        // Obtener el cuerpo de la solicitud HTTP
        InputStream requestBodyStream = (InputStream) exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBodyStream));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();
        Map<String, String> datosCuenta = new HashMap<>();
        String requestBodyString = requestBody.toString();
        // Separa los datos del cuerpo de la solicitud (suponiendo que están en formato
        // de pares clave=valor separados por &)
        // Separar los datos del cuerpo de la solicitud (suponiendo que están en formato
        // de pares clave=valor separados por "&")
        String[] parametros = requestBodyString.split("&");
        for (String parametro : parametros) {
            String[] keyValue = parametro.split("=");
            if (keyValue.length == 2) {
                // Almacena los datos en el mapa
                datosCuenta.put(keyValue[0], keyValue[1]);
            }
        }

        // Extrae los datos de la cuenta del mapa y crea un objeto de tipo Cuenta
        int idCuenta = Integer.parseInt(datosCuenta.get("idCuenta"));
        int titularCuenta = Integer.parseInt(datosCuenta.get("titularCuenta"));
        String pais = datosCuenta.get("pais");
        String codControlPais = datosCuenta.get("codControlPais");
        String entidad = datosCuenta.get("entidad");
        String oficina = datosCuenta.get("oficina");
        String codControlCuenta = datosCuenta.get("codControlCuenta");
        String numeroCuenta = datosCuenta.get("numeroCuenta");
        double saldo = Double.parseDouble(datosCuenta.get("saldo"));
        String tipoMoneda = datosCuenta.get("tipoMoneda");
        int idTarjeta = Integer.parseInt(datosCuenta.get("idTarjeta"));

        // Retorna un nuevo objeto Cuenta con los datos obtenidos
        return new Cuenta(idCuenta, titularCuenta, pais, codControlPais, entidad, oficina, codControlCuenta,
                numeroCuenta, saldo, tipoMoneda, idTarjeta);

    }
}

class DeletecuentaHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear el idCuenta desde la solicitud, por ejemplo, desde la URL o el cuerpo
            int idCuenta = parsearIdCuentaDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestEliminarCuenta(exchange.getRemoteAddress(), idCuenta);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }

    }

    public int parsearIdCuentaDesdeSolicitud(HttpExchange exchange) {
        String uri = exchange.getRequestURI().toString();
        String[] parts = uri.split("/");
        // Suponiendo que el id de la cuenta está en la última parte de la URL
        return Integer.parseInt(parts[parts.length - 1]);
    }
}

class RealizarTransaccionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            // Aquí estoy asumiendo que obtienes el idTarjeta de la solicitud HTTP
            String nombre = parsearNombreDesdeSolicitud(exchange);
            int idCliente = parsearIdClienteDesdeSolicitud(exchange);
            String iban = parsearIbanDesdeSolicitud(exchange);
            String concepto = parsearConceptoDesdeSolicitud(exchange);
            double cantidad = parsearCantidadDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestRealizarTransaccion(exchange.getRemoteAddress(), nombre,
                    idCliente, iban, concepto, cantidad);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }

    }

    private int parsearIdClienteDesdeSolicitud(HttpExchange exchange) {
        String queryString = exchange.getRequestURI().getQuery();
        String[] queryParams = queryString.split("&");
        for (String queryParam : queryParams) {
            String[] pair = queryParam.split("=");
            if (pair.length == 2 && pair[0].equals("idCliente")) {
                return Integer.parseInt(pair[1]);
            }
        }
        throw new IllegalArgumentException("IdCliente no encontrado en la solicitud");

    }

    private String parsearConceptoDesdeSolicitud(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }
        // Suponiendo que el concepto está en el cuerpo de la solicitud como un
        // parámetro llamado "concepto"
        String[] parametros = requestBody.toString().split("&");
        for (String parametro : parametros) {
            String[] keyValue = parametro.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("concepto")) {
                return keyValue[1];
            }
        }
        throw new IllegalArgumentException("Concepto no encontrado en el cuerpo de la solicitud");

    }

    private double parsearCantidadDesdeSolicitud(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }
        // Suponiendo que la cantidad está en el cuerpo de la solicitud como un
        // parámetro llamado "cantidad"
        String[] parametros = requestBody.toString().split("&");
        for (String parametro : parametros) {
            String[] keyValue = parametro.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("cantidad")) {
                return Double.parseDouble(keyValue[1]);
            }
        }
        throw new IllegalArgumentException("Cantidad no encontrada en el cuerpo de la solicitud");

    }

    private String parsearIbanDesdeSolicitud(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }
        // Suponiendo que el IBAN está en el cuerpo de la solicitud como un parámetro
        // llamado "iban"
        String[] parametros = requestBody.toString().split("&");
        for (String parametro : parametros) {
            String[] keyValue = parametro.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("iban")) {
                return keyValue[1];
            }
        }
        throw new IllegalArgumentException("IBAN no encontrado en el cuerpo de la solicitud");

    }

    private String parsearNombreDesdeSolicitud(HttpExchange exchange) {
        String queryString = exchange.getRequestURI().getQuery();
        String[] queryParams = queryString.split("&");
        for (String queryParam : queryParams) {
            String[] pair = queryParam.split("=");
            if (pair.length == 2 && pair[0].equals("nombre")) {
                return pair[1];
            }
        }
        throw new IllegalArgumentException("Nombre no encontrado en la solicitud");

    }

}

class ConsultarTransaccionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            // Aquí estoy asumiendo que obtienes el idCuenta de la solicitud HTTP
            int idCuenta = parsearIdCuentaDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestConsultarTransacciones(exchange.getRemoteAddress(),
                    idCuenta);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    // Método para parsear el idCuenta desde la solicitud HTTP (simulado)
    public int parsearIdCuentaDesdeSolicitud(HttpExchange exchange) {
        // Aquí debes implementar la lógica para extraer el idCuenta de la solicitud
        // Por ahora, solo retornar un idCuenta simulado
        return 456; // Ejemplo de idCuenta
    }

}

class ConsultarDetallesMovimientosHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            // Aquí estoy asumiendo que obtienes el idMovimiento y el idUsuario de la
            // solicitud HTTP
            int idMovimiento = parsearIdMovimientoDesdeSolicitud(exchange);
            int idUsuario = parsearIdUsuarioDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestDetallesMovimientos(exchange.getRemoteAddress(),
                    idMovimiento, idUsuario);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    // Método para parsear el idMovimiento desde la solicitud HTTP (simulado)
    public int parsearIdMovimientoDesdeSolicitud(HttpExchange exchange) {
        String queryString = exchange.getRequestURI().getQuery();
        String[] queryParams = queryString.split("&");
        for (String queryParam : queryParams) {
            String[] pair = queryParam.split("=");
            if (pair.length == 2 && pair[0].equals("idMovimiento")) {
                return Integer.parseInt(pair[1]);
            }
        }
        throw new IllegalArgumentException("idMovimiento no encontrado en la solicitud");

    }

    // Método para parsear el idUsuario desde la solicitud HTTP (simulado)
    public int parsearIdUsuarioDesdeSolicitud(HttpExchange exchange) {
        // Aquí podrías leer el idUsuario de manera similar al idMovimiento,
        // por ejemplo, desde los parámetros de la URL o el cuerpo de la solicitud.
        // Si estás usando un método específico de autenticación, podrías obtener
        // el idUsuario de ese método.
        // Esta es una implementación de ejemplo, deberás ajustarla según tus
        // necesidades.
        // Aquí simplemente se extrae de los parámetros de la URL.
        String queryString = exchange.getRequestURI().getQuery();
        String[] queryParams = queryString.split("&");
        for (String queryParam : queryParams) {
            String[] pair = queryParam.split("=");
            if (pair.length == 2 && pair[0].equals("idUsuario")) {
                return Integer.parseInt(pair[1]);
            }
        }
        throw new IllegalArgumentException("idUsuario no encontrado en la solicitud");
    }
}

class CancelarMovimientosHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            // Aquí estoy asumiendo que obtienes la cuenta y el idTransaccion de la
            // solicitud HTTP
            Cuenta cuenta = parsearCuentaDesdeSolicitud(exchange);
            int idTransaccion = parsearIdTransaccionDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestCancelarMovimientos(exchange.getRemoteAddress(),
                    cuenta, idTransaccion);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    public Cuenta parsearCuentaDesdeSolicitud(HttpExchange exchange) throws IOException {
        // Obtener el cuerpo de la solicitud
        InputStream requestBodyStream = (InputStream) exchange.getRequestBody();
        BufferedReader requestBodyReader = new BufferedReader(new InputStreamReader(requestBodyStream));

        // Leer el cuerpo de la solicitud y convertirlo a un objeto Cuenta usando Gson
        Gson gson = new Gson();
        Cuenta cuenta = gson.fromJson(requestBodyReader, Cuenta.class);

        return cuenta;
    }

    public int parsearIdTransaccionDesdeSolicitud(HttpExchange exchange) throws IOException {
        // Obtener el cuerpo de la solicitud
        InputStream requestBodyStream = (InputStream) exchange.getRequestBody();
        BufferedReader requestBodyReader = new BufferedReader(new InputStreamReader(requestBodyStream));

        // Leer el cuerpo de la solicitud y convertirlo a un objeto JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBodyReader, JsonObject.class);

        // Obtener el idTransaccion del JSON
        int idTransaccion = jsonObject.get("idTransaccion").getAsInt();

        return idTransaccion;
    }

}

class CrearTarjetaHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            // Aquí estoy asumiendo que obtienes los parámetros desde la solicitud HTTP
            String estadoTarjeta = parsearParametroDesdeSolicitud(exchange, "estadoTarjeta");
            String tipoTarjeta = parsearParametroDesdeSolicitud(exchange, "tipoTarjeta");
            String marcaTarjeta = parsearParametroDesdeSolicitud(exchange, "marcaTarjeta");
            String numeroTarjeta = parsearParametroDesdeSolicitud(exchange, "numeroTarjeta");

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestcrearTarjeta(exchange.getRemoteAddress(),
                    estadoTarjeta, tipoTarjeta, marcaTarjeta, numeroTarjeta);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

    public String parsearParametroDesdeSolicitud(HttpExchange exchange, String parametro)
            throws UnsupportedEncodingException {
        String consulta = exchange.getRequestURI().getQuery();
        if (consulta != null) {
            // Dividir la consulta en pares clave-valor
            String[] pares = consulta.split("&");
            for (String par : pares) {
                // Dividir el par en clave y valor
                String[] keyValue = par.split("=");
                if (keyValue.length == 2 && keyValue[0].equals(parametro)) {
                    // Si la clave coincide con el parámetro deseado, decodificar y retornar el
                    // valor
                    return URLDecoder.decode(keyValue[1], "UTF-8");
                }
            }
        }
        // Si no se encuentra el parámetro, retornar null
        return null;
    }

}

class EliminarTarjetaHandler implements HttpHandler {
    public static int parsearIdTarjetaDesdeSolicitud(HttpExchange exchange) throws IOException {
        // Obtener el método de la solicitud (GET, POST, etc.)
        String requestMethod = exchange.getRequestMethod();

        // Si es una solicitud GET, intenta extraer el idTarjeta de la URL
        if (requestMethod.equalsIgnoreCase("GET")) {
            // Obtener la URI de la solicitud
            String uri = exchange.getRequestURI().toString();

            // Buscar el parámetro "idTarjeta" en la URI
            String[] queryParams = uri.split("\\?");
            if (queryParams.length > 1) {
                Map<String, String> params = getQueryParams(queryParams[1]);
                if (params.containsKey("idTarjeta")) {
                    return Integer.parseInt(params.get("idTarjeta"));
                }
            }
        }

        // Si es una solicitud POST, intenta extraer el idTarjeta del cuerpo de la
        // solicitud
        if (requestMethod.equalsIgnoreCase("POST")) {
            // Leer el cuerpo de la solicitud
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            // Buscar el parámetro "idTarjeta" en el cuerpo de la solicitud
            Map<String, String> params = getQueryParams(requestBody.toString());
            if (params.containsKey("idTarjeta")) {
                return Integer.parseInt(params.get("idTarjeta"));
            }
        }

        // Si no se encuentra el parámetro "idTarjeta", lanzar una excepción o devolver
        // un valor por defecto
        throw new IllegalArgumentException("No se pudo encontrar el parámetro 'idTarjeta' en la solicitud.");
    }

    // Método para analizar los parámetros de consulta de la URL
    public static Map<String, String> getQueryParams(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = idx > 0 ? pair.substring(0, idx) : pair;
                String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : null;
                params.put(key, value);
            }
        }
        return params;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Manejar la solicitud
        try {
            // Parsear los parámetros de la solicitud, por ejemplo, desde la URL o el cuerpo
            int idTarjeta = parsearIdTarjetaDesdeSolicitud(exchange);

            // Llamar a la función del controlador y obtener el resultado
            String resultado = ControladorHTTP.handleHTTPRequestEliminarTarjeta(exchange.getRemoteAddress(),
                    idTarjeta);

            // Enviar la respuesta al cliente
            HTTPHandler.enviarRespuesta(exchange, resultado);
        } catch (Exception e) {
            // Enviar una respuesta de error al cliente si ocurre una excepción
            HTTPHandler.enviarRespuestaError(exchange, e);
        }
    }

}

class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No es necesario implementar esta función, pero debe estar presente debido a
        // la interfaz
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Permitir solicitudes desde cualquier origen
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        // Permitir los métodos HTTP que deseas permitir (GET, POST, PUT, DELETE, etc.)
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // Permitir ciertos encabezados
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Indicar si las credenciales pueden ser incluidas en las solicitudes
        // (true/false)
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // No es necesario implementar esta función, pero debe estar presente debido a
        // la interfaz
    }
}

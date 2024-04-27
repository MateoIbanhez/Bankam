package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import modelo.Cuenta;
import modelo.Usuario;

public class ControladorHTTP {
    // usuario
    @SuppressWarnings("unused")
    private static int handleHTTPRequestLogin(Socket clientSocket) throws Exception {

        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
            @SuppressWarnings("unused")
            ControladorUsuario controladorUsuario = new ControladorUsuario();
            return controladorUsuario.loginUser(documento, password);
        } else {
            // Manejar el caso en que no se proporcionaron el documento o la contraseña en
            // la solicitud HTTP
            return 0;
        }
    }

    // usuario
    @SuppressWarnings("unused")
    private static void handleHTTPRequestDeleteUser(Socket clientSocket, int idUsuario) throws Exception {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
        controladorUsuario.eliminar(idUsuario);

    }

    // usuario
    @SuppressWarnings("unused")
    private static String handleHTTPRequestSubirFoto(Socket clientSocket, String rutaImg, int idUsuario)
            throws Exception {

        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
        boolean insercionExitosa = ControladorUsuario.insertarImg(rutaImg, idUsuario);

        if (insercionExitosa) {
            return "Imagen subida correctamente.";
        } else {
            return "Error al subir la imagen.";
        }
    }

    // usuario
    @SuppressWarnings("unused")
    private static String handleHTTPRequestActualizarUsuario(Socket clientSocket, Usuario usuario) throws Exception {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    @SuppressWarnings("unused")
    private static String handleHTTPRequestAbrirCuenta(Socket clientSocket, Cuenta cuenta, int idUsuario)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    @SuppressWarnings("unused")
    private static String handleHTTPRequestActualizarCuenta(Socket clientSocket, Cuenta cuenta) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    @SuppressWarnings("unused")
    private static String handleHTTPRequestEliminarCuenta(Socket clientSocket, int idCuenta) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    @SuppressWarnings("unused")
    private static String handleHTTPRequestRealizarTransaccion(Socket clientSocket, String nombre, int idCliente,
            String iban, String concepto, double cantidad) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    @SuppressWarnings("unused")
    private static String handleHTTPRequestConsultarTransacciones(Socket clientSocket, int idCuenta) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    @SuppressWarnings("unused")
    private static String handleHTTPRequestDetallesMovimientos(Socket clientSocket, int idMovimiento, int idUsuario)
            throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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

    //transaccion
    @SuppressWarnings("unused")
    private static String handleHTTPRequestCancelarMovimientos(Socket clientSocket, Cuenta cuenta, int idTransaccion) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
    
    //tarjeta
    private static String handleHTTPRequestcrearTarjeta(Socket clientSocket, String estadoTarjeta, String tipoTarjeta, String marcaTarjeta, String numeroTarjeta) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
        boolean creacionExitosa = ControladorTarjeta.crearTarjeta(estadoTarjeta, tipoTarjeta, marcaTarjeta, numeroTarjeta);
        
        if (creacionExitosa == true) {
            return "Tarjeta creada correctamente.";
        } else {
            return "Error al crear la tarjeta.";
        }
    }

    //tarjeta
    private static String handleHTTPRequestEliminarTarjeta(Socket clientSocket, int idTarjeta) throws IOException {
        // Leer la solicitud HTTP
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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


    @Override
    public String toString() {
        return "ControladorHTTP []";
    }

}

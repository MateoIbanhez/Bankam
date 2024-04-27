package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorHTTP {

    @SuppressWarnings({ "static-access", "unused" })
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
    // Asumiendo que tienes el documento y la contraseña en el cuerpo de la solicitud HTTP
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
        // Manejar el caso en que no se proporcionaron el documento o la contraseña en la solicitud HTTP
        return 0;
    }
}
    }

    /*
     * public static void main(String[] args) throws IOException {
     * // Crea un servidor HTTP en el puerto 5173
     * HttpServer server = HttpServer.create(new InetSocketAddress(5173), 0);
     * 
     * // Crea contextos para los diferentes controladores
     * server.createContext("/api/usuario/login", new ControladorUsuario().new
     * LoginHandler());
     * server.createContext("/api/cuenta", new ControladorCuenta().new
     * CuentaHandler());
     * server.createContext("/api/tarjeta", new ControladorTarjeta().new
     * TarjetaHandler());
     * server.createContext("/api/transaccion", new ControladorTransaccion().new
     * TransaccionHandler());
     * 
     * // Inicia el servidor
     * server.start();
     * 
     * System.out.println("Servidor iniciado en el puerto 5173...");
     * }
     * 
     * 
     * static class HelloHandler implements HttpHandler {
     * 
     * @Override
     * public void handle(HttpExchange exchange) throws IOException {
     * // Configura la respuesta HTTP
     * String response = "¡Hola desde el servidor Java!";
     * exchange.sendResponseHeaders(200, response.getBytes().length);
     * 
     * // Obtiene el flujo de salida para escribir la respuesta
     * OutputStream os = exchange.getResponseBody();
     * os.write(response.getBytes());
     * os.close();
     * }
     * }
     */

}

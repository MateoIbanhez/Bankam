package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorHTTP {


    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(5173); // Puerto 5173
        System.out.println("Servidor HTTP escuchando en el puerto 5173...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Esperar una conexión entrante
            System.out.println("Nueva conexión entrante: " + clientSocket);

            // Leer la solicitud HTTP
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

            // Enviar respuesta HTTP
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println();
            writer.println("¡Hola desde el servidor HTTP básico en Java!");
            writer.flush();

            // Cerrar la conexión con el cliente
            clientSocket.close();
        }
    }

    /*
    public static void main(String[] args) throws IOException {
        // Crea un servidor HTTP en el puerto 5173
        HttpServer server = HttpServer.create(new InetSocketAddress(5173), 0);
        
        // Crea contextos para los diferentes controladores
        server.createContext("/api/usuario/login", new ControladorUsuario().new LoginHandler());
        server.createContext("/api/cuenta", new ControladorCuenta().new CuentaHandler());
        server.createContext("/api/tarjeta", new ControladorTarjeta().new TarjetaHandler());
        server.createContext("/api/transaccion", new ControladorTransaccion().new TransaccionHandler());
        
        // Inicia el servidor
        server.start();
        
        System.out.println("Servidor iniciado en el puerto 5173...");
    }


    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Configura la respuesta HTTP
            String response = "¡Hola desde el servidor Java!";
            exchange.sendResponseHeaders(200, response.getBytes().length);

            // Obtiene el flujo de salida para escribir la respuesta
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    } 
    */

}

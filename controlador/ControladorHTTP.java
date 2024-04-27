package controlador;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class ControladorHTTP {

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
}

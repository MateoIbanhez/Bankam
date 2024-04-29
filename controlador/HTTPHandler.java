package controlador;

import java.io.IOException;
import org.omg.CORBA.portable.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public interface HTTPHandler {
    
    public static void enviarRespuesta(HttpExchange exchange, String resultado) throws IOException {
        exchange.sendResponseHeaders(200, resultado.length());
        OutputStream os = (OutputStream) exchange.getResponseBody();
        os.write(resultado.getBytes());
        os.close();
    }

    public static void enviarRespuestaError(HttpExchange exchange, Exception e) throws IOException {
        String errorMessage = "Error interno en el servidor: " + e.getMessage();
        exchange.sendResponseHeaders(500, errorMessage.length());
        OutputStream os = (OutputStream) exchange.getResponseBody();
        os.write(errorMessage.getBytes());
        os.close();
    }

}

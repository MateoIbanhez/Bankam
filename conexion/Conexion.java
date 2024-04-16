package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;


public class Conexion {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static Connection conectar() throws ClassNotFoundException {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/bd_banco";
        Connection cn = null;
        try {
            
            Class.forName(DRIVER_CLASS);
            cn=(Connection)DriverManager.getConnection(url,username,password);
            
            return (Connection) cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        }
        return (Connection) cn;
    }
}

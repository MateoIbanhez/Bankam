package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() throws ClassNotFoundException {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/db_banco";
        Connection cn = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            cn=(Connection)DriverManager.getConnection(url,username,password);
            
            return (Connection) cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        }
        return (Connection) cn;
    }
}

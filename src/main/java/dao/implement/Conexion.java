
package dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Función: conectar el sistema CrediYa con MySQL.
 */
public class Conexion{
    private static final String URL =
            "jdbc:mysql://localhost:3306/crediya";

    private static final String USER = "root";

    private static final String PASSWORD = "TU_CONTRASEÑA";

    // Establecer conexión con MySQL.
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

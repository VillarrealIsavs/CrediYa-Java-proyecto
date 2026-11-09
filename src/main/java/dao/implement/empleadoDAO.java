
package dao.implement;

import dao.interfaces.IEmpleadoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;

/*
    Función: guardar y consultar empleados en la base de datos CrediYa.
 */
public class empleadoDAO implements IEmpleadoDao {

    // Registrar un empleado en la base de datos
    @Override
    public boolean guardar(Empleado empleado) {

        String sql = "INSERT INTO empleados "
                + "(nombre, documento, rol, correo, salario) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getnombre());
            ps.setString(2, empleado.getdocumento());
            ps.setString(3, empleado.getrol());
            ps.setString(4, empleado.getcorreo());
            ps.setDouble(5, empleado.getsalario());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar empleado: "
                    + e.getMessage());
            return false;
        }
    }

    // Consultar todos los empleados registrados
    @Override
    public List<Empleado> listar() {

        String sql = "SELECT * FROM empleados";

        List<Empleado> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Empleado e = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("documento"),
                        rs.getString("correo"),
                        rs.getString("rol"),
                        rs.getDouble("salario")
                );

                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar empleados: "
                    + e.getMessage());
        }

        return lista;
    }
}

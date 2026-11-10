
package dao.implement;

import dao.interfaces.IPrestamoDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Prestamo;
import model.Cliente;
import model.Empleado;

/*
 * Función: guardar, consultar y actualizar préstamos.
 */
public class prestamoDAO implements IPrestamoDao {

    // Registrar un préstamo
    @Override
    public boolean guardar(Prestamo prestamo) {

        String sql = "INSERT INTO prestamos "
                + "(cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, "
                + "fecha_vencimiento, estado, total) "
                + "VALUES ("
                + prestamo.getcliente().getid() + ", "
                + prestamo.getempleado().getid() + ", "
                + prestamo.getmonto() + ", "
                + prestamo.getinteres() + ", "
                + prestamo.getcuotas() + ", '"
                + prestamo.getfechaInicio() + "', '"
                + prestamo.getfechaVencimiento() + "', '"
                + prestamo.getestado() + "', "
                + prestamo.gettotal() + ")";

        try (Connection con = Conexion.conectar();
             java.sql.Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar préstamo: " + e.getMessage());
            return false;
        }
    }

    // Consultar préstamos con sus clientes y empleados
    @Override
    public List<Prestamo> listar() {

        String sql = "SELECT p.id AS prestamo_id, p.monto, p.interes, "
                + "p.cuotas, p.fecha_inicio, p.fecha_vencimiento, "
                + "p.estado, p.total, "
                + "c.id AS cliente_id, c.nombre AS cliente_nombre, "
                + "c.documento AS cliente_documento, c.correo AS cliente_correo, "
                + "c.telefono AS cliente_telefono, "
                + "e.id AS empleado_id, e.nombre AS empleado_nombre, "
                + "e.documento AS empleado_documento, e.correo AS empleado_correo, "
                + "e.rol AS empleado_rol, e.salario AS empleado_salario "
                + "FROM prestamos p "
                + "INNER JOIN clientes c ON p.cliente_id = c.id "
                + "INNER JOIN empleados e ON p.empleado_id = e.id";

        List<Prestamo> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             java.sql.Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("cliente_nombre"),
                        rs.getString("cliente_documento"),
                        rs.getString("cliente_correo"),
                        rs.getString("cliente_telefono")
                );

                Empleado empleado = new Empleado(
                        rs.getInt("empleado_id"),
                        rs.getString("empleado_nombre"),
                        rs.getString("empleado_documento"),
                        rs.getString("empleado_correo"),
                        rs.getString("empleado_rol"),
                        rs.getDouble("empleado_salario")
                );

                Prestamo prestamo = new Prestamo(
                        rs.getInt("prestamo_id"),
                        cliente,
                        empleado,
                        rs.getDouble("monto"),
                        rs.getDouble("interes"),
                        rs.getInt("cuotas"),
                        rs.getString("estado"),
                        rs.getDate("fecha_inicio").toLocalDate()
                );

                prestamo.settotal(rs.getDouble("total"));
                prestamo.setfechaVencimiento(
                        rs.getDate("fecha_vencimiento").toLocalDate()
                );

                lista.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar préstamos: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar el estado de un préstamo
    public boolean actualizarEstado(int id, String estado) {

        String sql = "UPDATE prestamos SET estado = '"
                + estado + "' WHERE id = " + id;

        try (Connection con = Conexion.conectar();
             java.sql.Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar préstamo: " + e.getMessage());
            return false;
        }
    }
}


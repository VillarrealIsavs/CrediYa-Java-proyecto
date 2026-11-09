
package dao.implement;

import dao.interfaces.IPagoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Pago;
import model.Prestamo;

/*
    Función: guardar y consultar los pagos realizados en CrediYa.
 */
public class pagoDAO implements IPagoDao {

    // Registrar un pago en la base de datos
    @Override
    public boolean guardar(Pago pago) {

        String sql = "INSERT INTO pagos "
                + "(prestamo_id, monto, fecha) "
                + "VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pago.getprestamo().getid());
            ps.setDouble(2, pago.getmonto());
            ps.setDate(3, Date.valueOf(pago.getfecha()));

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar pago: "
                    + e.getMessage());
            return false;
        }
    }

    // Consultar los pagos de un préstamo específico
    @Override
    public List<Pago> listar(Prestamo prestamo) {

        String sql = "SELECT * FROM pagos WHERE prestamo_id = ?";

        List<Pago> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, prestamo.getid());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Pago pago = new Pago(
                            rs.getInt("id"),
                            prestamo,
                            rs.getDouble("monto"),
                            rs.getDate("fecha").toLocalDate()
                    );

                    lista.add(pago);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar pagos: "
                    + e.getMessage());
        }

        return lista;
    }
}

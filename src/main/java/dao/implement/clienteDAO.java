
package dao.implement;

import dao.interfaces.IClienteDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/*
    Función: guardar y consultar clientes en la base de datos CrediYa.
 */
public class clienteDAO implements IClienteDao {

    // Registrar un cliente en la base de datos
    @Override
    public boolean guardar(Cliente cliente) {

        String sql = "INSERT INTO clientes "
                + "(nombre, documento, correo, telefono) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getnombre());
            ps.setString(2, cliente.getdocumento());
            ps.setString(3, cliente.getcorreo());
            ps.setString(4, cliente.gettelefono());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: "
                    + e.getMessage());
            return false;
        }
    }

    // Consultar todos los clientes registrados
    @Override
    public List<Cliente> listar() {

        String sql = "SELECT * FROM clientes";

        List<Cliente> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("documento"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar clientes: "
                    + e.getMessage());
        }

        return lista;
    }
}


package controller;

import dao.implement.clienteDAO;
import datos.Archivos;
import java.util.List;
import model.Cliente;

/*
    Función: gestionar el registro y consulta de clientes.
 */
public class GestionarCliente {

    private final clienteDAO clienteDao = new clienteDAO();
    private final Archivos archivo = new Archivos();

    // Registrar cliente en MySQL y TXT
    public boolean registrarCliente(Cliente cliente) {

        boolean guardado = clienteDao.guardar(cliente);

        if (guardado) {
            String datos = cliente.getid() + ","
                    + cliente.getnombre() + ","
                    + cliente.getdocumento() + ","
                    + cliente.getcorreo() + ","
                    + cliente.gettelefono();

            archivo.guardar("clientes.txt", datos);
        }

        return guardado;
    }

    // Consultar clientes registrados
    public List<Cliente> listarClientes() {
        return clienteDao.listar();
    }
}
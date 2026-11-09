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

    public boolean registrarCliente(Cliente cliente) {
        boolean guardado = clienteDao.guardar(cliente);

        if (guardado) {
            guardarClienteTXT(cliente);
        }

        return guardado;
    }

    public List<Cliente> listarClientes() {
        return clienteDao.listar();
    }

    public void guardarClienteTXT(Cliente cliente) {
        String datos = cliente.getid() + ","
                + cliente.getnombre() + ","
                + cliente.getdocumento() + ","
                + cliente.getcorreo() + ","
                + cliente.gettelefono();

        archivo.guardar("clientes.txt", datos);
    }
}
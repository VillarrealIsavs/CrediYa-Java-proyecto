

package controller;

import dao.implement.clienteDAO;
import datos.Archivos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    // Consultar clientes desde MySQL y TXT
    public List<Cliente> listarClientes() {

        List<Cliente> lista = clienteDao.listar();
        Set<Integer> ids = new HashSet<>();

        for (Cliente cliente : lista) {
            ids.add(cliente.getid());
        }

        List<String> registros = archivo.leer("clientes.txt");

        for (String registro : registros) {

            String[] datos = registro.split(",", -1);

            if (datos.length == 5) {
                try {
                    int id = Integer.parseInt(datos[0]);

                    if (id > 0 && !ids.contains(id)) {

                        Cliente cliente = new Cliente(
                                id,
                                datos[1],
                                datos[2],
                                datos[3],
                                datos[4]
                        );

                        lista.add(cliente);
                        ids.add(id);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Registro de cliente inválido.");
                }
            }
        }

        return lista;
    }
}

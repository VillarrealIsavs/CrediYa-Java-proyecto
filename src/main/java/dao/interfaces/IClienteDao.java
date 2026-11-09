
package dao.interfaces;

import java.util.List;
import model.Cliente;

/*
    Función: establecer las operacionesm disponibles para los clientes.
 */
public interface IClienteDao{
    boolean guardar(Cliente cliente);
    List<Cliente> listar();
}

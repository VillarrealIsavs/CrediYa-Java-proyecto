
package dao.interfaces;

import java.util.List;
import model.Pago;
import model.Prestamo;

/*
    Función: establecer las operaciones disponibles para los pagos.
 */
public interface IPagoDao{
    boolean guardar(Pago pago);
    List<Pago> listar(Prestamo prestamo);
}


package dao.interfaces;

import java.util.List;
import model.Prestamo;

/*
    Función: establecer las operaciones disponibles para los préstamos.
 */
public interface IPrestamoDao{
    boolean guardar(Prestamo prestamo);
    List<Prestamo> listar();
}

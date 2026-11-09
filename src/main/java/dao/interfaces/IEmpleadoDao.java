
package dao.interfaces;

import java.util.List;
import model.Empleado;

/*
    Función: establecer las operacionesdisponibles para los empleados.
 */ 
public interface IEmpleadoDao{
    boolean guardar(Empleado empleado);
    List<Empleado> listar();
}

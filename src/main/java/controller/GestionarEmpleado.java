
package controller;

import dao.implement.empleadoDAO;
import datos.Archivos;
import java.util.List;
import model.Empleado;

/*
 * Función: gestionar el registro y consulta de empleados.
 */
public class GestionarEmpleado {

    private final empleadoDAO empleadoDao = new empleadoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar empleado en MySQL y TXT
    public boolean registrarEmpleado(Empleado empleado) {

        boolean guardado = empleadoDao.guardar(empleado);

        if (guardado) {
            String datos = empleado.getid() + ","
                    + empleado.getnombre() + ","
                    + empleado.getdocumento() + ","
                    + empleado.getcorreo() + ","
                    + empleado.getrol() + ","
                    + empleado.getsalario();

            archivo.guardar("empleados.txt", datos);
        }

        return guardado;
    }

    // Consultar empleados registrados
    public List<Empleado> listarEmpleados() {
        return empleadoDao.listar();
    }
}
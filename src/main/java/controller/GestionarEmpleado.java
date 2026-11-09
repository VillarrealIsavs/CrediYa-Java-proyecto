
package controller;

import dao.implement.empleadoDAO;
import datos.Archivos;
import java.util.List;
import model.Empleado;

/*
    Función: gestionar el registro y consulta de los empleados de CrediYa.
 */
public class GestionarEmpleado {

    // Crear los objetos necesarios
    private final empleadoDAO empleadoDao = new empleadoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar un empleado en MySQL
    public boolean registrarEmpleado(Empleado empleado) {
        return empleadoDao.guardar(empleado);
    }

    // Consultar los empleados registrados
    public List<Empleado> listarEmpleados() {
        return empleadoDao.listar();
    }

    // Guardar un empleado en un archivo TXT
    public void guardarEmpleadoTXT(Empleado empleado) {

        String datos = empleado.getid() + ","
                + empleado.getnombre() + ","
                + empleado.getdocumento() + ","
                + empleado.getcorreo() + ","
                + empleado.getrol() + ","
                + empleado.getsalario();

        archivo.guardar("empleados.txt", datos);
    }
}

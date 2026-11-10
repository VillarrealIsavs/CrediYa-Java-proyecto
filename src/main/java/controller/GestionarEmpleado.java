
package controller;

import dao.implement.empleadoDAO;
import datos.Archivos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Empleado;

/*
    Función: gestionar el registro y consulta de empleados.
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

    // Consultar empleados desde MySQL y TXT
    public List<Empleado> listarEmpleados() {

        List<Empleado> lista = empleadoDao.listar();
        Set<Integer> ids = new HashSet<>();

        for (Empleado empleado : lista) {
            ids.add(empleado.getid());
        }

        List<String> registros = archivo.leer("empleados.txt");
        for (String registro : registros) {
            String[] datos = registro.split(",", -1);
            if (datos.length == 6) {
                try {
                    int id = Integer.parseInt(datos[0]);
                    if (id > 0 && !ids.contains(id)) {

                        Empleado empleado = new Empleado(
                                id,
                                datos[1],
                                datos[2],
                                datos[3],
                                datos[4],
                                Double.parseDouble(datos[5])
                        );
                        lista.add(empleado);
                        ids.add(id);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Registro de empleado inválido.");
                }
            }
        }

        return lista;
    }
}

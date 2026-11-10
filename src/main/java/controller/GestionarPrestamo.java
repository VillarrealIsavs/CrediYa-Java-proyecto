

package controller;

import dao.implement.prestamoDAO;
import datos.Archivos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Prestamo;

/*
  Función: gestionar el registro y consulta de préstamos.
 */
public class GestionarPrestamo {

    private final prestamoDAO prestamoDao = new prestamoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar préstamo en MySQL y TXT
    public boolean registrarPrestamo(Prestamo prestamo) {

        boolean guardado = prestamoDao.guardar(prestamo);

        if (guardado) {
            String datos = prestamo.getid() + ","
                    + prestamo.getcliente().getid() + ","
                    + prestamo.getempleado().getid() + ","
                    + prestamo.getmonto() + ","
                    + prestamo.getinteres() + ","
                    + prestamo.getcuotas() + ","
                    + prestamo.getestado() + ","
                    + prestamo.getfechaInicio();

            archivo.guardar("prestamos.txt", datos);
        }

        return guardado;
    }

    // Consultar préstamos registrados
    public List<Prestamo> listarPrestamos() {

        List<Prestamo> prestamos = prestamoDao.listar();
        List<String> registros = archivo.leer("prestamos.txt");

        if (registros.isEmpty()) {
            return prestamos;
        }

        Set<Integer> ids = new HashSet<>();

        for (String registro : registros) {
            String[] datos = registro.split(",");

            if (datos.length == 8) {
                try {
                    ids.add(Integer.parseInt(datos[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Registro de préstamo inválido.");
                }
            }
        }

        List<Prestamo> lista = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            if (ids.contains(prestamo.getid())) {
                lista.add(prestamo);
            }
        }

        return lista.isEmpty() ? prestamos : lista;
    }
}

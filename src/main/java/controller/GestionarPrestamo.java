
package controller;

import dao.implement.prestamoDAO;
import datos.Archivos;
import java.util.List;
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
        return prestamoDao.listar();
    }
}
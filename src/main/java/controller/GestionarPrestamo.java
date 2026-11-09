
package controller;

import dao.implement.prestamoDAO;
import datos.Archivos;
import java.util.List;
import model.Prestamo;

/*
    Función: gestionar el registro y consulta de los préstamos de CrediYa.
 */
public class GestionarPrestamo {

    // Crear los objetos necesarios
    private final prestamoDAO prestamoDao = new prestamoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar un préstamo en MySQL
    public boolean registrarPrestamo(Prestamo prestamo) {
        return prestamoDao.guardar(prestamo);
    }

    // Consultar los préstamos registrados
    public List<Prestamo> listarPrestamos() {
        return prestamoDao.listar();
    }

    // Guardar un préstamo en un archivo TXT
    public void guardarPrestamoTXT(Prestamo prestamo) {

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
}

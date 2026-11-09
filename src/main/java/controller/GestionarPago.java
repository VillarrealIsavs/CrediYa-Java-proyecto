
package controller;

import dao.implement.pagoDAO;
import datos.Archivos;
import java.util.List;
import model.Pago;
import model.Prestamo;

/**
 * Función: gestionar el registro y consulta
 * de los pagos realizados en CrediYa.
 */
public class GestionarPago {

    // Crear los objetos necesarios
    private final pagoDAO pagoDao = new pagoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar un pago en MySQL
    public boolean registrarPago(Pago pago) {
        return pagoDao.guardar(pago);
    }

    // Consultar los pagos de un préstamo
    public List<Pago> listarPagos(Prestamo prestamo) {
        return pagoDao.listar(prestamo);
    }

    // Guardar un pago en un archivo TXT
    public void guardarPagoTXT(Pago pago) {

        String datos = pago.getid() + ","
                + pago.getprestamo().getid() + ","
                + pago.getmonto() + ","
                + pago.getfecha();

        archivo.guardar("pagos.txt", datos);
    }
}

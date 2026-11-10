
package controller;

import dao.implement.pagoDAO;
import datos.Archivos;
import java.util.List;
import model.Pago;
import model.Prestamo;

/*
 * Función: gestionar el registro y consulta de pagos.
 */
public class GestionarPago{
    private final pagoDAO pagoDao = new pagoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar pago en MySQL y TXT
    public boolean registrarPago(Pago pago) {

        boolean guardado = pagoDao.guardar(pago);

        if (guardado) {
            String datos = pago.getid() + ","
                    + pago.getprestamo().getid() + ","
                    + pago.getmonto() + ","
                    + pago.getfecha();

            archivo.guardar("pagos.txt", datos);
        }

        return guardado;
    }

    // Consultar pagos de un préstamo
    public List<Pago> listarPagos(Prestamo prestamo) {
        return pagoDao.listar(prestamo);
    }
}
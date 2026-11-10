

package controller;

import dao.implement.pagoDAO;
import dao.implement.prestamoDAO;
import datos.Archivos;
import java.util.List;
import model.Pago;
import model.Prestamo;

/*
 * Función: gestionar los pagos y el saldo de los préstamos.
 */
public class GestionarPago {

    private final pagoDAO pagoDao = new pagoDAO();
    private final prestamoDAO prestamoDao = new prestamoDAO();
    private final Archivos archivo = new Archivos();

    // Registrar un pago
    public boolean registrarPago(Pago pago) {

        Prestamo prestamo = pago.getprestamo();
        double saldo = calcularSaldo(prestamo);

        if (pago.getmonto() <= 0 || pago.getmonto() > saldo) {
            System.out.println("El monto no es válido. Saldo pendiente: $" + saldo);
            return false;
        }

        boolean guardado = pagoDao.guardar(pago);

        if (guardado) {

            String datos = pago.getid() + ","
                    + prestamo.getid() + ","
                    + pago.getmonto() + ","
                    + pago.getfecha();

            archivo.guardar("pagos.txt", datos);

            double nuevoSaldo = saldo - pago.getmonto();

            if (nuevoSaldo <= 0.001) {
                prestamoDao.actualizarEstado(prestamo.getid(), "pagado");
                System.out.println("El préstamo ha sido pagado completamente.");
            } else {
                System.out.println("Saldo pendiente: $" + nuevoSaldo);
            }
        }

        return guardado;
    }

    // Consultar los pagos de un préstamo
    public List<Pago> listarPagos(Prestamo prestamo) {
        return pagoDao.listar(prestamo);
    }

    // Calcular el saldo pendiente
    public double calcularSaldo(Prestamo prestamo) {

        double pagado = 0;

        List<Pago> pagos = listarPagos(prestamo);

        for (Pago pago : pagos) {
            pagado += pago.getmonto();
        }

        return prestamo.gettotal() - pagado;
    }
}

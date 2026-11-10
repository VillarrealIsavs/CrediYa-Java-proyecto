
package controller;

import dao.implement.pagoDAO;
import dao.implement.prestamoDAO;
import datos.Archivos;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        List<Pago> pagos = pagoDao.listar(prestamo);
        List<String> registros = archivo.leer("pagos.txt");

        Set<Integer> ids = new HashSet<>();

        for (String registro : registros) {

            String[] datos = registro.split(",");

            if (datos.length == 4) {
                try {
                    int id = Integer.parseInt(datos[0]);
                    int prestamoId = Integer.parseInt(datos[1]);

                    if (prestamoId == prestamo.getid()) {
                        ids.add(id);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Registro de pago inválido.");
                }
            }
        }

        pagos.removeIf(pago -> !ids.isEmpty() && !ids.contains(pago.getid()));

        return pagos;
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

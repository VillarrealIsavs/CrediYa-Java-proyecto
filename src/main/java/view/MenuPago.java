
package view;

import controller.GestionarPago;
import controller.GestionarPrestamo;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Pago;
import model.Prestamo;

/*
 * Función: mostrar el menú para registrar y consultar pagos.
 */
public class MenuPago {

    private final Scanner entrada;
    private final GestionarPago gestionarPago = new GestionarPago();
    private final GestionarPrestamo gestionarPrestamo = new GestionarPrestamo();

    public MenuPago(Scanner entrada) {
        this.entrada = entrada;
    }

    // Mostrar el menú de pagos
    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== PAGOS =====");
            System.out.println("1. Registrar pago");
            System.out.println("2. Consultar pagos");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                case 1 -> registrarPago();
                case 2 -> consultarPagos();
                case 3 -> System.out.println("Regresando...");
                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 3);
    }

    // Registrar un pago
    private void registrarPago() {

        List<Prestamo> prestamos = gestionarPrestamo.listarPrestamos();

        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos registrados.");
            return;
        }

        System.out.print("ID del prestamo: ");
        int id = Integer.parseInt(entrada.nextLine());

        for (Prestamo p : prestamos) {

            if (p.getid() == id) {

                double saldo = gestionarPago.calcularSaldo(p);

                if (saldo <= 0) {
                    System.out.println("Este prestamo ya esta pagado.");
                    return;
                }

                System.out.println("Cliente: " + p.getcliente().getnombre());
                System.out.println("Total del prestamo: $" + p.gettotal());
                System.out.println("Saldo pendiente: $" + saldo);

                System.out.print("Monto del pago: ");
                double monto = Double.parseDouble(entrada.nextLine());

                Pago pago = new Pago(0, p, monto, LocalDate.now());

                if (gestionarPago.registrarPago(pago)) {
                    System.out.println("Pago registrado correctamente.");
                }

                return;
            }
        }

        System.out.println("Prestamo no encontrado.");
    }

    // Consultar el historial de pagos
    private void consultarPagos() {

        System.out.print("ID del prestamo: ");
        int id = Integer.parseInt(entrada.nextLine());

        List<Prestamo> prestamos = gestionarPrestamo.listarPrestamos();

        for (Prestamo p : prestamos) {

            if (p.getid() == id) {

                List<Pago> pagos = gestionarPago.listarPagos(p);

                System.out.println("\n===== HISTORIAL DE PAGOS =====");

                if (pagos.isEmpty()) {
                    System.out.println("No existen pagos registrados.");
                } else {
                    for (Pago pago : pagos) {
                        System.out.println("Monto: $" + pago.getmonto()
                                + " | Fecha: " + pago.getfecha());
                    }
                }

                System.out.println("Total del prestamo: $" + p.gettotal());
                System.out.println("Saldo pendiente: $"
                        + gestionarPago.calcularSaldo(p));
                System.out.println("Estado: " + p.getestado());

                return;
            }
        }

        System.out.println("Prestamo no encontrado.");
    }
}

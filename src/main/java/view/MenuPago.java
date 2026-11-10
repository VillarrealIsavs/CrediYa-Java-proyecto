package view;

import controller.GestionarPago;
import controller.GestionarPrestamo;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Pago;
import model.Prestamo;

/*
 * Función: gestionar el registro y consulta de pagos.
 */
public class MenuPago {

    private final Scanner entrada;
    private final GestionarPago gestionarPago = new GestionarPago();
    private final GestionarPrestamo gestionarPrestamo = new GestionarPrestamo();

    public MenuPago(Scanner entrada) {
        this.entrada = entrada;
    }

    public void mostrarMenu() {
        System.out.println("\n===== PAGOS =====");
        System.out.println("1. Registrar pago");
        System.out.println("2. Consultar pagos");
        System.out.println("3. Volver");
        System.out.print("Seleccione: ");

        int opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {
            case 1 -> registrarPago();
            case 2 -> consultarPagos();
            case 3 -> { }
            default -> System.out.println("Opcion no valida.");
        }
    }

    private void registrarPago() {
        List<Prestamo> prestamos = gestionarPrestamo.listarPrestamos();

        System.out.print("ID del prestamo: ");
        int id = Integer.parseInt(entrada.nextLine());

        for (Prestamo p : prestamos) {
            if (p.getid() == id) {
                System.out.print("Monto del pago: ");
                double monto = Double.parseDouble(entrada.nextLine());

                Pago pago = new Pago(0, p, monto, LocalDate.now());

                System.out.println(gestionarPago.registrarPago(pago)
                        ? "Pago registrado."
                        : "Error al registrar.");
                return;
            }
        }

        System.out.println("Prestamo no encontrado");
    }

    private void consultarPagos() {
        System.out.print("id del prestamo: ");
        int id = Integer.parseInt(entrada.nextLine());

        for (Prestamo p : gestionarPrestamo.listarPrestamos()) {
            if (p.getid() == id) {
                List<Pago> pagos = gestionarPago.listarPagos(p);

                for (Pago pago : pagos) {
                    System.out.println("Monto: " + pago.getmonto()
                            + " - Fecha: " + pago.getfecha());
                }
                return;
            }
        }

        System.out.println("Prestamo no encontrado");
    }
}
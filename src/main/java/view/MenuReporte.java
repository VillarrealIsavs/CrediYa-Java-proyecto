
package view;

import controller.GestionarPrestamo;
import controller.reportes;
import java.util.List;
import java.util.Scanner;
import model.Prestamo;

public class MenuReporte{
    private final Scanner entrada;
    private final GestionarPrestamo gestionarPrestamo = new GestionarPrestamo();
    private final reportes reporte = new reportes();

    public MenuReporte(Scanner entrada) {
        this.entrada = entrada;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== REPORTES =====");
            System.out.println("1. Prestamos pendientes");
            System.out.println("2. Prestamos cancelados");
            System.out.println("3. Prestamos entregados");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");

            opcion = Integer.parseInt(entrada.nextLine());

            List<Prestamo> lista = gestionarPrestamo.listarPrestamos();

            switch (opcion) {
                case 1 -> lista = reporte.prestamosActivos(lista);
                case 2 -> lista = reporte.prestamosCancelados(lista);
                case 3 -> lista = reporte.prestamosEntregados(lista);
                case 4 -> {
                    continue;
                }
                default -> {
                    System.out.println("Opcion no valida.");
                    continue;
                }
            }

            if (lista.isEmpty()) {
                System.out.println("No hay registros.");
            }

            for (Prestamo p : lista) {
                System.out.println("ID: " + p.getid());
                System.out.println("Cliente: " + p.getcliente().getnombre());
                System.out.println("Monto: " + p.getmonto());
                System.out.println("Estado: " + p.getestado());
            }

        } while (opcion != 4);
    }
}
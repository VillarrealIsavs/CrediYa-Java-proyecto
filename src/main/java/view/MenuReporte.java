
package view;

import controller.GestionarPrestamo;
import controller.reportes;
import java.util.List;
import java.util.Scanner;
import model.Prestamo;

/*
 * Función: mostrar los reportes de los préstamos.
 */
public class MenuReporte {

    private final Scanner entrada;
    private final GestionarPrestamo gestionarPrestamo = new GestionarPrestamo();
    private final reportes reporte = new reportes();

    public MenuReporte(Scanner entrada) {
        this.entrada = entrada;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== Examen Gestion Prestamos =====");
            System.out.println("1. Prestamos pendientes");
            System.out.println("2. Prestamos cancelados");
            System.out.println("3. Prestamos entregados");
            System.out.println("4. Prestamos pagados");
            System.out.println("5. Prestamos vencidos");
            System.out.println("6. Clientes morosos");
            System.out.println("7. Volver");

            try {
                System.out.print("Seleccione: ");
                opcion = Integer.parseInt(entrada.nextLine());

                if (opcion == 7) {
                    continue;
                }

                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opcion no valida.");
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opcion numerica valida.");
                opcion = 0;
                continue;
            }

            List<Prestamo> lista = gestionarPrestamo.listarPrestamos();

            switch (opcion) {
                case 1 -> lista = reporte.prestamosActivos(lista);
                case 2 -> lista = reporte.prestamosCancelados(lista);
                case 3 -> lista = reporte.prestamosEntregados(lista);
                case 4 -> lista = reporte.prestamosPagados(lista);
                case 5 -> lista = reporte.prestamosVencidos(lista);
                case 6 -> lista = reporte.clientesMorosos(lista);
            }

            if (lista.isEmpty()) {
                System.out.println("No hay registros.");
            } else {
                for (Prestamo p : lista) {
                    System.out.println("\nID del prestamo: " + p.getid());
                    System.out.println("Cliente: " + p.getcliente().getnombre());
                    System.out.println("Documento: " + p.getcliente().getdocumento());
                    System.out.println("Monto: $" + p.getmonto());
                    System.out.println("Total: $" + p.gettotal());
                    System.out.println("Vencimiento: " + p.getfechaVencimiento());
                    System.out.println("Estado: " + p.getestado());
                }
            }

        } while (opcion != 7);
    }
}

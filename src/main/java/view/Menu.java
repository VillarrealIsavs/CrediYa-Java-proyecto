
package view;

import java.util.Scanner;
import view.MenuPago;
/*
    Función: mostrar el menú principal del sistema CrediYa.
 */
public class Menu{
    private final Scanner entrada = new Scanner(System.in);
    private final MenuPersona menuPersona = new MenuPersona(entrada);
    private final MenuPrestamo menuPrestamo = new MenuPrestamo(entrada);
    private final MenuPago menuPago = new MenuPago(entrada);
    private final MenuReporte menuReporte = new MenuReporte(entrada);

    // Mostrar las opciones principales
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== CREDIYA =====");
            System.out.println("1. Gestionar empleados y clientes");
            System.out.println("2. Gestionar prestamos");
            System.out.println("3. Gestionar pagos");
            System.out.println("4. Consultar reportes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                case 1 -> menuPersona.mostrarMenu();
                case 2 -> menuPrestamo.mostrarMenu();
                case 3 -> menuPago.mostrarMenu();
                case 4 -> menuReporte.mostrarMenu();
                case 5 -> System.out.println("Gracias por usar CrediYa.");
                default -> System.out.println("Opcion no encontrada.");
            }
        } while (opcion != 5);
    }
}


package view;

import java.util.Scanner;

/*
 * Función: mostrar el menú principal del sistema CrediYa.
 */
public class Menu {

    private final Scanner entrada = new Scanner(System.in);
    private final MenuPersona menuPersona = new MenuPersona(entrada);
    private final MenuPrestamo menuPrestamo = new MenuPrestamo(entrada);
    private final MenuPago menuPago = new MenuPago(entrada);
    private final MenuReporte menuReporte = new MenuReporte(entrada);

    // Mostrar el menú principal
    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n+------------------------------------------+");
            System.out.println("|                 CREDIYA                  |");
            System.out.println("|          SISTEMA DE PRESTAMOS             |");
            System.out.println("+------------------------------------------+");
            System.out.println("|                                          |");
            System.out.println("|  1. Gestionar empleados y clientes       |");
            System.out.println("|  2. Gestionar prestamos                  |");
            System.out.println("|  3. Gestionar pagos                      |");
            System.out.println("|  4. Examen Gestion Prestamos             |");
            System.out.println("|  5. Salir                                |");
            System.out.println("|                                          |");
            System.out.println("+------------------------------------------+");

            try {
                System.out.print("\n  >> Seleccione una opcion: ");
                opcion = Integer.parseInt(entrada.nextLine());

                switch (opcion) {
                    case 1 -> menuPersona.mostrarMenu();
                    case 2 -> menuPrestamo.mostrarMenu();
                    case 3 -> menuPago.mostrarMenu();
                    case 4 -> menuReporte.mostrarMenu();
                    case 5 -> System.out.println("\nGracias por usar CrediYa.");
                    default -> System.out.println("\n[!] Opcion no encontrada.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\n[!] Ingrese una opcion numerica valida.");
                opcion = 0;
            }

        } while (opcion != 5);
    }
}

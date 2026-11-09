
package view;

import java.util.Scanner;

/**
 * Función: mostrar el menú principal del sistema CrediYa
 * y permitir al usuario seleccionar una opción.
 */
public class Menu{

    private final Scanner entrada = new Scanner(System.in);

    // Mostrar las opciones principales del sistema
    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== CREDIYA =====");
            System.out.println("1. Gestionar empleados");
            System.out.println("2. Gestionar clientes");
            System.out.println("3. Gestionar prestamos");
            System.out.println("4. Gestionar pagos");
            System.out.println("5. Consultar reportes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                entrada.next();
                System.out.print("Seleccione una opcion: ");
            }

            opcion = entrada.nextInt();

            switch (opcion) {
                case 1 -> System.out.println("Empleados.");
                case 2 -> System.out.println("Clientes.");
                case 3 -> System.out.println("Prestamos.");
                case 4 -> System.out.println("Pagos.");
                case 5 -> System.out.println("Reportes.");
                case 6 -> System.out.println("Gracias por usar CrediYa.");
                default -> System.out.println("Opcion no válida.");
            }

        } while (opcion != 6);
    }
}

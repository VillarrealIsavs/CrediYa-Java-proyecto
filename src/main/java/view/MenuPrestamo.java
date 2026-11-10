
package view;

import controller.GestionarPrestamo;
import controller.GestionarCliente;
import controller.GestionarEmpleado;
import controller.calculos;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Prestamo;
import model.Cliente;
import model.Empleado;
import strategy.estrategia;
import strategy.interessimple;
import strategy.interescompuesto;

/*
 * Función: gestionar el registro y consulta de préstamos.
 */
public class MenuPrestamo {

    private final Scanner entrada;
    private final GestionarPrestamo gestionarPrestamo = new GestionarPrestamo();
    private final GestionarCliente gestionarCliente = new GestionarCliente();
    private final GestionarEmpleado gestionarEmpleado = new GestionarEmpleado();
    private final calculos calculadora = new calculos();

    public MenuPrestamo(Scanner entrada) {
        this.entrada = entrada;
    }

    // Leer una opción válida del menú
    private int leerOpcion(String mensaje, int minimo, int maximo) {
        while (true) {
            try {
                System.out.print(mensaje);
                int opcion = Integer.parseInt(entrada.nextLine());

                if (opcion >= minimo && opcion <= maximo) {
                    return opcion;
                }

                System.out.println("Seleccione una opción entre "
                        + minimo + " y " + maximo + ".");

            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción numérica válida.");
            }
        }
    }

    // Leer un número entero positivo
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = Integer.parseInt(entrada.nextLine());

                if (numero > 0) {
                    return numero;
                }

                System.out.println("Ingrese un número mayor que cero.");

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    // Leer un monto positivo
    private double leerNumero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double numero = Double.parseDouble(
                        entrada.nextLine().replace(",", ".")
                );

                if (Double.isFinite(numero) && numero > 0) {
                    return numero;
                }

                System.out.println("Ingrese un número mayor que cero.");

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    // Leer un interés igual o mayor que cero
    private double leerInteres(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double interes = Double.parseDouble(
                        entrada.nextLine().replace(",", ".")
                );

                if (Double.isFinite(interes) && interes >= 0) {
                    return interes;
                }

                System.out.println("El interés no puede ser negativo.");

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un interés válido.");
            }
        }
    }

    // Mostrar el menú de préstamos
    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== PRESTAMOS =====");
            System.out.println("1. Registrar prestamo");
            System.out.println("2. Consultar prestamos");
            System.out.println("3. Volver");

            opcion = leerOpcion("Seleccione: ", 1, 3);

            switch (opcion) {
                case 1 -> registrarPrestamo();
                case 2 -> consultarPrestamos();
                case 3 -> System.out.println("Volviendo...");
            }

        } while (opcion != 3);
    }

    // Registrar un préstamo
    private void registrarPrestamo() {
        List<Cliente> clientes = gestionarCliente.listarClientes();
        List<Empleado> empleados = gestionarEmpleado.listarEmpleados();

        if (clientes.isEmpty() || empleados.isEmpty()) {
            System.out.println("Registre clientes y empleados primero.");
            return;
        }

        System.out.println("\n===== CLIENTES =====");

        for (Cliente c : clientes) {
            System.out.println(c.getid() + ". " + c.getnombre());
        }

        Cliente cliente = null;

        while (cliente == null) {
            int idCliente = leerEntero("ID del cliente: ");

            for (Cliente c : clientes) {
                if (c.getid() == idCliente) {
                    cliente = c;
                    break;
                }
            }

            if (cliente == null) {
                System.out.println("Cliente no encontrado. Intente nuevamente.");
            }
        }

        System.out.println("\n===== EMPLEADOS =====");

        for (Empleado e : empleados) {
            System.out.println(e.getid() + ". " + e.getnombre());
        }

        Empleado empleado = null;

        while (empleado == null) {
            int idEmpleado = leerEntero("ID del empleado: ");

            for (Empleado e : empleados) {
                if (e.getid() == idEmpleado) {
                    empleado = e;
                    break;
                }
            }

            if (empleado == null) {
                System.out.println("Empleado no encontrado. Intente nuevamente.");
            }
        }

        double monto = leerNumero("Monto: ");
        double interes = leerInteres("Interes (%): ");
        int cuotas = leerEntero("Numero de cuotas: ");

        System.out.println("\n===== TIPO DE INTERES =====");
        System.out.println("1. Interes simple");
        System.out.println("2. Interes compuesto");

        int tipo = leerOpcion("Seleccione: ", 1, 2);

        estrategia metodo;

        switch (tipo) {
            case 1 -> metodo = new interessimple();
            case 2 -> metodo = new interescompuesto();
            default -> {
                System.out.println("Opcion no valida.");
                return;
            }
        }

        double total = calculadora.calcularTotal(
                monto, interes, cuotas, metodo
        );

        double valorCuota = calculadora.calcularCuota(
                monto, interes, cuotas, metodo
        );

        Prestamo prestamo = new Prestamo(
                0, cliente, empleado, monto,
                interes, cuotas, "pendiente", LocalDate.now()
        );

        prestamo.settotal(total);

        System.out.println("\nTotal a pagar: " + total);
        System.out.println("Valor de cada cuota: " + valorCuota);

        if (gestionarPrestamo.registrarPrestamo(prestamo)) {
            System.out.println("Prestamo registrado.");
        } else {
            System.out.println("Error al registrar.");
        }
    }

    // Consultar los préstamos registrados
    private void consultarPrestamos() {
        List<Prestamo> lista = gestionarPrestamo.listarPrestamos();

        if (lista.isEmpty()) {
            System.out.println("No hay prestamos registrados.");
            return;
        }

        for (Prestamo p : lista) {
            System.out.println("\nID: " + p.getid());
            System.out.println("Cliente: " + p.getcliente().getnombre());
            System.out.println("Empleado: " + p.getempleado().getnombre());
            System.out.println("Monto: " + p.getmonto());
            System.out.println("Interes: " + p.getinteres());
            System.out.println("Cuotas: " + p.getcuotas());
            System.out.println("Estado: " + p.getestado());
            System.out.println("Fecha: " + p.getfechaInicio());
        }
    }
}


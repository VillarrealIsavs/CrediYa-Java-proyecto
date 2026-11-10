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

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== PRESTAMOS =====");
            System.out.println("1. Registrar prestamo");
            System.out.println("2. Consultar prestamos");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                case 1 -> registrarPrestamo();
                case 2 -> consultarPrestamos();
                case 3 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 3);
    }

    private void registrarPrestamo() {
        List<Cliente> clientes = gestionarCliente.listarClientes();
        List<Empleado> empleados = gestionarEmpleado.listarEmpleados();

        if (clientes.isEmpty() || empleados.isEmpty()) {
            System.out.println("Registre clientes y empleados primero.");
            return;
        }

        for (Cliente c : clientes) {
            System.out.println(c.getid() + ". " + c.getnombre());
        }

        System.out.print("ID del cliente: ");
        int idCliente = Integer.parseInt(entrada.nextLine());
        Cliente cliente = null;

        for (Cliente c : clientes) {
            if (c.getid() == idCliente) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        for (Empleado e : empleados) {
            System.out.println(e.getid() + ". " + e.getnombre());
        }

        System.out.print("ID del empleado: ");
        int idEmpleado = Integer.parseInt(entrada.nextLine());
        Empleado empleado = null;

        for (Empleado e : empleados) {
            if (e.getid() == idEmpleado) {
                empleado = e;
                break;
            }
        }

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Monto: ");
        double monto = Double.parseDouble(entrada.nextLine());

        System.out.print("Interes (%): ");
        double interes = Double.parseDouble(entrada.nextLine());

        System.out.print("Numero de cuotas: ");
        int cuotas = Integer.parseInt(entrada.nextLine());

        System.out.println("1. Interes simple");
        System.out.println("2. Interes compuesto");
        int tipo = Integer.parseInt(entrada.nextLine());

        estrategia metodo;

        switch (tipo) {
            case 1 -> metodo = new interessimple();
            case 2 -> metodo = new interescompuesto();
            default -> {
                System.out.println("Opcion no valida.");
                return;
            }
        }

        double total = calculadora.calcularTotal(monto, interes, cuotas, metodo);
        double valorCuota = calculadora.calcularCuota(monto, interes, cuotas, metodo);

        Prestamo prestamo = new Prestamo(0, cliente, empleado, monto,
        interes, cuotas, "pendiente", LocalDate.now());
        
        prestamo.settotal(total);

        System.out.println("Total a pagar: " + total);
        System.out.println("Valor de cada cuota: " + valorCuota);

        if (gestionarPrestamo.registrarPrestamo(prestamo)) {
            System.out.println("Prestamo registrado.");
        } else {
            System.out.println("Error al registrar.");
        }
    }

    private void consultarPrestamos() {
        List<Prestamo> lista = gestionarPrestamo.listarPrestamos();

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
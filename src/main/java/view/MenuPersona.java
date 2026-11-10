

package view;

import controller.GestionarEmpleado;
import controller.GestionarCliente;
import java.util.List;
import java.util.Scanner;
import model.Empleado;
import model.Cliente;

/*
    Función: gestionar el registro y consulta de empleados y clientes.
 */
public class MenuPersona{
    private final Scanner entrada;
    private final GestionarEmpleado gestionarEmpleado = new GestionarEmpleado();
    private final GestionarCliente gestionarCliente = new GestionarCliente();

    public MenuPersona(Scanner entrada){
        this.entrada = entrada;
    }

    // Leer un texto obligatorio
    private String leerTexto(String mensaje){
        String texto;
        do {
            System.out.print(mensaje);
            texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    // Leer un número positivo
    private double leerNumero(String mensaje){
        double numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = Double.parseDouble(entrada.nextLine());

                if (numero > 0) {
                    return numero;
                }
                System.out.println("Ingrese un número mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    // Mostrar las opciones de empleados y clientes
    public void mostrarMenu(){
        int opcion;
        do {
            System.out.println("\n===== PERSONAS =====");
            System.out.println("1. Gestionar empleados");
            System.out.println("2. Gestionar clientes");
            System.out.println("3. Volver");

            opcion = leerOpcion("Seleccione una opcion: ", 1, 3);

            switch (opcion) {
                case 1 -> menuEmpleados();
                case 2 -> menuClientes();
                case 3 -> System.out.println("Volviendo al menu principal.");
            }

        } while (opcion != 3);
    }

    // Gestionar empleados
    private void menuEmpleados(){
        int opcion;
        do {
            System.out.println("\n===== EMPLEADOS =====");
            System.out.println("1. Registrar");
            System.out.println("2. Consultar");
            System.out.println("3. Volver");

            opcion = leerOpcion("Seleccione una opcion: ", 1, 3);

            switch (opcion) {
                case 1 -> registrarEmpleado();
                case 2 -> consultarEmpleados();
                case 3 -> System.out.println("Volviendo.");
            }

        } while (opcion != 3);
    }

    // Registrar empleado
    private void registrarEmpleado(){
        System.out.println("\n===== REGISTRAR EMPLEADO =====");

        String nombre = leerTexto("Nombre: ");
        String documento = leerTexto("Documento: ");
        String correo = leerTexto("Correo: ");
        String rol = leerTexto("Rol: ");
        double salario = leerNumero("Salario: ");

        Empleado empleado = new Empleado(
                0, nombre, documento, correo, rol, salario
        );

        if (gestionarEmpleado.registrarEmpleado(empleado)) {
            System.out.println("Empleado registrado correctamente.");
        } else {
            System.out.println("Error al registrar empleado.");
        }
    }

    // Consultar empleados
    private void consultarEmpleados() {
        List<Empleado> lista = gestionarEmpleado.listarEmpleados();

        for (Empleado empleado : lista) {
            System.out.println("\nID: " + empleado.getid());
            System.out.println("Nombre: " + empleado.getnombre());
            System.out.println("Documento: " + empleado.getdocumento());
            System.out.println("Correo: " + empleado.getcorreo());
            System.out.println("Rol: " + empleado.getrol());
            System.out.println("Salario: " + empleado.getsalario());
        }

        if (lista.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        }
    }

    // Gestionar clientes
    private void menuClientes() {
        int opcion;

        do {
            System.out.println("\n===== CLIENTES =====");
            System.out.println("1. Registrar");
            System.out.println("2. Consultar");
            System.out.println("3. Volver");

            opcion = leerOpcion("Seleccione una opcion: ", 1, 3);

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> consultarClientes();
                case 3 -> System.out.println("Volviendo.");
            }

        } while (opcion != 3);
    }

    // Registrar cliente
    private void registrarCliente(){
        System.out.println("\n===== REGISTRAR CLIENTE =====");

        String nombre = leerTexto("Nombre: ");
        String documento = leerTexto("Documento: ");
        String correo = leerTexto("Correo: ");
        String telefono = leerTexto("Telefono: ");

        Cliente cliente = new Cliente(
                0, nombre, documento, correo, telefono
        );
        if (gestionarCliente.registrarCliente(cliente)) {
            System.out.println("Cliente registrado correctamente.");
        } else {
            System.out.println("Error al registrar cliente.");
        }
    }

    // Consultar clientes
    private void consultarClientes(){
        List<Cliente> lista = gestionarCliente.listarClientes();

        for (Cliente cliente : lista) {
            System.out.println("\nID: " + cliente.getid());
            System.out.println("Nombre: " + cliente.getnombre());
            System.out.println("Documento: " + cliente.getdocumento());
            System.out.println("Correo: " + cliente.getcorreo());
            System.out.println("Telefono: " + cliente.gettelefono());
        }
        if (lista.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        }
    }

    // Leer una opción válida del menú
    private int leerOpcion(String mensaje, int minimo, int maximo){
        int opcion;
        while (true) {
            try {
                System.out.print(mensaje);
                opcion = Integer.parseInt(entrada.nextLine());

                if (opcion >= minimo && opcion <= maximo){
                    return opcion;
                }

                System.out.println("Seleccione una opción entre "
                        + minimo + " y " + maximo + ".");

            } catch (NumberFormatException e){
                System.out.println("Ingrese una opción numérica válida.");
            }
        }
    }
}

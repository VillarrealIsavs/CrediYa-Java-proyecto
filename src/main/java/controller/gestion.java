

package controller;

import dao.implement.empleadoDAO;
import dao.implement.clienteDAO;
import dao.implement.prestamoDAO;
import dao.implement.pagoDAO;
import datos.Archivos;
import model.Empleado;
import model.Cliente;
import model.Prestamo;
import model.Pago;

/*
  Función: coordinar las operaciones de los módulos de empleados, clientes, préstamos y pagos.
 */
public class gestion {

    // Crear los objetos DAO
    private final empleadoDAO empleadoDao = new empleadoDAO();
    private final clienteDAO clienteDao = new clienteDAO();
    private final prestamoDAO prestamoDao = new prestamoDAO();
    private final pagoDAO pagoDao = new pagoDAO();

    // Crear el objeto para manejar archivos TXT
    private final Archivos archivo = new Archivos();

    // Registrar un empleado en MySQL
    public boolean registrarEmpleado(Empleado empleado) {
        return empleadoDao.guardar(empleado);
    }

    // Consultar los empleados registrados
    public java.util.List<Empleado> listarEmpleados() {
        return empleadoDao.listar();
    }

    // Guardar un empleado en un archivo TXT
    public void guardarEmpleadoTXT(Empleado empleado) {

        String datos = empleado.getid() + ","
                + empleado.getnombre() + ","
                + empleado.getdocumento() + ","
                + empleado.getcorreo() + ","
                + empleado.getrol() + ","
                + empleado.getsalario();

        archivo.guardar("empleados.txt", datos);
    }

    // Registrar un cliente en MySQL
    public boolean registrarCliente(Cliente cliente) {
        return clienteDao.guardar(cliente);
    }

    // Consultar los clientes registrados
    public java.util.List<Cliente> listarClientes() {
        return clienteDao.listar();
    }

    // Registrar un préstamo en MySQL
    public boolean registrarPrestamo(Prestamo prestamo) {
        return prestamoDao.guardar(prestamo);
    }

    // Consultar los préstamos registrados
    public java.util.List<Prestamo> listarPrestamos() {
        return prestamoDao.listar();
    }

    // Registrar un pago en MySQL
    public boolean registrarPago(Pago pago) {
        return pagoDao.guardar(pago);
    }

    // Consultar los pagos de un préstamo
    public java.util.List<Pago> listarPagos(Prestamo prestamo) {
        return pagoDao.listar(prestamo);
    }
    
}

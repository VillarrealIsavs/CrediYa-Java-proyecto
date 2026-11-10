
package model;
import java.time.LocalDate;
/** Función: almacenar la información de los préstamos realizados en CrediYa.*/
public class Prestamo{
    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private double monto;
    private double interes;
    private int cuotas;
    private double total;
    private String estado;
    private LocalDate fechaInicio;

    // Constructor vacío
    public Prestamo() {
    }

    // Constructor con parámetros
    public Prestamo(int id, Cliente cliente, Empleado empleado,
                    double monto, double interes, int cuotas,
                    String estado, LocalDate fechaInicio) {

        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
    }

    // Getters y Setters

    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public Cliente getcliente() {
        return cliente;
    }
    public void setcliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Empleado getempleado() {
        return empleado;
    }
    public void setempleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public double getmonto() {
        return monto;
    }
    public void setmonto(double monto) {
        this.monto = monto;
    }
    public double getinteres() {
        return interes;
    }
    public void setinteres(double interes) {
        this.interes = interes;
    }
    public int getcuotas() {
        return cuotas;
    }
    public void setcuotas(int cuotas) {
        this.cuotas = cuotas;
    }
    public double gettotal() {
        return total;
    }
    public void settotal(double total) {
        this.total = total;
    }
    public String getestado() {
        return estado;
    }
    public void setestado(String estado) {
        this.estado = estado;
    }
    public LocalDate getfechaInicio() {
        return fechaInicio;
    }
    public void setfechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
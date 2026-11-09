
package model;

import java.time.LocalDate;
/*
  Función: almacenar los pagos realizados por los clientes en CrediYa.
 */
public class Pago{
    private int id;
    private Prestamo prestamo;
    private double monto;
    private LocalDate fecha;

    public Pago() {
    }

    public Pago(int id, Prestamo prestamo, double monto, LocalDate fecha) {
        this.id = id;
        this.prestamo = prestamo;
        this.monto = monto;
        this.fecha = fecha;
    }

    // Consultar y modificar el id
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    // Consultar y modificar el préstamo
    public Prestamo getprestamo() {
        return prestamo;
    }

    public void setprestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    // Consultar y modificar el monto
    public double getmonto() {
        return monto;
    }

    public void setmonto(double monto) {
        this.monto = monto;
    }

    // Consultar y modificar la fecha
    public LocalDate getfecha() {
        return fecha;
    }

    public void setfecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

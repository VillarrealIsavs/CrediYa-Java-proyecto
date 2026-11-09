
package model;
/*
 Función: almacenar la información de los empleados registrados en el sistema CrediYa.
 */
public class Empleado extends Persona{
    private String rol;
    private double salario;

    public Empleado(){
    }

    // Inicializar los datos del empleado
    public Empleado(int id, String nombre, String documento, String correo, String rol, double salario){
        super(id, nombre, documento, correo);
        this.rol = rol;
        this.salario = salario;
    }

    // Consultar y modificar el rol
    public String getrol(){
        return rol;
    }

    public void setrol(String rol){
        this.rol = rol;
    }

    // Consultar y modificar el salario
    public double getsalario(){
        return salario;
    }

    public void setsalario(double salario){
        this.salario = salario;
    }
}

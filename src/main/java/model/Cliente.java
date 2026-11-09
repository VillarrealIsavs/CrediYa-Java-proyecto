
package model;
/*
  Función: almacenar la información de los clientes registrados en el sistema CrediYa.
 */
public class Cliente extends Persona{

    private String telefono;

    public Cliente(){
    }

    // Inicializar los datos del cliente
    public Cliente(int id, String nombre, String documento, String correo, String telefono){
        super(id, nombre, documento, correo);
        this.telefono = telefono;
    }

    // Consultar y modificar el teléfono
    public String gettelefono(){
        return telefono;
    }

    public void settelefono(String telefono){
        this.telefono = telefono;
    }
}


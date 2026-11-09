
package model;
/*
  Función: almacenar la información general de las personas registradas en CrediYa.
 */
public class Persona{
    private int id;
    private String nombre;
    private String documento;
    private String correo;
    
    public Persona(){
    }
    // Inicializar los datos de la persona
    public Persona(int id, String nombre, String documento, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
    }

    // Consultar y modificar los datos
    public int getid(){
        return id;}

    public void setid(int id){
        this.id = id;
    }

    public String getnombre(){
        return nombre;
    }

    public void setnombre(String nombre){
        this.nombre = nombre;
    }

    public String getdocumento(){
        return documento;
    }

    public void setdocumento(String documento) {
        this.documento = documento;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }
}

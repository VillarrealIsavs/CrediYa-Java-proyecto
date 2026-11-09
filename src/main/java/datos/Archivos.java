
package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    Función: guardar y consultar información mediante archivos de texto TXT.
 */
public class Archivos {

    // Guardar información en un archivo
    public void guardar(String nombreArchivo, String contenido) {

        File carpeta = new File("datos");
        carpeta.mkdirs();

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(new File(carpeta, nombreArchivo), true))) {

            escritor.write(contenido);
            escritor.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar información: " + e.getMessage());
        }
    }

    // Leer la información de un archivo
    public List<String> leer(String nombreArchivo) {

        List<String> lista = new ArrayList<>();

        File archivo = new File("datos", nombreArchivo);

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                lista.add(linea);
            }

        } catch (IOException e) {
            System.out.println("Error al leer información: " + e.getMessage());
        }

        return lista;
    }
}

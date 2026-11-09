
package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.Prestamo;

/**
 * Función: generar reportes de los préstamos
 * registrados en el sistema CrediYa.
 */
public class reportes {

    // Consultar los préstamos activos
    public List<Prestamo> prestamosActivos(List<Prestamo> prestamos) {

        return prestamos.stream()
                .filter(p -> p.getestado().equalsIgnoreCase("pendiente"))
                .collect(Collectors.toList());
    }

    // Consultar los préstamos cancelados
    public List<Prestamo> prestamosCancelados(List<Prestamo> prestamos) {

        return prestamos.stream()
                .filter(p -> p.getestado().equalsIgnoreCase("cancelado"))
                .collect(Collectors.toList());
    }
}

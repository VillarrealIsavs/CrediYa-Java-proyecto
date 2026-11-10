
package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.Prestamo;

/*
 Función: consultar los préstamos según su estado.
 */
public class reportes{
    public List<Prestamo> prestamosActivos(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> p.getestado().equals("pendiente"))
                .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosCancelados(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> p.getestado().equals("cancelado"))
                .collect(Collectors.toList());
    }
}
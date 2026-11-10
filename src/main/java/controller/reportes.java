

package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Prestamo;

/*
 * Función: consultar los préstamos según su estado y vencimiento.
 */
public class reportes {

    public List<Prestamo> prestamosActivos(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> "pendiente".equals(p.getestado()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosCancelados(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> "cancelado".equals(p.getestado()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosEntregados(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> "entregado".equals(p.getestado()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosPagados(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> "pagado".equals(p.getestado()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosVencidos(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> p.getfechaVencimiento() != null
                && p.getfechaVencimiento().isBefore(LocalDate.now())
                && !"pagado".equals(p.getestado())
                && !"cancelado".equals(p.getestado()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> clientesMorosos(List<Prestamo> prestamos) {
        Map<Integer, Prestamo> clientes = prestamosVencidos(prestamos)
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getcliente().getid(),
                        p -> p,
                        (anterior, actual) -> anterior,
                        LinkedHashMap::new
                ));

        return new ArrayList<>(clientes.values());
    }
}

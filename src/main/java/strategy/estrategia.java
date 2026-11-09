
package strategy;

/*
    Función: establecer el método para calcular los intereses de los préstamos.
 */
public interface estrategia {

    // Calcular los intereses según el método elegido
    double calcularInteres(double monto, double interes, int cuotas);
}

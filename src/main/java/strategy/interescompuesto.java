
package strategy;

/**
 * Función: calcular el interés compuesto de un préstamo.
 */
public class interescompuesto implements estrategia {

    @Override
    public double calcularInteres(double monto, double interes, int cuotas) {

        double tasa = interes / 100;
        double total = 1;

        for (int i = 0; i < cuotas; i++) {
            total = total * (1 + tasa);
        }

        return monto * (total - 1);
    }
}

package strategy;

/*
    Función: calcular los intereses de un préstamo utilizando el método de interés compuesto.
 */
public class interescompuesto implements estrategia {

    // Calcular el interés compuesto del préstamo
    @Override
    public double calcularInteres(double monto, double interes, int cuotas) {

        double tasa = interes / 100;

        return monto * (Math.pow(1 + tasa, cuotas) - 1);
    }
}

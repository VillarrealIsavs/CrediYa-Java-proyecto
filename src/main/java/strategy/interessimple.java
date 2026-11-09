
package strategy;

/*
    Función: calcular los intereses de un préstamo utilizando el método de interés simple.
 */
public class interessimple implements estrategia {

    // Calcular el interés simple del préstamo
    @Override
    public double calcularInteres(double monto, double interes, int cuotas) {

        return monto * (interes / 100) * cuotas;
    }
}

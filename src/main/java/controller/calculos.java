
package controller;

import strategy.estrategia;

/*
  Función: calcular el total de los préstamos y el valor de sus cuotas.
 */
public class calculos{

    // Calcular el total del préstamo con intereses
    public double calcularTotal(double monto, double interes, int cuotas, estrategia metodo) {

        double intereses = metodo.calcularInteres(monto, interes, cuotas);

        return monto + intereses;
    }

    // Calcular el valor de cada cuota
    public double calcularCuota(double monto, double interes, int cuotas, estrategia metodo) {

        if (cuotas <= 0){
            throw new IllegalArgumentException("El número de cuotas debe ser mayor que cero.");
        }

        double total = calcularTotal(monto, interes, cuotas, metodo);

        return total / cuotas;
    }
}

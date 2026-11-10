
package controller;

import view.Menu;

/*
    Función: controlar el flujo principal del sistema CrediYa.
 */
public class GestionarFlujo {

    private final Menu menu = new Menu();

    public void iniciar() {
        menu.mostrarMenu();
    }
}
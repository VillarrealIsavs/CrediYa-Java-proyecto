
package controller;

import view.Menu;

/*
    Función: controlar el funcionamiento principal de CrediYa.
 */
public class GestionarFlujo{
    private final Menu menu = new Menu();

    // Iniciar el sistema
    public void iniciar() {
        menu.mostrarMenu();
    }
}
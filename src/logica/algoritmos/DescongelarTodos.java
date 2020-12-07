package logica.algoritmos;

import logica.Algoritmo;
import logica.Juego;

public class DescongelarTodos extends Algoritmo {

	public void ejecutar(Object object) {
		((Juego)object).descongelarTodos();
	}

	
}

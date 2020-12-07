package logica.algoritmos;

import entidades.*;
import logica.Algoritmo;

public class Disparo extends Algoritmo {

	public void ejecutar(Object object) {
		((Jugador)object).puedeDisparar();
	}
}

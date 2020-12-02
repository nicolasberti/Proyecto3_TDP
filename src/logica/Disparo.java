package logica;

import entidades.*;

public class Disparo extends Algoritmo {

	public void ejecutar(Object object) {
		((Jugador)object).puedeDisparar();
	}
}

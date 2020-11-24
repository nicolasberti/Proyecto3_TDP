package logica;

import entidades.*;

public class Disparo extends Algoritmo {

	public void ejecutar(Entidad entidad) {
		((Jugador)entidad).puedeDisparar();
	}
}

package logica.algoritmos;

import logica.Algoritmo;
import logica.Juego;

public class Descongelar extends Algoritmo {

	@Override
	public void ejecutar(Object object) {
		((Juego)object).descongelarInfectados();
	}

}

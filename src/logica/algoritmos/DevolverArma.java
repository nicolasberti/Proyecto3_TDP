package logica.algoritmos;

import entidades.*;
import logica.Algoritmo;

public class DevolverArma extends Algoritmo {

	private Arma exArma;
	
	public DevolverArma(Arma exArma) {
		this.exArma = exArma;
	}
	
	@Override
	public void ejecutar(Object object) {
		((Jugador)object).setArma(exArma);
	}

}

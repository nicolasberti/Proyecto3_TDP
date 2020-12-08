package logica.algoritmos;

import logica.Algoritmo;
import logica.Juego;

public class Descongelar extends Algoritmo {

	private int nivelUtilizado;
	private int tanda;
	
	public Descongelar(int nivel, int tanda) {
		nivelUtilizado = nivel;
		this.tanda = tanda;
	}
	
	@Override
	public void ejecutar(Object object) {
		Juego juego = ((Juego)object);
		if(juego.getNivelActual() == nivelUtilizado && juego.getTandaActual() == tanda)
			juego.descongelarInfectados();
	}

}

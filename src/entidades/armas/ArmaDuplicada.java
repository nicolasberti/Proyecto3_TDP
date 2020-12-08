package entidades.armas;

import entidades.Arma;
import logica.Juego;

public class ArmaDuplicada extends Arma {

	public ArmaDuplicada() { 
		// Duplica el arma actual del jugador.
		int multiplicando = 2;
		velocidad = Juego.get().getJugador().getArma().getVelocidad() * multiplicando;
		cargaDesinfeccion = Juego.get().getJugador().getArma().getCargaDesinfeccion() * multiplicando;
	}
	
}

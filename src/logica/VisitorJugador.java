package logica;

import entidades.*;

public class VisitorJugador extends Visitor {

	@Override
	public void visit(Particula particula) {
		// Algoritmo si un JUGADOR visita a una PARTICULA
	}

	@Override
	public void visit(Jugador jugador) {
		// Algoritmo si un JUGADOR visita a un JUGADOR
		
	}

	@Override
	public void visit(Infectado infectado) {
		// Algoritmo si un JUGADOR visita a un INFECTADO
		
	}
	
	@Override
	public void visit(Premio premio) {
		// Algoritmo si un JUGADOR visita a un PREMIO
	}

}

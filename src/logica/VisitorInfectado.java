package logica;

import entidades.*;

public class VisitorInfectado extends Visitor {

	@Override
	public void visit(Particula particula) {
		// Algoritmo si un INFECTADO visita a una PARTICULA
	}

	@Override
	public void visit(Jugador jugador) {
		// Algoritmo si un INFECTADO visita a un JUGADOR
		
	}

	@Override
	public void visit(Infectado infectado) {
		// Algoritmo si un INFECTADO visita a un INFECTADO
		
	}
	
	@Override
	public void visit(Premio premio) {
		// Algoritmo si un INFECTADO visita a un PREMIO
	}

}

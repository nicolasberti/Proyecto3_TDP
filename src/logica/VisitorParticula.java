package logica;

import entidades.*;

public class VisitorParticula extends Visitor {

	@Override
	public void visit(Particula particula) {
		// Algoritmo si una PARTICULA visita a una PARTICULA
	}

	@Override
	public void visit(Jugador jugador) {
		// Algoritmo si una PARTICULA visita a un JUGADOR
		
	}

	@Override
	public void visit(Infectado infectado) {
		// Algoritmo si una PARTICULA visita a un INFECTADO
		
	}
	
	@Override
	public void visit(Premio premio) {
		// Algoritmo si una PARTICULA visita a un PREMIO
	}

}

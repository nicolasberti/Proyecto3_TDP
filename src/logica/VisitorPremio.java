package logica;

import entidades.*;

public class VisitorPremio extends Visitor {

	@Override
	public void visit(Proyectil proyectil) {
		// Algoritmo si un PREMIO visita a un PROYECTIL
	}

	@Override
	public void visit(Jugador jugador) {
		// Algoritmo si un PREMIO visita a un JUGADOR
		
	}

	@Override
	public void visit(Infectado infectado) {
		// Algoritmo si un PREMIO visita a un INFECTADO
		
	}
	
	@Override
	public void visit(Premio premio) {
		// Algoritmo si un PREMIO visita a un PREMIO
	}

}

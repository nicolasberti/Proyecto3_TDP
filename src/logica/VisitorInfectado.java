package logica;

import entidades.*;

public class VisitorInfectado extends Visitor {

	@Override
	public void visit(Proyectil proyectil) {
		// Algoritmo si un INFECTADO visita a un PROYECTIL
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

package logica;

import entidades.*;

public class VisitorProyectil extends Visitor {

	@Override
	public void visit(Proyectil proyectil) {
		// Algoritmo si una PARTICULA visita a un PROYECTIL
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

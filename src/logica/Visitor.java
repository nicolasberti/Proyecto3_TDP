package logica;

import entidades.*;

public abstract class Visitor {

	public abstract void visit(Particula particula);
	public abstract void visit(Jugador jugador);
	public abstract void visit(Infectado infectado);
}

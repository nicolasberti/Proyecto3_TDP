package logica;

import entidades.*;

public abstract class Visitor {

	public abstract void visit(Proyectil proyectil);
	public abstract void visit(Jugador jugador);
	public abstract void visit(Infectado infectado);
	public abstract void visit(Premio premio);
}

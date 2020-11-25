package logica;

import entidades.*;

public abstract class Visitor {

	public abstract boolean visit(Infectado infectado, int cargaViral);
	public abstract boolean visit(Jugador jugador, int cargaViral);
}

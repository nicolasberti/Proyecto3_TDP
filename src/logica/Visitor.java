package logica;

import entidades.*;

public abstract class Visitor {

	public abstract boolean visit(Infectado infectado, int desinfeccion);
}

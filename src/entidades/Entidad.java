package entidades;

import logica.*;

public abstract class Entidad {

	protected int velocidad;
	protected Posicion posicion;
	
	public abstract void accept(Visitor visit);
	
}

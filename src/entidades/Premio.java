package entidades;

import logica.Juego;

@SuppressWarnings("serial")
public abstract class Premio extends Entidad {
	
	protected boolean usado = false;
	// Los premios se mueven de abajo hacia arriba.

	public abstract void utilizar();
	
	public boolean getUsado() { return usado; }
}

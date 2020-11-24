package entidades;

import logica.Visitor;

public abstract class Premio extends Entidad {

	public Premio() {
		
	}
	
	// Los premios se mueven de abajo hacia arriba.
	public void moverse() {
		this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
	}
	
}

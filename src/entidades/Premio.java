package entidades;

@SuppressWarnings("serial")
public abstract class Premio extends Entidad {
	
	protected boolean usado = false;
	// Los premios se mueven de abajo hacia arriba.
	public void moverse() {
		if(!congelado)
			this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
	}

	public abstract void utilizar();
	
}

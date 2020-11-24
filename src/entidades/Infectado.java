package entidades;

import logica.Visitor;

public abstract class Infectado extends Entidad {

	protected int cargaViral;
	protected int rangoDeInfeccion;
	protected boolean jugando;
	protected int [] posInicial;
	protected boolean congelado;
	protected boolean desinfectado;
	protected boolean girando;
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
	
	public boolean getJugando() { return jugando; } 
	
	public void volverPos() {
		posicion.setX(posInicial[0]);
		posicion.setY(posInicial[1]);
		girando = false;
	}
	
	// Los infectados se mueven de arriba hacia abajo.
	public void moverse() {
		this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
		if(!girando) {
			girando = this.getX()>400;
			this.setX(this.getX()+this.calculoAvanzar(this.getVelocidad()));
			}
		else {
			girando = this.getX()>100;
			this.setX(this.getX()-this.calculoAvanzar(this.getVelocidad()));
			}
	}
	
	public void desaparecer() { }
	
	public void congelar() { }
	
	
}

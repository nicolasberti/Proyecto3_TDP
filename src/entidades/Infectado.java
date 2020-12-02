package entidades;

import logica.Visitor;

public abstract class Infectado extends Entidad {

	protected int cargaViral = 100; // Vida del infectado
	protected int danio;			// Da�o que produce el infectado
	protected int cargaDesinfeccion; // N�mero para hacer el c�lculo de cuanto se puede desinfectar seg�n el proyectil recibido
	protected int rangoDeInfeccion; // Rango de infecci�n del infectado
	protected boolean jugando; // Representa si el infectado est� jugando
	protected int [] posInicial; // Posiciones iniciales del infectado
	protected boolean girando; // Representa si el infectado est� girando
	
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
		if(!congelado) {
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
	}
	
	public int getDanio() { return danio; }
	
	public int getCargaDesinfeccion() { return cargaDesinfeccion; }
	
	public int getCargaViral() { return cargaViral; }
	
	public void setCargaViral(int cargaViral) { this.cargaViral = cargaViral; }
	
	
	
	public boolean accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
}

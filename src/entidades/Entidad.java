package entidades;

import javax.swing.JLabel;

import logica.Juego;
import logica.Visitor;
@SuppressWarnings("serial")
public abstract class Entidad extends JLabel {

	protected int velocidad;
	protected Posicion posicion;
	protected boolean congelado = false;
	
	public int getVelocidad() { return velocidad; }
	
	public void setY(int y) {
		posicion.setY(y);
		this.setBounds(posicion.getX(), y, this.getWidth(), this.getHeight());
	}
	
	public void setX(int x) {
		posicion.setX(x);
		this.setBounds(x, posicion.getY(), this.getWidth(), this.getHeight());
	}
	
	// Las entidades en principio se mueven de arriba hacía abajo.
	public boolean moverse() {
		if(!congelado) {
			this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
			if(this.getY() >= Juego.get().getMapa().getLinea())
				return true;
		}
		return false;
	}
	
	public boolean getCongelado() { return congelado; }
	
	public void congelar() { congelado = true; }
	
	public void descongelar() { congelado = false; }
	
	public int getX() { return posicion.getX(); }
	
	public int getY() { return posicion.getY(); }
	
	
	public boolean estaEnElRadio(Entidad entidad) {
		return posicion.estaEnElRadio(entidad.getPosicion());
	}
	
	private Posicion getPosicion() { return posicion; }
	
	public int calculoAvanzar(int velocidad) {
		return velocidad*5;
	}
	
	public abstract void accept(Visitor visit);
	
	public abstract void disparar();
	
}

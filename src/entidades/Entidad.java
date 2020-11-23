package entidades;

import javax.swing.JLabel;

import logica.*;

public abstract class Entidad extends JLabel {

	protected int velocidad;
	protected Posicion posicion;
	
	public abstract void accept(Visitor visitor);
	
	public int getVelocidad() { return velocidad; }
	
	public void setY(int y) {
		posicion.setY(y);
		this.setBounds(posicion.getX(), y, this.getWidth(), this.getHeight());
	}
	
	public void setX(int x) {
		posicion.setX(x);
		this.setBounds(x, posicion.getY(), this.getWidth(), this.getHeight());
	}
	
	public int getX() { return posicion.getX(); }
	
	public int getY() { return posicion.getY(); }
	
	
	public boolean estaEnElRadio(Entidad entidad) {
		return posicion.estaEnElRadio(entidad.getPosicion());
	}
	
	private Posicion getPosicion() { return posicion; }
	
}

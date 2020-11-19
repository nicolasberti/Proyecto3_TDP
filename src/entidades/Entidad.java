package entidades;

import javax.swing.JLabel;

import logica.*;

public abstract class Entidad extends JLabel {

	protected int velocidad;
	protected Posicion posicion;
	
	public abstract void accept(Visitor visit);
	
}

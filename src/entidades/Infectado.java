package entidades;

import logica.Visitor;

public abstract class Infectado extends Entidad {

	protected int cargaViral;
	protected int rangoDeInfeccion;
	protected boolean jugando;
	protected int [] posInicial;
	protected boolean congelado;
	protected boolean desinfectado;
	
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
	
	public boolean getJugando() { return jugando; } 
	
	public void volverPos() {
		posicion.setX(posInicial[0]);
		posicion.setY(posInicial[1]);
	}
	
	
	public abstract void desaparecer();
	//public abstract void moverse();
	public abstract void congelar();
	
	
}

package entidades;

import logica.Visitor;

public abstract class Infectado extends Entidad {

	private int cargaViral;
	private int rangoDeInfeccion;
	private boolean jugando;
	private int [] posInicial;
	private boolean congelado;
	private boolean desinfectado;
	
	public abstract void infectar(Visitor visit);
	public abstract void desaparecer();
	public abstract void volverPos();
	//public abstract void moverse();
	public abstract void congelar();
}

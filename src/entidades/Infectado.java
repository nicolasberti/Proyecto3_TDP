package entidades;

import logica.Visitor;

public abstract class Infectado extends Entidad {

	protected int cargaViral;
	protected int rangoDeInfeccion;
	protected boolean jugando;
	protected int [] posInicial;
	protected boolean congelado;
	protected boolean desinfectado;
	
	public abstract void desaparecer();
	public abstract void volverPos();
	//public abstract void moverse();
	public abstract void congelar();
}

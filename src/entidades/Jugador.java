package entidades;

import logica.*;

public class Jugador extends Entidad {

	private int cargaViral;
	
	public Jugador(int velocidad) {
		//Modificar
		int x,y,r;
		x=y=r=0;
		
		
		super.posicion = new Posicion(x,y,r);
		super.velocidad = velocidad;
	}
	
	public void disparar() {
		
	}
	
	public void usarEfecto(int indice) {
		
	}
	
	public void moverDerecha() {
		
	}
	
	public void moverIzquierda() {
		
	}
	
	public void usarPremio(Premio p) {
		
	}
	
	public void accept(Visitor visit) {
		
	}
		
}

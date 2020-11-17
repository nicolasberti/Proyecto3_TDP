package entidades;

import logica.*;

public class Jugador extends Entidad {

	private int cargaViral;
	
	public Jugador(int velocidad) {
		super.posicion = new Posicion();
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

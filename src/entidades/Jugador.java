package entidades;

import logica.*;

public class Jugador extends Entidad {

	private int cargaViral;
	
	public Jugador(int velocidad) {

		// Cordenadas preterminadas
		super.posicion = new Posicion(250,400,5);
		super.velocidad = velocidad;
		posicion.actualizar(this);
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

package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.*;
import logica.*;

public class Jugador extends Entidad {

	private int cargaViral;
	
	public Jugador(int velocidad) {

		// Cordenadas preterminadas
		super.posicion = new Posicion(250,400,5);
		super.velocidad = velocidad;
		
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/jugador.gif"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}
	
	public void disparar() {
		
	}
	
	public void usarEfecto(int indice) {
		
	}
	
	public void moverDerecha() {
		this.setX((this.getX())+this.calculoAvanzar(this.getVelocidad()));
	}
	
	public void moverIzquierda() {
		this.setX((this.getX())-this.calculoAvanzar(this.getVelocidad()));
	}
	
	public void usarPremio(Premio p) {
		
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
		
}

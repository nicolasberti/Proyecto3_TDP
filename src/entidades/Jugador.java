package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.*;
import logica.*;

public class Jugador extends Entidad {

	private int cargaViral;
	private Arma miArma;
	private boolean puedeDisparar;
	
	public Jugador(int velocidad) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(250,400,30);
		super.velocidad = velocidad;
		this.miArma = new ArmaComun();
		this.puedeDisparar = true;
		
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/jugador.gif"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}
	
	public void disparar() {
		if(puedeDisparar) {
			puedeDisparar = false;
			
			
			AutoAlgoritmo habilitar = new AutoAlgoritmo(new Disparo(), 1, this); // 1 segundo para habiltiar otro disparo.
			habilitar.start();
		}
	}
	
	public Arma getArma() { return miArma; }
	
	public void setArma(Arma arma) { this.miArma = arma; }
	
	public void puedeDisparar() {
		puedeDisparar = true;
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
	
}

package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.*;
import logica.*;

public class Jugador extends Entidad {

	/*
	 * Para el jugador podemos usar también un patrón Singleton ya que solo se tendrá una instancia de él.
	 */
	private static Jugador jugador;
	private int cargaViral = 0;
	private Arma miArma;
	private boolean puedeDisparar;
	
	public static Jugador get(int velocidad) {
		if(jugador == null) {
			jugador = new Jugador(velocidad);
		}
		return jugador;
	}
	
	private Jugador(int velocidad) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(250,400,20);
		super.velocidad = velocidad;
		this.miArma = new ArmaComun();
		this.puedeDisparar = true;
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon image = new ImageIcon(GUI_nueva.class.getResource("/img/jugador.gif"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	public Proyectil disparar() {
		Proyectil disparo = null;
		if(puedeDisparar && !congelado) {
			puedeDisparar = false;
			disparo = new Proyectil(this.getX(), this.getY(), this.getArma().getVelocidad(), this.getArma().getCargaDesinfeccion());
			AutoAlgoritmo habilitar = new AutoAlgoritmo(new Disparo(), 1, this); // 1 segundo para habiltiar otro disparo.
			habilitar.start();
		}
		return disparo;
	}
	
	public void setCargaViral(int cargaViral) { this.cargaViral = cargaViral; }
	
	public int getCargaViral() { return cargaViral; }
	
	public Arma getArma() { return miArma; }
	
	public void setArma(Arma arma) { this.miArma = arma; }
	
	public void puedeDisparar() {
		puedeDisparar = true;
	}
	public void usarEfecto(int indice) {
		
	}
	
	public void moverDerecha() {
		if(!congelado)
			this.setX((this.getX())+this.calculoAvanzar(this.getVelocidad()));
	}
	
	public void moverIzquierda() {
		if(!congelado)
			this.setX((this.getX())-this.calculoAvanzar(this.getVelocidad()));
	}
	
	public void usarPremio(Premio p) {
		p.utilizar();
	}
	
	public boolean accept(Visitor visit) {
		return visit.visit(this);
	}
	
}

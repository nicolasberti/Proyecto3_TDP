package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.Visitor;

public class Particula extends Entidad {

	private int tiempoEnVida; // Segundos
	
	public Particula(int x, int y, int velocidad) {
		// OBS: Si bien la particula se desplaza en forma vertical (es decir, solo avanza en una dirección) puede infectar en una cierta circunferencia.
		super.posicion = new Posicion(x,y,20);
		super.velocidad = velocidad;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		this.tiempoEnVida = 0;
		// Agregar imagen de particula
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/particula.png"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}
	
	// Las particulas se mueven de arriba hacia abajo.
	public void moverse() {
		this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
	}
	
	public void sumarTiempo() {
		tiempoEnVida++;
	}
	
	public int getTiempo() { return tiempoEnVida; }
	
}

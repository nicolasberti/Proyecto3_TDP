package entidades;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import GUI.GUI_juego;

@SuppressWarnings("serial")
public class Particula extends Entidad {

	private int tiempoEnVida; // Segundos
	private int danio; // Daño que recibirá el jugador.
	
	public Particula(int danio, int x, int y, int velocidad) {
		this.danio = danio;
		// OBS: Si bien la particula se desplaza en forma vertical (es decir, solo avanza en una dirección) puede infectar en una cierta circunferencia.
		super.posicion = new Posicion(x,y,30);
		super.velocidad = velocidad;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		this.tiempoEnVida = 0;
		// Agregar imagen de particula
		ImageIcon image = new ImageIcon(GUI_juego.class.getResource("/img/particula.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	// Las particulas se mueven de arriba hacia abajo.
	public void moverse() {
		if(!congelado)
			this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
	}
	
	public int getDanio() { return danio; }
	
	public void sumarTiempo() {
		tiempoEnVida++;
	}
	
	public int getTiempo() { return tiempoEnVida; }
	
}

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
		super.posicion = new Posicion(x,y,5);
		super.velocidad = velocidad;
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		this.tiempoEnVida = 0;
		// Las particulas no tienen un sprite, ya que se interpretan como que son muy chicas. En el caso de colocar un sprite, simplemente, hay que colocar un icono acá y en el hilo de movimiento agregarlas al panel del juego.
	}
	
	public void sumarTiempo() {
		tiempoEnVida++;
	}
	
	public int getTiempo() { return tiempoEnVida; }
	
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

}

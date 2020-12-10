package entidades;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import GUI.GUI_juego;
import logica.Juego;
import logica.Visitor;

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
	
	public int getDanio() { return danio; }
	
	
	public boolean moverse() {
		boolean borrar = false;
		int segundos = 3; // Duración de las particulas
		int hiloTiempo = 5; // El hilo itera cada 200milisegundos. Entonces, 1 segundo serían 5 * 200.
		if(!congelado) {
			tiempoEnVida++;
			this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
			if(this.getY() >= Juego.get().getMapa().getLinea())
				borrar = true;
			if( tiempoEnVida >= segundos*hiloTiempo)
				borrar = true;
		}
		return borrar;
	}
	
	@Override
	public void accept(Visitor visit) {
		
	}

	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}
	
}

package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.Visitor;

public class Proyectil extends Entidad {

	private int cargaDesinfeccion;
	
	public Proyectil(int x, int y, int velocidad, int cargaDesinfeccion) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(x,y,20);
		super.velocidad = velocidad; // Velocidad según el arma que la dispara
		this.cargaDesinfeccion = cargaDesinfeccion;
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/disparo.png"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}
	
	// Los proyectiles se mueven de abajo hacia arriba.
	public void moverse() {
		this.setY( ( this.getY()- this.calculoAvanzar(this.getVelocidad()) ) );
	}
	
	public int getCargaDesinfeccion() { return cargaDesinfeccion; }
	

}

package entidades;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import GUI.GUI;

@SuppressWarnings("serial")
public class Proyectil extends Entidad {

	private int cargaDesinfeccion;
	
	public Proyectil(int x, int y, int velocidad, int cargaDesinfeccion) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(x,y,20);
		super.velocidad = velocidad; // Velocidad según el arma que la dispara
		this.cargaDesinfeccion = cargaDesinfeccion;
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon image = new ImageIcon(GUI.class.getResource("/img/disparo.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	// Los proyectiles se mueven de abajo hacia arriba.
	public void moverse() {
		if(!congelado)
			this.setY( ( this.getY()- this.calculoAvanzar(this.getVelocidad()) ) );
	}
	
	public int getCargaDesinfeccion() { return cargaDesinfeccion; }
	

}

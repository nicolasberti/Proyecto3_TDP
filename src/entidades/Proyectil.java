package entidades;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import GUI.GUI_juego;
import logica.Juego;
import logica.Visitor;

@SuppressWarnings("serial")
public class Proyectil extends Entidad {

	private int cargaDesinfeccion;
	private boolean habilitado = true;
	
	public Proyectil(int x, int y, int velocidad, int cargaDesinfeccion) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(x,y,20);
		super.velocidad = velocidad; // Velocidad según el arma que la dispara
		this.cargaDesinfeccion = cargaDesinfeccion;
		this.setBounds(posicion.getX(), posicion.getY(), 30, 65);
		ImageIcon image = new ImageIcon(GUI_juego.class.getResource("/img/disparo.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	// Los proyectiles se mueven de abajo hacia arriba.
	public boolean moverse() {
		if(!congelado) {
			this.setY( ( this.getY()- this.calculoAvanzar(this.getVelocidad()) ) );
			if(this.getY() <= 0)
				return true;
		}
		return false;
	}
	
	public void deshabilitar() { habilitado = false; }
	public boolean habilitado() { return habilitado; }
	
	public int getCargaDesinfeccion() { return cargaDesinfeccion; }

	@Override
	public void accept(Visitor visit) {
		// TODO Auto-generated method stub 
	}

	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}
	

}

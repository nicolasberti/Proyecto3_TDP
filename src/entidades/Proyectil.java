package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.Visitor;

public class Proyectil extends Entidad {

	
	public Proyectil(int velocidad) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(250,250,5);
		super.velocidad = velocidad; // Velocidad según el arma que la dispara
				
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/disparo.png"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}

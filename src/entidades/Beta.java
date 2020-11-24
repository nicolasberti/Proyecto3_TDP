package entidades;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.Visitor;

public class Beta extends Infectado {

	public Beta() {
		Random rnd = new Random();
		super.rangoDeInfeccion = 30;
		super.posicion = new Posicion(rnd.nextInt(500), rnd.nextInt(100),rangoDeInfeccion);
		super.velocidad = 1;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		super.girando = false;
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/infectadoBeta.gif"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}

}

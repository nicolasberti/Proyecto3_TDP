package entidades;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.Juego;
import logica.Mapa;
import logica.Visitor;

public class Alpha extends Infectado {

	public Alpha() {
		
		Mapa mapa = Juego.get().getMapa();
		Random rnd = new Random();
		super.rangoDeInfeccion = 20;
		super.cargaDesinfeccion = 4;
		super.danio = 4;
		super.posicion = new Posicion(rnd.nextInt(mapa.getX()), rnd.nextInt(mapa.getY()/100),rangoDeInfeccion);
		super.velocidad = 2;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		super.girando = false;
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon image = new ImageIcon(GUI_nueva.class.getResource("/img/infectadoAlpha.gif"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}

}

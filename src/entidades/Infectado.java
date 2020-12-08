package entidades;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import logica.Juego;
import logica.Mapa;
import logica.Visitor;

@SuppressWarnings("serial")
public abstract class Infectado extends Entidad {

	protected int cargaViral = 100; // Vida del infectado
	protected int danio;			// Daño que produce el infectado
	protected int cargaDesinfeccion; // Número para hacer el cálculo de cuanto se puede desinfectar según el proyectil recibido
	protected int rangoDeInfeccion; // Rango de infección del infectado
	protected boolean jugando; // Representa si el infectado está jugando
	protected int [] posInicial; // Posiciones iniciales del infectado
	protected boolean girando; // Representa si el infectado está girando
	protected int nivel; // Indica de que nivel es el infectado.
	
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
	
	public boolean getJugando() { return jugando; } 
	
	public void volverPos() {
		posicion.setX(posInicial[0]);
		posicion.setY(posInicial[1]);
		girando = false;
	}
	
	// Los infectados se mueven de arriba hacia abajo.
	public void moverse() {
		boolean actualizarImagen = girando;
		if(!congelado) {
			this.setY( ( this.getY()+ this.calculoAvanzar(this.getVelocidad()) ) );
			if(!girando) {
				girando = this.getX()>400;
				this.setX(this.getX()+this.calculoAvanzar(this.getVelocidad()));
				}
			else {
				girando = this.getX()>100;
				this.setX(this.getX()-this.calculoAvanzar(this.getVelocidad()));
			}
			if(actualizarImagen != girando)
				this.actualizarImagen();
		}
	}
	
	public int getDanio() { return danio; }
	
	public int getCargaDesinfeccion() { return cargaDesinfeccion; }
	
	public int getCargaViral() { return cargaViral; }
	
	public void setCargaViral(int cargaViral) { this.cargaViral = cargaViral; }
	
	public void actualizarImagen() {
		String infectadoTipo = this.getClass().toString().substring(this.getClass().toString().lastIndexOf('.')+1);
		ImageIcon image;
		if(girando)
			 image = new ImageIcon(this.getClass().getResource("/img/infectados/nivel"+nivel+"/"+infectadoTipo+".gif"));
		else
			 image = new ImageIcon(this.getClass().getResource("/img/infectados/nivel"+nivel+"/"+infectadoTipo+"Rotado.gif"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	
	public boolean accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public int[] generarCordenadas() {
		Random rnd = new Random();
		Mapa mapa = Juego.get().getMapa();
		int cordenadas[] = new int[2];
		cordenadas[0] = rnd.nextInt(mapa.getX()-20);
		cordenadas[1] = rnd.nextInt(mapa.getY()/100);
		return cordenadas;
	}
	
}

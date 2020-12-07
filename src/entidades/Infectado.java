package entidades;

import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	protected int imagenActual; // Índice de la imagen actual
	
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
			this.actualizarImagen();
		}
	}
	
	public int getDanio() { return danio; }
	
	public int getCargaDesinfeccion() { return cargaDesinfeccion; }
	
	public int getCargaViral() { return cargaViral; }
	
	public void setCargaViral(int cargaViral) { this.cargaViral = cargaViral; }
	
	public void actualizarImagen() {
		String infectadoTipo = this.getClass().toString().substring(this.getClass().toString().lastIndexOf('.')+1);
		String rutaOrigen = System.getProperty("user.dir")+"\\src\\img\\infectados\\nivel"+nivel+"\\"+infectadoTipo;
		int cantArchivos = new File(rutaOrigen).listFiles().length;
		//cantArchivos = cantArchivos/2; 
		if(imagenActual == cantArchivos-1)
			imagenActual = 0;
		else
			imagenActual++;
		ImageIcon image = new ImageIcon(rutaOrigen+"\\"+imagenActual+".png");
		/*if(!girando)
			ImageIcon image = new ImageIcon(rutaOrigen+"\\"+imagenActual+".png");
		else
			ImageIcon image = new ImageIcon(rutaOrigen+"\\"+imagenActual+"GIRANDO.png");
			*/
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	
	public boolean accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
}

package logica;

import java.util.ArrayList;
import java.util.List;
import aplicacion.*;

import javax.swing.JFrame;

import entidades.*;

public class HiloMovimiento extends Thread {

	private Juego miJuego;
	private List<Infectado> infectados;
	private List<Premio> premios;
	private List<Particula> particulas;
	private JFrame frame;
	private boolean jugando = false;
	
	public HiloMovimiento(Juego miJuego, JFrame frame) {
		this.miJuego = miJuego;
		this.infectados = new ArrayList<Infectado>();
		this.premios =  new ArrayList<Premio>();
		this.particulas =  new ArrayList<Particula>();
		this.frame = frame;
	}
	
	public List<Infectado> getInfectados(){ return infectados; }
	public List<Premio> getPremio(){ return premios; }
	public List<Particula> getParticulas(){ return particulas; }
	
	public void empezar() {
		jugando = true;
		this.start();
	}
	
	public void run() {
		try {
			
			
			while(jugando) {
				HiloMovimiento.sleep(200); 
				// Movimiento de los infectados
				for(Infectado infectado : infectados) {
					if(infectado.getY() >= miJuego.getMapa().getLinea()) {
						infectado.volverPos();
					} else {
						infectado.setY( ( infectado.getY()+avanzar(infectado.getVelocidad()) ) );
					}

					for(Particula particula : particulas) {
						if(infectado.estaEnElRadio(particula)) {
							// colisión entre un infectado y una particula
						}
							
					}
				}
				 ((GUI) frame).repintar();
			}
		
		
		} 
		catch (InterruptedException e1) { e1.printStackTrace(); }
		
		
	}
	
	// Funciones para calcular cuantas cordenadas tiene que avanzar según la velocidad.
	public int avanzar(int velocidad) {
		int entero = 1;
		return velocidad*entero;
	}

}

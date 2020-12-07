package logica.hilos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GUI.*;
import entidades.Jugador;
import entidades.Proyectil;
import logica.Juego;

public class HiloTeclado extends Thread implements KeyListener {

	public GUI GUI;
	
	public HiloTeclado(GUI GUI) {
		super();
		this.GUI = GUI;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Juego juego = Juego.get();
		if(juego.getJugando() == true) {
			Jugador jugador = juego.getJugador();
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT: {
				if(jugador.getX() >= 10) {
					jugador.moverIzquierda();
					if(jugador.getX() <= 10)
						jugador.setX(10); 
				}
				break;
			}
			case KeyEvent.VK_RIGHT: {
				if(jugador.getX() <= juego.getMapa().getX()-70) {
					jugador.moverDerecha();
					if(jugador.getX() >= juego.getMapa().getX()-70)
						jugador.setX(juego.getMapa().getX()-70);
				}
				break;
			}
			case KeyEvent.VK_SPACE:{
				Proyectil disparo = jugador.disparar();
				if(disparo != null) {
					GUI.disparo(disparo);
					juego.getHilo().getProyectiles().add(disparo);
				}
				break;
			}
			}
			GUI.repintar();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

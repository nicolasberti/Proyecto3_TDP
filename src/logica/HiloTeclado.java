package logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import entidades.Jugador;
import aplicacion.*;

public class HiloTeclado extends Thread implements KeyListener {

	public JFrame GUI;
	
	public HiloTeclado(JFrame GUI) {
		super();
		this.GUI = GUI;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Juego juego = ((GUI_nueva)GUI).getJuego();
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
		}
		GUI.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

package logica.hilos;

import java.util.ArrayList;
import java.util.List;

import GUI.*;
import entidades.*;
import logica.*;
import logica.visitors.*;

public class HiloMovimiento extends Thread {

	private GUI_juego frame;
	private boolean jugando = false;
	private List<Entidad> entidades;
	private List<Entidad> entidadesAgregar;
	private List<Entidad> entidadesRemover;
	
	public HiloMovimiento(GUI_juego frame) {
		this.frame = frame;
		entidades = new ArrayList<Entidad>();
		entidadesAgregar = new ArrayList<Entidad>();
		entidadesRemover = new ArrayList<Entidad>();
	}
	
	
	public void empezar() {
		jugando = true;
		this.start();
	}
	
	public void run() {
		try {
			int tiempo = 200; // Tiempo del algoritmo en milisegundos.
			while(jugando) {
				HiloMovimiento.sleep(tiempo); 
				List<Entidad> removerEntidades = new ArrayList<Entidad>();
				for(Entidad entidad : entidades) {
					// Movimiento
					if( entidad.moverse() ) { // true sssi se tiene que remover
						removerEntidades.add(entidad);
					}
					// Colisión
					for(Entidad entidadColision : entidades) {
						if(entidad != entidadColision) {
							if(entidad.estaEnElRadio(entidadColision)) {
								Visitor visitorAsignado = visitorAsignado(entidadColision);
								if(visitorAsignado != null)
									entidad.accept( visitorAsignado );
							}
						}
					}
				}
				for(Entidad entidad : entidadesAgregar)
					entidades.add(entidad);
				entidadesAgregar = new ArrayList<Entidad>();
				if(removerEntidades.size() > 0)
					removerEntidades(removerEntidades);
				if(entidadesRemover.size() > 0)
					removerEntidades(entidadesRemover);
				comprobar();
				frame.repintar();
			}
		} catch (InterruptedException e1) { e1.printStackTrace(); }
	} 
	
	
	public List<Entidad> getEntidades() { return entidades; }
	
	
	
	public void add(Entidad entidad) {
		frame.agregarPanel(entidad);
		entidadesAgregar.add(entidad);
	}
	
	public void remover(Entidad entidad) {
		frame.removerPanel(entidad);
		entidadesRemover.add(entidad);
	}
	
	public void perder() {
		Juego juego = Juego.get();
		juego.perder();
		frame.perder();
		jugando = juego.getJugando();
	}
	
	public void comprobar() {
		Juego juego = Juego.get();
		if( hayInfectados() == false) {
			if(juego.getMapa().getNivelActual().getTandaActual() == 1) {
				juego.segundaTanda();
				frame.segundaTanda();
				frame.agregarInfectados(Juego.get().getInfectadosActuales());
				removerPP();
			} else {
				removerPP();
				if(juego.getUltimoNivel()) {
					this.ganar();
				} else {
					frame.pasarNivel();
				}
			}
		}
	}

	// Métodos privados
	
	private void removerPP() { // remueve particulas y proyectiles.
		entidadesRemover = new ArrayList<Entidad>();
		for(Entidad entidad : entidades) {
			String nombre = superClase(entidad.getClass()).substring( superClase(entidad.getClass()).lastIndexOf(".")+1 );
			if( nombre.equals("Particula") || nombre.equals("Proyectil")) {
				entidadesRemover.add(entidad);
			}
		}
		removerEntidades(entidadesRemover);
	}
	
	private void ganar() {
		Juego juego = Juego.get();
		juego.ganar();
		frame.ganar();
		jugando = juego.getJugando();
	}
	
	private boolean hayInfectados() {
		boolean hay = false;
		for(Entidad entidad : entidades) {
			String nombre = superClase(entidad.getClass()).substring( superClase(entidad.getClass()).lastIndexOf(".")+1 );
			if( nombre.equals("Infectado") )
				hay = true;
		}
		return hay;
	}
	
	private void removerEntidades(List<Entidad> c) {
		if(c.size() > 0) {
			for(Entidad entidad : c) {
				entidades.remove(entidad);
				frame.removerPanel(entidad);
			}
		}
		c = new ArrayList<Entidad>();
	}
	
	private Visitor visitorAsignado(Entidad entidad) {
		Visitor visitor = null;
		switch( superClase(entidad.getClass()).substring( superClase(entidad.getClass()).lastIndexOf(".")+1 )) {
			case "Infectado": {
				visitor = new VisitorInfectado( ((Infectado)entidad) );
				break;
			}
			case "Premio": {
				visitor = new VisitorPremio( ((Premio)entidad), frame );
				break;
			}
			case "Particula": {
				visitor = new VisitorParticula( ((Particula)entidad) );
				break;
			}
			case "Proyectil": {
				visitor = new VisitorProyectil( ((Proyectil)entidad), frame );
				break;
			}
		}
		return visitor;
	}
	
	// Retorna la clase más abstracta que herede de Entidad.
	private String superClase(Class clase) {
		if( clase.getSuperclass().toString().endsWith("Entidad"))
			return clase.toString();
		else
			return superClase(clase.getSuperclass());
	}
}

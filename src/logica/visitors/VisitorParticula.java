package logica.visitors;

import entidades.Infectado;
import entidades.Jugador;
import entidades.Particula;
import logica.Juego;
import logica.Visitor;

public class VisitorParticula extends Visitor {

	private Particula particula;
	
	public VisitorParticula(Particula particula) {
		this.particula = particula;
	}
	
	@Override
	public void visit(Infectado infectado) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Jugador jugador) {
		boolean esta = false;
		jugador.setCargaViral( jugador.getCargaViral() + particula.getDanio());
		if(jugador.getCargaViral() >= 100) {
			esta = true;
		}
		if(esta)
			Juego.get().getHilo().perder();
		Juego.get().getHilo().remover(particula);
	}

}

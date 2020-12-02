package logica;

import entidades.Infectado;
import entidades.Jugador;
import entidades.Particula;

public class VisitorParticula extends Visitor {

	private Particula particula;
	
	public VisitorParticula(Particula particula) {
		this.particula = particula;
	}
	
	@Override
	public boolean visit(Infectado infectado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(Jugador jugador) {
		boolean esta = false;
		jugador.setCargaViral( jugador.getCargaViral() + particula.getDanio());
		if(jugador.getCargaViral() >= 100) {
			esta = true;
		}
		return esta;
	}

}

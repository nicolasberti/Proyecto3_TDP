package logica.visitors;

import GUI.GUI_juego;
import entidades.Infectado;
import entidades.Jugador;
import entidades.Premio;
import logica.Juego;
import logica.Visitor;

public class VisitorPremio extends Visitor {

	private Premio premio;
	private GUI_juego frame;
	
	public VisitorPremio(Premio premio, GUI_juego frame) {
		this.premio = premio;
		this.frame = frame;
	}
	@Override
	public void visit(Infectado infectado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Jugador jugador) {
		if(premio.getUsado() == false) {
			Juego.get().getJugador().usarPremio(premio);
			Juego.get().getHilo().remover(premio);
			frame.notificacion("¡Has utilizado un premio!");
		}
	}

}

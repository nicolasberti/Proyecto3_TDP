package logica;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AutoRemove extends Thread {
	protected JLabel label;
	protected JPanel panel;
	protected int segundos;
	
	public AutoRemove( JLabel label, JPanel panel, int segundos ) {
		this.label = label;
		this.panel = panel;
		this.segundos = segundos;
	}
	
	public void run() {
		try {
			Thread.sleep(segundos*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.panel.remove(this.label);
		this.stop();
	}
}
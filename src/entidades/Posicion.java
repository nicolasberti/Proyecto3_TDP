package entidades;

public class Posicion {

	private int x;
	private int y;
	private int r;
	
	public Posicion(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getR() { return r; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setR(int r) { this.r = r; }
	
	public boolean estaEnElRadio(Posicion p) {
		boolean esta = false;
		/*
		 *  Se calcula la distancia entre 2 puntos: si la distancia es menor o igual que el radio de la posición, entonces está en el radio.
		 *  Esto sirve para saber si dos entidades colisionaron.
		 *  
		*/
		double cateto1 = (double) x - p.getX();
		double cateto2 = (double) y - p.getY();
		double hipotenusa = Math.sqrt(cateto1*cateto1 + cateto2*cateto2);
		if(hipotenusa <= (double)r)
			esta = true;
		return esta;
	}
	
}

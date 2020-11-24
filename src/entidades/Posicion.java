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
		Double distance;
		int hitBox;
		boolean inRange = false;
		distance = Math.sqrt(Math.pow(x-p.getX(), 2)+Math.pow(y-p.getY(),2));
		hitBox = p.getR() + r;
		if(hitBox>distance)
			inRange = true;
		return inRange;	
	}
	
}

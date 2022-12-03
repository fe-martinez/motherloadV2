package algo3.motherloadV2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;
import jugador.Posicion;

public class Particulas {
	private double x;
	private double y;
	
	private Posicion vel; 
	private double radio;
	private double duracion = 1.0;
	private double decaimiento;
	
	private Paint color;
	

	public Particulas(double x, double y, Posicion vel, double radio, double tiempoApagado, Paint color) {
		this.x = x;
		this.y = y;
		this.vel = vel;
		this.radio = radio;
		this.decaimiento = 0.016 / tiempoApagado;
		this.color = color;
	}
	
	
	public boolean visible() {
		return this.duracion > 0;
	}
	
	public void update() {
		x += vel.getX();
		y += vel.getY();
		
		duracion -= decaimiento;
	}
	
	public void dibujar(GraphicsContext context) {
		context.setFill(color);
		context.fillOval(x, y, radio, radio);
	}
	
	
}

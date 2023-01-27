package vistas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import jugador.Posicion;

public class TextoFlotante {
	private double x;
	private double y;
	
	private Posicion vel; 
	private double fontSize;
	private double duracion = 1.0;
	private double decaimiento;
	private String texto;
	private Paint color;

	public TextoFlotante(String texto, double x, double y, Posicion vel, double fontSize, double tiempoApagado, Paint color) {
		this.texto = texto;
		this.x = x;
		this.y = y;
		this.vel = vel;
		this.fontSize = fontSize;
		this.decaimiento =  0.016 / tiempoApagado;
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
		context.setFont(Font.font(fontSize));
		context.fillText(texto, x, y);
	}
}

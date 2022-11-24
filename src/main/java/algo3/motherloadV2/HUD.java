package algo3.motherloadV2;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jugador.Jugador;

public class HUD {
	private GraphicsContext context;
	private double screenWidth;
	private double screenHeight;
	private Jugador pj;
	
	public HUD(GraphicsContext context, double screenWidth, double screenHeight, Jugador pj) {
		this.context = context;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.pj = pj;
	}
	
	public void dibujarHUD() {
		double porcentajeHP = (pj.getNave().getHP() / pj.getNave().getMaxHP()) * 100;
		context.setFill(Color.RED);
		context.fillRect(20, 10, 2 * porcentajeHP, 20);
		
		double porcentajeNafta = (pj.getNave().getNivelDeCombustible() / pj.getNave().getCapacidadTanque()) * 100;
		context.setFill(Color.SIENNA);
		context.fillRect(20, 40, 2 * porcentajeNafta, 20);
	}
	
	
}

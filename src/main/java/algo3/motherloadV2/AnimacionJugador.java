package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import jugador.Jugador;


//Necesita un poco de trabajo. Creo que hay opciones mas elegantes.
public class AnimacionJugador {
	public static final int MAX_TICKS = 24;
	public static final int FRAME_1 = MAX_TICKS / 4;
	public static final int FRAME_2 = MAX_TICKS / 2;
	public static final int FRAME_3 = MAX_TICKS / 2 + MAX_TICKS / 4;
	public static final int FRAME_4 = MAX_TICKS;
	
	private List<Image> imagenes;
	private Jugador pj;
	private int ticksActuales;
	private double width;
	private double height;

	public AnimacionJugador(Jugador pj, double grillaPjAncho, double grillaPjAlto) {
		this.imagenes = inicializarImagenes();
		this.pj = pj;
		this.ticksActuales = 0;
		this.width = grillaPjAncho;
		this.height = grillaPjAlto;
	}

	private List<Image> inicializarImagenes() {
		var imagenesJugador = new ArrayList<Image>();
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Jugador1.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Jugador2.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Jugador3.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Jugador4.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Abajo2.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Derecha2.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Izquierda2.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Volando1.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Volando2.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Volando3.png", width, height));
    	imagenesJugador.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Volando4.png", width, height));
		return imagenesJugador;
	}
	
	
	public Image imagenADibujar() {
		ticksActuales++;
		if(pj.getTipoAnimacion() == 4) {
			if(ticksActuales < FRAME_1) {
				return imagenes.get(7);
			} else if(ticksActuales < FRAME_2) {
				return imagenes.get(8);
			} else if(ticksActuales < FRAME_2){
				return imagenes.get(9);
			} else {
				if(ticksActuales > MAX_TICKS) {
					ticksActuales = 0;
				}
				return imagenes.get(10);
			}
		}
		
		if(pj.getTipoAnimacion() == 0) {
			if(ticksActuales < FRAME_1) {
				return imagenes.get(0);
			} else if(ticksActuales < FRAME_2) {
				return imagenes.get(1);
			} else if(ticksActuales < FRAME_2){
				return imagenes.get(2);
			} else {
				if(ticksActuales > MAX_TICKS) {
					ticksActuales = 0;
				}
				return imagenes.get(3);
			}
		}
		
		
		if(ticksActuales > MAX_TICKS) {
			ticksActuales = 0;
		}
		return imagenes.get(pj.getTipoAnimacion() + 3);
	}
	
	
	
	
	
	
}

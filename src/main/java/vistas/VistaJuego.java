package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jugador.Jugador;
import jugador.Posicion;
import jugador.TipoMovimiento;
import terreno.PisoSuperior;
import terreno.Suelo;
import tiendas.EstacionDeReparacion;
import tiendas.EstacionDeServicio;
import tiendas.EstacionDeVenta;
import tiendas.TiendaDeConsumibles;
import tiendas.TiendaDeMejoras;
import tp.CreadorDeImagenes;
import tp.Juego;
import tp.Sonidos;

public class VistaJuego {
	public static double WIDTH = 1024;
	public static double HEIGHT = 768;

	public static final double GRILLA_ANCHO = 80;
	public static final double GRILLA_ALTO = 80;
	
	public static final double GRILLA_PJ_ANCHO = 64;
	public static final double GRILLA_PJ_ALTO = 64;
	
	public static double COLUMNAS = 64;
	
	public static final double MID_X = WIDTH / 2;
	public static final double MID_Y = HEIGHT / 2;
	
    	private static char ID_TIERRA = 'T';
    	private static char ID_MINADO = 'M';
    	private static char ID_BRONCE = 'B';
    	private static char ID_HIERRO = 'H';
    	private static char ID_PLATA = 'P';
    	private static char ID_ORO = 'O';
    	private static char ID_PASTO = 'G';
    	private static char ID_DIAMANTE = 'D';
    	private static char ID_COBRE = 'C';
	private static char ID_FINAL = 'F';
	private static int ANIMACION_TALADRANDO_1 = 1;
	private static int ANIMACION_TALADRANDO_2 = 2;
	private static int ANIMACION_TALADRANDO_3 = 3;
	private static int ANIMACION_VOLANDO = 4;
		
    
	private List<Particulas> particulas = new ArrayList<>();
	private List<TextoFlotante> textoFlotante = new ArrayList<>();
	private VistaInventario vistaInventario;
	private HUD hud;
	private Juego juego;
	private ArrayList<Image> imagenes;
	GraphicsContext context;
	AnimacionJugador imagenesPJ;
	private Map<Character, Image> imagenesTerreno;
	private TextoFlotante texto;
	private Group root;
	
	public VistaJuego(GraphicsContext context, Juego juego, Group root, Stage stage, double filas) {
		this.context = context;
		this.juego = juego;
		WIDTH = stage.getWidth();
		HEIGHT = stage.getHeight();
		this.root = root;
        imagenes = cargarImagenes();
        imagenesTerreno = cargarImagenesTerreno();
        imagenesPJ = new AnimacionJugador(juego.getJugador(), GRILLA_PJ_ANCHO, GRILLA_PJ_ALTO);
        hud = new HUD(root, context, WIDTH, juego.getJugador(), juego.getGuardarPartida(), stage);
        AnimacionJugador imagenesPJ = new AnimacionJugador(juego.getJugador(), 48, 48);
        this.imagenesPJ = imagenesPJ;
        this.vistaInventario = new VistaInventario(root, juego.getJugador(), stage.getWidth(), stage.getHeight());
	}
	
	public void dibujar() {
    	context.clearRect(0, 0, WIDTH, HEIGHT);
    	dibujarFondo(context, imagenes, juego.getJugador());
    	dibujarTerreno(context, juego.getSuelo(), juego.getPisoSuperior(), imagenesTerreno, imagenes, (int)juego.getJugador().getX(), (int)juego.getJugador().getY());
    	dibujarJugador(context, imagenesPJ, juego.getJugador());
    	
    	
    	for(Iterator<Particulas> iterador = particulas.iterator(); iterador.hasNext(); ) {
    		Particulas particula1 = iterador.next();
    		particula1.update();
    		if(!particula1.visible()) {
    			iterador.remove();
    			continue;
    		}
    		
    		particula1.dibujar(context);
    	}
    	
    	for(Iterator<TextoFlotante> iteradorTexto = textoFlotante.iterator(); iteradorTexto.hasNext(); ) {
    		TextoFlotante text = iteradorTexto.next();
    		text.update();
    		if(!text.visible()) {
    			iteradorTexto.remove();
    			continue;
    		}
    		
    		text.dibujar(context);
    	}
    	
    	
    	
    	hud.dibujarHUD();
    	vistaInventario.dibujarBotonInventario(context);
    }
	
	private static void dibujarFondo(GraphicsContext context, ArrayList<Image> imagenes, Jugador pj) {
		double playerScreenX = (WIDTH/2) - Math.round(pj.getX()) - (GRILLA_ANCHO/2);
		double backgroundX = 2 *playerScreenX * (WIDTH - 3000) /(WIDTH);
		context.drawImage(imagenes.get(0), backgroundX, -HEIGHT * 1.3);
	}
    
	private void dibujarJugador(GraphicsContext context, AnimacionJugador imagenes,  Jugador jugador) {
		context.drawImage(imagenes.imagenADibujar(), ((WIDTH/2)) - (GRILLA_PJ_ANCHO/2), (HEIGHT/2));
		if(jugador.getTipoAnimacion() == ANIMACION_TALADRANDO_1) {
			particulas.addAll(dibujarParticulasTierra(jugador, (WIDTH/2), (HEIGHT/2) + 56, 0));
			Sonidos.reproducir("Taladro");
		} else if(jugador.getTipoAnimacion() == ANIMACION_TALADRANDO_2) {
			particulas.addAll(dibujarParticulasTierra(jugador, (WIDTH/2) + 32, (HEIGHT/2) + 32, 0.5));
			Sonidos.reproducir("Taladro");
		} else if(jugador.getTipoAnimacion() == ANIMACION_TALADRANDO_3) {
			particulas.addAll(dibujarParticulasTierra(jugador, (WIDTH/2) - 32, (HEIGHT/2) + 32, -0.5));
			Sonidos.reproducir("Taladro");
		} else {
			Sonidos.stopReproduccion("Taladro");
		}
		
		
		if(jugador.getTipoAnimacion() == ANIMACION_VOLANDO) {
			Sonidos.reproducir("Helicoptero");
		} else {
			Sonidos.stopReproduccion("Helicoptero");
		}
		
	
		if(jugador.getOrientacion() == TipoMovimiento.DERECHA) {
			particulas.addAll(dibujarParticulasHumo(jugador, WIDTH/2 - GRILLA_PJ_ANCHO/2, HEIGHT/2 + GRILLA_PJ_ALTO/2 - 10, -2));
		} else {
			particulas.addAll(dibujarParticulasHumo(jugador, WIDTH/2 + GRILLA_PJ_ANCHO/4, HEIGHT/2 + GRILLA_PJ_ALTO/2 - 10, 2));
		}
		
		for(String actual: juego.getJugador().getNotificaciones()) {
			if(actual.charAt(0) == '-') {
				Sonidos.reproducir("Golpe");
			}
		}
		
		var notificacion = dibujarNotificaciones(juego.getJugador(),  WIDTH/2 - GRILLA_PJ_ANCHO/2, HEIGHT/2 + GRILLA_PJ_ALTO/2);
		if(notificacion != null) {
			textoFlotante.add(notificacion);			
		}
	}
	
	public static void dibujarTerreno(GraphicsContext context, Suelo suelo, PisoSuperior tiendas, Map<Character, Image> imagenesTerreno, ArrayList<Image> imagenes,double pjX, double pjY) {
		double playerScreenX = (WIDTH/2) - (GRILLA_ANCHO/2);
		double playerScreenY = (HEIGHT/2) - (GRILLA_ALTO/2);
		for(double i = 0; i < suelo.getAlto(); i++) {
			for(double j = 0; j < COLUMNAS; j++) {
				double objWorldX = j * GRILLA_ANCHO;
				double objWorldY = i * GRILLA_ALTO;
				double screenX = objWorldX - (pjX * GRILLA_ANCHO) + playerScreenX;
				double screenY = objWorldY - (pjY * GRILLA_ALTO) + playerScreenY;
				
				if (	objWorldX + GRILLA_ANCHO > (pjX * GRILLA_ANCHO) - playerScreenX &&
						objWorldX - GRILLA_ANCHO < (pjX * GRILLA_ANCHO) + playerScreenX &&
						objWorldY + GRILLA_ALTO > (pjY * GRILLA_ALTO) - playerScreenY &&
						objWorldY - GRILLA_ALTO < (pjY * GRILLA_ALTO) + playerScreenY) {
						
					if(i == 8 && tiendas.getTiendaPos((int) j) != null) {
						dibujarTienda(context, tiendas, imagenes, screenX, screenY, (int)j);
	    			} else {
	    				context.drawImage(tipoImagen(suelo, imagenesTerreno, j, i), screenX, screenY);
	    			}
				}
			}
		}
		
	}
	
	private static void dibujarTienda(GraphicsContext context, PisoSuperior tiendas, ArrayList<Image> imagenes, double screenX, double screenY, int posX) {
		if(tiendas.getTiendaPos(posX) instanceof EstacionDeServicio) {
			context.drawImage(imagenes.get(3), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof EstacionDeVenta) {
			context.drawImage(imagenes.get(4), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof EstacionDeReparacion) {
			context.drawImage(imagenes.get(5), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof TiendaDeMejoras) {
			context.drawImage(imagenes.get(6), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof TiendaDeConsumibles){
			context.drawImage(imagenes.get(7), screenX, screenY - 48);
		}
	}
	
	
    private static Image tipoImagen(Suelo suelo, Map<Character, Image> imagenesTerreno,double x, double y) {
    	var bloqueID = suelo.getBloque(new Posicion((int)x, (int)y)).getBloqueID();
    	if(bloqueID == ID_TIERRA && y == 9) {
    		bloqueID = ID_PASTO;
    	} else if(bloqueID == ' ' && y < 9) {
    		return null;
    	} else if(bloqueID == ' ' && y > suelo.getAlto() - 15) {
    		bloqueID = ID_FINAL;
    	} else if(bloqueID == ' ' && y >= 9) {
    		bloqueID = ID_MINADO;
    	} 
    	var imagen = imagenesTerreno.get(bloqueID);
    	if(imagen == null) {
    		imagen = imagenesTerreno.get('T');
    	}
    	return imagen;
    }
        
    private Map<Character, Image> cargarImagenesTerreno() {
    	var mapImagenes = new HashMap<Character, Image>();
    	mapImagenes.put(ID_TIERRA, CreadorDeImagenes.obtenerImagen("src/rsc/Tierra.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_MINADO, CreadorDeImagenes.obtenerImagen("src/rsc/Minado.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_BRONCE, CreadorDeImagenes.obtenerImagen("src/rsc/Bronce.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_HIERRO, CreadorDeImagenes.obtenerImagen("src/rsc/Hierro.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_PLATA, CreadorDeImagenes.obtenerImagen("src/rsc/Plata.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_ORO, CreadorDeImagenes.obtenerImagen("src/rsc/Oro.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_PASTO, CreadorDeImagenes.obtenerImagen("src/rsc/Pasto.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_DIAMANTE, CreadorDeImagenes.obtenerImagen("src/rsc/Diamante.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_COBRE, CreadorDeImagenes.obtenerImagen("src/rsc/Cobre.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put(ID_FINAL, CreadorDeImagenes.obtenerImagen("src/rsc/FondoFinal.png", GRILLA_ANCHO, GRILLA_ALTO));
    	return mapImagenes;
    }
    
    
    private ArrayList<Image> cargarImagenes(){
    	var imagenes = new ArrayList<Image>();
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Background2.png", 3000, 2000));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Tienda.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Minado.png", GRILLA_ANCHO, GRILLA_ALTO));
		
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Shops/Fuel128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Shops/Venta128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Shops/Repair128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Shops/Upgrade128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Shops/Consumibles128.png", 128, 128));
    	return imagenes;
    }
      
    private List<Particulas> dibujarParticulasTierra(Jugador pj, double posStartX, double posStartY, double sentido) {
    	var particulas = new ArrayList<Particulas>();
    	for(int i = 0; i < 2; i++) {
    		Particulas p = new Particulas(posStartX, posStartY, new Posicion((Math.random() - 0.5 + sentido) * 2, Math.random()), Math.random() * 10, 0.5, Color.rgb(74, 48, 35));
    		particulas.add(p);
    	}
    	return particulas;
    }
    
    private List<Particulas> dibujarParticulasHumo(Jugador pj, double posStartX, double posStartY, double sentido) {
    	ArrayList<Particulas> particulas = new ArrayList<Particulas>();
    	Particulas particula = new Particulas(posStartX, posStartY, new Posicion((Math.random()) * sentido, Math.random() * -0.5), Math.random() * 30, 0.3, Color.rgb(224, 224, 224, 0.2));
    	particulas.add(particula);
    	return particulas;
    }
    
    private TextoFlotante dibujarNotificaciones(Jugador pj, double posX, double posY) {
    	if(!pj.getNotificaciones().isEmpty()) {
    		texto = new TextoFlotante(pj.getNotificaciones().get(0), posX, posY, new Posicion(0.1, -0.5), 20, 2, Color.rgb(255, 255, 255));
    		textoFlotante.add(texto);
    		pj.getNotificaciones().remove(0);
    		return texto;
    	}
    	return null;
    }
    
    public void avisoInicio() {
    	VBox organizador = new VBox();
    	organizador.setAlignment(Pos.CENTER);
    	organizador.setSpacing(10);
    	organizador.setMaxSize(800, 600);
    	organizador.setPrefSize(800, 600);
    	organizador.setLayoutX(WIDTH/2 - 400);
    	organizador.setLayoutY(HEIGHT/2 - 300);
    	Label texto1 = new Label("Para encontrar la rosa, deberas llegar a lo mas profundo de este mapa");
    	texto1.setFont(Font.font(20));
    	Label texto2 = new Label("Para cumplir la tarea se te brindara una poderosa maquina minera");
    	texto2.setFont(Font.font(20));
    	Label texto3 = new Label("Obtene minerales, para luego venderlos y con el dinero comprar\n\tmejoras para durar mas tiempo en las minas");
    	texto3.setFont(Font.font(20));
    	Label texto4 = new Label("Recorda cargar combustible y reparar tu nave en las tiendas para no morir!");
    	texto4.setFont(Font.font(20));
    	Label texto5 = new Label("Mucha suerte");
    	texto5.setFont(Font.font(20));
    	List<KeyCode> controles = juego.teclasActuales();
    	Label texto6 = new Label("Para moverte, usa las teclas:\n" + "Arriba: " + controles.get(0) + "\nAbajo: " + controles.get(1) + "\nDerecha: " + controles.get(2) + "\nIzquierda: " + controles.get(3));
    	texto6.setFont(Font.font(20));
    	Button botonOK = new Button("Entendido");
    	botonOK.setOnAction(e -> this.root.getChildren().remove(this.root.getChildren().size() - 1));

    	organizador.setBackground(Background.fill(Color.rgb(200, 200, 200, 0.7)));
    	organizador.getChildren().addAll(texto1, texto2, texto3, texto4, texto5, texto6);
    	organizador.getChildren().add(botonOK);
    	this.root.getChildren().add(organizador);
    }
    

}

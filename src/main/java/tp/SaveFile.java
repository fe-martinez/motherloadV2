package tp;

import java.io.Serializable;
import java.util.ArrayList;

import jugador.Inventario;
import jugador.Jugador;
import jugador.Posicion;
import mejoras.Usable;
import minerales.Mineral;
import terreno.Suelo;

//La idea es evitar que todas las clases del juego deban implementar Serializable.
public class SaveFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7456931393272320988L;
	//Stats de jugador
	
	double posX;
	double posY;
	int dinero;
	double nivelCombustible;
	double capacidadTanque;
	int nivelHP;
	int maxHP;
	
	//Inventario
	
	ArrayList<Character> minerales;
	ArrayList<Character> usables;
	
	//Mapa
	
	int altoMapa;
	int anchoMapa;	
	char[][] mapa;
	
	public SaveFile() {
	}
	
	public boolean guardarStats(Jugador pj) {
		this.posX = pj.getX();
		this.posY = pj.getY();
		this.dinero = pj.getDinero();
		this.nivelCombustible = pj.getNave().getNivelDeCombustible();
		this.capacidadTanque = pj.getNave().getCapacidadTanque();
		this.nivelHP = pj.getNave().getHP();
		this.maxHP = pj.getNave().getMaxHP();
		return true;
	}
	
	public boolean guardarInventario(Inventario inventario) {
		var minerales = new ArrayList<Character>();
		for(Mineral mineral: inventario.getMinerales()) {
			minerales.add(mineral.getBloqueID());
		}
		this.minerales = minerales;
		
		var usables = new ArrayList<Character>();
		for(Usable usable: inventario.getUsables()) {
			usables.add(usable.getMejoraID());
		}
		this.usables = usables;
		return true;
	}
	
	public boolean guardarMapa(Suelo suelo) {
		this.altoMapa = suelo.getAlto();
		this.anchoMapa = suelo.getAncho();
		var mapaChar = new char[this.altoMapa][this.anchoMapa];
		
		for(int i = 0; i < this.altoMapa; i++) {
			for(int j = 0; j < this.anchoMapa; j++) {
				mapaChar[i][j] = suelo.getBloque(new Posicion(j, i)).getBloqueID();
			}
		}
		
		this.mapa = mapaChar;
		return true;
	}
	
	public boolean cargarStats(SaveFile save, Jugador pj) {
		pj.setX(save.posX);
		pj.setY(save.posY);
		pj.setDinero(save.dinero);
		pj.getNave().setNivelDeCombustible(save.nivelCombustible);
		pj.getNave().setCapacidadDelTanque(save.capacidadTanque);
		pj.getNave().setHP(save.nivelHP);
		pj.getNave().setMaxHP(save.maxHP);
		return true;
	}
	
	public boolean cargarInventario(SaveFile save, Jugador pj) {		
		return true;
	}
	
	public boolean cargarMapa(SaveFile save, Suelo suelo) {
		suelo.cargarCharMap(save.altoMapa, save.anchoMapa, save.mapa);
		return false;
	}
	
	
	
	
	
	
	
	
}

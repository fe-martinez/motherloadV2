package tp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jugador.Jugador;
import terreno.Suelo;

public class GuardarPartida {
	private Jugador pj;
	private Suelo suelo;
	ObjectOutputStream output;
	ObjectInputStream input;
	SaveFile saveFile;
	
	public GuardarPartida(Jugador pj, Suelo suelo) {
		this.pj = pj;
		this.suelo = suelo;
	}
	
	public boolean guardarPartida() {
		saveFile = new SaveFile();
		
		saveFile.guardarStats(pj);
		saveFile.guardarInventario(pj.getInventario());
		saveFile.guardarMapa(suelo);
		
		try {
			this.output = new ObjectOutputStream(new FileOutputStream("src/gameData/savegame.dat"));
			output.writeObject(saveFile);
			output.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cargarPartida(Jugador pj, Suelo suelo) {
		
		
		try {
			this.input = new ObjectInputStream(new FileInputStream("src/gameData/savegame.dat"));
			SaveFile save = (SaveFile) input.readObject();
			save.cargarStats(save, pj);
			save.cargarMapa(save, suelo);
			save.cargarInventario(save, pj);
			input.close();
			return true;
		} catch (IOException e) {
			Alertas.showAlerta("Fallo cargar partida");
		} catch (ClassNotFoundException e) {
			Alertas.showAlerta("Fallo cargar partida");
		}
		return false;
	}
	
	
	
	
}

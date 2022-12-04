package tp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.stage.Stage;

public class ConfigJuego {
	private boolean fullScreen;
	private double screenWidth;
	private double screenHeight;
	private int dificultad;

	public ConfigJuego(boolean fullScreen, double screenWidth, double screenHeight, int dificultad) {
		this.fullScreen = fullScreen;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.dificultad = dificultad;
	}

	public void writeConfigFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("../motherloadV2/src/gameData/config.txt"));
			writer.write("fullScreen=" + String.valueOf(this.fullScreen));
			writer.newLine();
			writer.write("screenWidth=" + this.screenWidth);
			writer.newLine();
			writer.write("screenHeight=" + this.screenHeight);
			writer.newLine();
			writer.write("dificultad=" + this.dificultad);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ConfigJuego readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("../motherloadV2/src/gameData/config.txt"));
			Scanner scanner = new Scanner(reader);
			scanner.useDelimiter("=");
			scanner.next();
			var fullScreenString = Boolean.parseBoolean(scanner.nextLine().replace("=", "").replace("\n", ""));  
			scanner.next();
			double screenWidthValue = Double.parseDouble(scanner.nextLine().replace("=", ""));
			scanner.next();
			double screenHeightValue = Double.parseDouble( scanner.nextLine().replace("=", ""));
			scanner.next();
			var dificultad = Integer.parseInt(scanner.nextLine().replace("=", ""));
			return new ConfigJuego(fullScreenString, screenWidthValue, screenHeightValue, dificultad);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public int getDificultad() {
		return dificultad;
	}
	
	
	
	
	
	
	
	
}




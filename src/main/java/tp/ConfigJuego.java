package tp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ConfigJuego {
	private boolean fullScreen;
	private double screenWidth;
	private double screenHeight;
	private int dificultad;
	private List<String> teclas;

	public ConfigJuego(boolean fullScreen, double screenWidth, double screenHeight, int dificultad, List<String> teclas) {
		this.fullScreen = fullScreen;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.dificultad = dificultad;
		this.teclas = teclas;
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
			writer.newLine();
			writer.write("teclas=" + this.teclas.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ConfigJuego readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("../motherloadV2/src/gameData/config.txt"));
			try (Scanner scanner = new Scanner(reader)) {
				scanner.useDelimiter("=");
				scanner.next();
				var fullScreenString = Boolean.parseBoolean(scanner.nextLine().replace("=", "").replace("\n", ""));  
				scanner.next();
				double screenWidthValue = Double.parseDouble(scanner.nextLine().replace("=", ""));
				scanner.next();
				double screenHeightValue = Double.parseDouble( scanner.nextLine().replace("=", ""));
				scanner.next();
				var dificultad = Integer.parseInt(scanner.nextLine().replace("=", ""));
				List<String> teclas = new ArrayList<String>();
				
				if(scanner.hasNext()) {	
					scanner.next();
					var lineaTeclas = scanner.nextLine();
					var arrayTeclas = lineaTeclas.replace("=", "").replace("[", "").replace("]", "").replace(" ", "").split(",");
					teclas = Arrays.asList(arrayTeclas);
					System.out.print(teclas);
				}
				
				return new ConfigJuego(fullScreenString, screenWidthValue, screenHeightValue, dificultad, teclas);
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
				return null;
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			
			return null;
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
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
	
	public List<String> getTeclas() {
		return this.teclas;
	}
	
	public List<KeyCode> getTeclasKeyCode(){
		return teclas.stream().map(t -> KeyCode.getKeyCode(t)).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
}




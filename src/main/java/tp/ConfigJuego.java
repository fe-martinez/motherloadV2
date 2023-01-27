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

public class ConfigJuego {
	private double screenWidth;
	private double screenHeight;
	private int dificultad;
	private List<String> teclas;
	private double volumen;
	private static String PATH_ARCHIVO_CONFIGS = "src/gameData/config.txt";
	public ConfigJuego(double screenWidth, double screenHeight, int dificultad, double volumen, List<String> teclas) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.dificultad = dificultad;
		this.teclas = teclas;
		this.volumen = volumen;
	}

	//Sobre-escribe el archivo de configuraciones para guardar una nuva configuración.
	public void writeConfigFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_ARCHIVO_CONFIGS));
			writer.write("screenWidth=" + this.screenWidth);
			writer.newLine();
			writer.write("screenHeight=" + this.screenHeight);
			writer.newLine();
			writer.write("dificultad=" + this.dificultad);
			writer.newLine();
			writer.write("volumen=" + this.getVolumen());
			writer.newLine();
			writer.write("teclas=" + this.teclas.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Lee una configuración dejuego en un archivo y devuelve dicha configuración.
	public static ConfigJuego readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH_ARCHIVO_CONFIGS));
			try (Scanner scanner = new Scanner(reader)) {
				scanner.useDelimiter("=");
				scanner.next();
				double screenWidthValue = Double.parseDouble(scanner.nextLine().replace("=", ""));
				scanner.next();
				double screenHeightValue = Double.parseDouble( scanner.nextLine().replace("=", ""));
				scanner.next();				
				var dificultad = Integer.parseInt(scanner.nextLine().replace("=", ""));
				scanner.next();
				double volumenValue = Double.parseDouble(scanner.nextLine().replace("=", ""));
				List<String> teclas = new ArrayList<String>();
				
				if(scanner.hasNext()) {	
					scanner.next();
					var lineaTeclas = scanner.nextLine();
					var arrayTeclas = lineaTeclas.replace("=", "").replace("[", "").replace("]", "").replace(" ", "").split(",");
					teclas = Arrays.asList(arrayTeclas);
				}
				
				return new ConfigJuego(screenWidthValue, screenHeightValue, dificultad, volumenValue, teclas);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} catch (FileNotFoundException e) {
			return null;
		}
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

	public double getVolumen() {
		return volumen;
	}

}




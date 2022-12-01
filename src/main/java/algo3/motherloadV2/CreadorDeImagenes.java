package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class CreadorDeImagenes {
	
	public static Image obtenerImagen(String nombre,double widht, double height) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(nombre), widht, height, true, false);
			return image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}	
}

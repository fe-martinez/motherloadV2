package tp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class CreadorDeImagenes {
	
	//Recibe un path y un tamaño. Crea la imágen con ese nombre y tamaño, con smooth y resizable en true.
	public static Image obtenerImagen(String nombre,double width, double height) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(nombre), width, height, true, true);
			return image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}	
}

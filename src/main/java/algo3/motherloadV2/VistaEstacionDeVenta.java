package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class VistaEstacionDeVenta{
	List<String> valores = List.of("$5","$10","$25","$50","Fill");
	List<String> list = List.of("Hierro","Bronce","Cobre","Oro","Plata","Diamante");
	List<String> listaDePrecios = List.of("30","60","90","120","250","650");
	Stage stage;
	List<Label> labels = new ArrayList<>();
    VBox pane1 = new VBox();
    List<Integer> contador = new ArrayList<>();
    Popup popup = new Popup();

	public VistaEstacionDeVenta(Stage stage, Group root) {
		this.stage = stage;
		this.inicializar();
	}

	public void inicializar() {
		//Cantidad de los distintos minerales inventado por mí xd
		contador.add(1);
	    contador.add(2);
	    contador.add(0);
	    contador.add(3);
	    contador.add(0);
	    contador.add(0);
	    
	    //Recorro la lista de minerales y anoto las cantidades
	    for(int i = 0; i < contador.size(); i++) {
	    	if(contador.get(i) > 0) {
	    		labels.add(new Label(list.get(i) + " " + contador.get(i) + " X " + listaDePrecios.get(i) + " = $" + (Integer.parseInt(listaDePrecios.get(i)) * contador.get(i))));
	    	}
	    }
	    
	    Image img = obtenerImagen("../motherloadV2/src/rsc/EstacionVenta.png",800,600);
	    BackgroundImage backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background background = new Background(backgroundImg);
	    pane1.setBackground(background);
	    pane1.setPrefSize(800,600);
	    
	    for(Label label: labels) {
	    	VBox.setMargin(label,new Insets(0,100,0,100));
	    }
	    VBox.setMargin(labels.get(0),new Insets(100,100,0,100));
	    
	    pane1.getChildren().addAll(labels);
	    
	    popup.getContent().add(pane1);
	    
	    StackPane stackPane = new StackPane();    
	}
	
	private static Image obtenerImagen(String nombre, double width, double height) {
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream(nombre), width, height, true, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image1;
	}
	
	public void mostrar() {
		this.popup.show(this.stage);
	}
}
package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.event.*;
import javafx.geometry.*;

public class VistaEstacionDeServicio  {
	Group root;
	Stage myStage;
	boolean mostrando;

	public VistaEstacionDeServicio(Stage stage, Group root) {
		this.myStage = stage;
		this.root = root;
		this.mostrando = false;
	}
	
	//Todo lo que creo acá tiene que ser atributo de la clase, igual no se ve bien, mi idea era hacer:
	//1 gridpane que tiene un label
	//1 gridpane que tiene los 4 botones del medio digamos
	//1 gridpane que tiene el botón de fill y otro label
	//y meter todo eso en un vbox asi queda todo uno encima del otro xd

	public void inicializar() throws FileNotFoundException {
	    GridPane pane1, pane2, pane3;
	    VBox layout = new VBox();
	    pane1 = new GridPane();
	    pane2 = new GridPane();
	    pane3 = new GridPane();
	    Image img = new Image(new FileInputStream("../motherloadV2/src/rsc/Fuel-512.png"), 800, 600, false, true);
	    BackgroundImage backgroundImg = new BackgroundImage(img, null, null, null, null);
	    Background background = new Background(backgroundImg);
	    //pane2.setBackground(background);
	    Label label1 = new Label("Elija la cantidad de combustible que desea cargar: ");
	    label1.setPrefSize(200,60);
	    label1.setBackground(Background.fill(Paint.valueOf("White")));
	    //label1.setStyle(STYLESHEET_CASPIAN);
	    int variable = 5;
	    Label label2 = new Label("Nivel de combustible: " + variable);
	    label2.setBackground(Background.fill(Paint.valueOf("White")));
	    label2.setPrefSize(500,60);
	    label2.setTextAlignment(TextAlignment.CENTER);
	    Button button5 = new Button("5");
	    button5.setPrefSize(100,60);
	    Button button10 = new Button("10");
	    button10.setPrefSize(100,60);
	    Button button25 = new Button("25");
	    button25.setPrefSize(100,60);
	    Button button50 = new Button("50");
	    button50.setPrefSize(100,60);
	    Button buttonFill = new Button("Fill");
	    buttonFill.setPrefSize(200,60);
	   
	    GridPane.setConstraints(label1,0,0);
	    GridPane.setConstraints(button5,0,0);
	    GridPane.setConstraints(button10,1,0);
	    GridPane.setConstraints(button25,0,1);
	    GridPane.setConstraints(button50,1,1);
	    GridPane.setConstraints(buttonFill,0,0);
	    GridPane.setConstraints(label2, 0, 1);
	   
	    pane1.getChildren().add(label1);
	    pane2.getChildren().addAll(button5,button10,button25,button50);
	    pane3.getChildren().addAll(buttonFill,label2);
	    pane1.setPrefSize(200,100);
	    pane2.setPrefSize(200,100);
	    pane3.setPrefSize(200,100);
	    
	    layout.setAlignment(Pos.CENTER);
	    layout.setSpacing(20);
	    VBox.setMargin(pane1,new Insets(100,100,0,200));
	    VBox.setMargin(pane2,new Insets(0,200,0,200));
	    VBox.setMargin(pane3,new Insets(0,200,100,200));
	    layout.setBackground(background);
	    layout.getChildren().addAll(pane1,pane2,pane3);
	    layout.setLayoutX((VistaJuego.WIDTH - 900) / 2);
    	layout.setLayoutY((VistaJuego.HEIGHT - 600) / 2);
	    
    	root.getChildren().add(layout);
    	this.mostrando = true;
    	
    	button5.setOnAction(e -> root.getChildren().remove(root.getChildren().size() - 1));
    }

    public void mostrar() {
    	//Lo de mostando era porque se apilaban las ventanas mientras el jugador estaba parado encima de la tienda
    	//Hay que encontrar una solucion mejor!
    	if(!this.mostrando) {
	    	try {
				this.inicializar();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

}

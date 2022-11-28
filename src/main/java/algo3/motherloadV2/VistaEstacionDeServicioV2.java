package algo3.motherloadV2;

//Si no anda con los botones, hice esta versión con un choicebox xd


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.event.*;
import javafx.collections.*;
import javafx.geometry.*;

public class VistaEstacionDeServicioV2{
	/*@Override
	public void start(Stage stage) {
	    GridPane pane1 = new GridPane();
	    
	    Image img = new Image("C:\\Users\\Clari\\Downloads\\Fuel-512.png",800,600,true,true);
	    BackgroundImage backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background background = new Background(backgroundImg);
	    pane1.setBackground(background);
	    pane1.setPrefSize(800,600);
	    Label label1,label2,label3;
	    label1 = new Label("Elija la cantidad de combustible que desea cargar: ");
	    label2 = new Label("Precio del combustible: $1/L");
	    int variable1,variable2;
	    variable1 = 5;
	    variable2 = 10;
	    label3 = new Label("Cantidad de combustible actual: " + variable1 + "/" + variable2);
	   
	    
	    ChoiceBox<String> choice = new ChoiceBox();
	    ObservableList<String> list = choice.getItems();
	    list.addAll(valores);
	    
	    label1.setPrefSize(300,10);
	    label1.setTextAlignment(TextAlignment.CENTER);
	    label1.setTextFill(Paint.valueOf("Black"));
	    label2.setPrefSize(300,10);
	    label2.setTextAlignment(TextAlignment.CENTER);
	    label2.setTextFill(Paint.valueOf("Black"));
	    label3.setPrefSize(300,10);
	    label3.setTextAlignment(TextAlignment.CENTER);
	    label3.setTextFill(Paint.valueOf("Black"));
	   
	    
	    GridPane.setConstraints(label1,0,0);
	    GridPane.setConstraints(label2,0,1);
	    GridPane.setConstraints(label3,0,3);
	    GridPane.setConstraints(choice,0,4);
	    
	    
	    
	    pane1.getChildren().addAll(label2,label3,label1,choice);
	    Popup popup = new Popup();
	    popup.getContent().add(pane1);
	   
	    
	    StackPane stackPane = new StackPane();
	    Button button = new Button("Holi");
	    stackPane.getChildren().add(button);
	   */ /*button.setOnAction(new EventHandler<ActionEvent>() {
	   
		@Override
			public void handle(ActionEvent event) {
				popup.show(stage);
		
			}
		});
	    */
	    /*button.setOnAction(e -> {popup.show(stage);});
		    
	    
		     
		   
	   
	    Scene scene = new Scene(stackPane);
	    stage.setScene(scene);
	    stage.setHeight(600);
	    stage.setWidth(1000);
	    stage.show();
	}*/
}



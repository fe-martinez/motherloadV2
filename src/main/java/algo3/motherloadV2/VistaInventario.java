package algo3.motherloadV2;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jugador.Jugador;
import mejoras.TipoUsable;
import mejoras.Usable;
import minerales.Mineral;

public class VistaInventario {
	private Group root;
	private Jugador pj;
	private Label labelHierro;
	private Label labelBronce;
	private Label labelPlata;
	private Labeled labelOro;
	private VBox cajaInventario;
	private Image imagen;
	private double screenWidth;
	private double screenHeight;
	private boolean isShowing;
	private Label labelDinamita;
	private Label labelRepair;
	private Label labelTanque;
	private Label labelTeleport;
	
	public VistaInventario(Group root, Jugador pj, double screenWidth, double screenHeight) {
		this.root = root;
		this.pj = pj;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		cargarImagenes();
		this.isShowing = false;
	}
	
	public void inicializarLabels() {
		var cantidadHierro = 0;
		var cantidadBronce = 0;
		var cantidadPlata = 0;
		var cantidadOro = 0;
		
		for(Mineral actual: pj.getInventario().getMinerales()){
			var actualID = actual.getBloqueID();
			if(actualID == 'H') {
				cantidadHierro++;
			} else if(actualID == 'B') {
				cantidadBronce++;
			} else if(actualID == 'P') {
				cantidadPlata++;
			} else if(actualID == 'O') {
				cantidadOro++;
			}
		}
		
		labelHierro = new Label("Hierro: " + cantidadHierro);
		labelHierro.setFont(Font.font(20));
		labelBronce = new Label("Bronce: " + cantidadBronce);
		labelBronce.setFont(Font.font(20));
		labelPlata = new Label("Plata: " + cantidadPlata);
		labelPlata.setFont(Font.font(20));
		labelOro = new Label("Oro: " + cantidadOro);
		labelOro.setFont(Font.font(20));
	}
	
	public void inicializarLabelsItems() {
		var cantidadDinamita = 0;
		var cantidadRepair = 0;
		var cantidadTP = 0;
		var cantidadTanque = 0;
		
		for(Usable actual: pj.getInventario().getUsables()) {
			var actualID = actual.getMejoraID();
			if(actualID == 'X') {
				cantidadDinamita++;
			} else if(actualID == 'F') {
				cantidadTanque++;
			} else if(actualID == 'Q') {
				cantidadTP++;
			} else if(actualID == 'R') {
				cantidadRepair++;
			}
		}
		
		labelDinamita = new Label("Dinamita: " + cantidadDinamita);
		labelDinamita.setFont(Font.font(20));
		
		labelRepair = new Label("HullRepairNanobots: " + cantidadRepair);
		labelRepair.setFont(Font.font(20));
		
		labelTanque = new Label("Tanque extra: " + cantidadTanque);
		labelTanque.setFont(Font.font(20));
		
		labelTeleport = new Label("Teleport: " + cantidadTP);
		labelTeleport.setFont(Font.font(20));
	}
	
	
	public void checkInteraccionInventario(MouseEvent e) {
		var x = e.getSceneX();
		var y = e.getSceneY();
		
		if(x >= this.screenWidth - 90 && x <= screenWidth - 90 + 64 && y >= 70 && y <= 70+64) {
			if(!isShowing) {
				dibujarInventario();
				isShowing = true;
			}
		}
	}
	
	public void dibujarInventario() {
		inicializarLabels();
		inicializarLabelsItems();
		cajaInventario = new VBox();
		cajaInventario.getChildren().addAll(labelHierro, labelBronce, labelPlata, labelOro, labelDinamita, labelRepair, labelTanque, labelTeleport);
		cajaInventario.setSpacing(25);
		cajaInventario.setPrefSize(500, 600);
		cajaInventario.setBackground(Background.fill(Color.rgb(200, 200, 200, 0.5)));
		cajaInventario.setLayoutX(screenWidth/2 - 250);
		cajaInventario.setLayoutY(screenHeight/2 - 300);
		
		Button ok = new Button("Ok");
		ok.setOnAction(e -> {root.getChildren().remove(root.getChildren().size() - 1); isShowing = false;});
		cajaInventario.getChildren().add(ok);
		root.getChildren().add(cajaInventario);
	}
	
	public void dibujarBotonInventario(GraphicsContext context) {
		context.drawImage(imagen, screenWidth - 90, 70);
	}
	
	private void cargarImagenes(){
		imagen = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Inventario/inventario2.png", 64, 64);
	}
	
	
	
	
}

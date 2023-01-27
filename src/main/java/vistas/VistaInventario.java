package vistas;

import javafx.geometry.Pos;
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
import tp.CreadorDeImagenes;

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
	private Label labelTituloMinerales;
	private Label labelTanque;
	private Label labelRepair;
	private Label labelTeleport;
	private Label labelDinamita;
	private Label labelTituloUsables;
	private Label labelDiamante;
	private Label labelCobre;
	private Label labelExplosivos;
	
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
		var cantidadDiamante = 0;
		var cantidadCobre = 0;
		
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
			} else if(actualID == 'D') {
				cantidadDiamante++;
			} else if(actualID == 'C') {
				cantidadCobre++;
			}
		}
		
		labelTituloMinerales = new Label("Minerales guardados " + pj.getInventario().getCantidadDeMinerales() + "/" + pj.getInventario().getMaxInventario());
		labelTituloMinerales.setFont(Font.font(30));
		labelCobre = new Label("Cobre: " + cantidadCobre);
		labelCobre.setFont(Font.font(20));
		labelHierro = new Label("Hierro: " + cantidadHierro);
		labelHierro.setFont(Font.font(20));
		labelBronce = new Label("Bronce: " + cantidadBronce);
		labelBronce.setFont(Font.font(20));
		labelPlata = new Label("Plata: " + cantidadPlata);
		labelPlata.setFont(Font.font(20));
		labelOro = new Label("Oro: " + cantidadOro);
		labelOro.setFont(Font.font(20));
		labelDiamante = new Label("Diamante: " + cantidadDiamante);
		labelDiamante.setFont(Font.font(20));
		
		
		var cantidadDinamita = 0;
		var cantidadRepair = 0;
		var cantidadTeleport = 0;
		var cantidadTanque = 0;
		var cantidadExplosivos = 0;
		
		for(Usable actual: pj.getInventario().getUsables()) {
			if(actual.getTipo() == TipoUsable.DINAMITA) {
				cantidadDinamita++;
			} else if(actual.getTipo() == TipoUsable.TELEPORT) {
				cantidadTeleport++;
			}else if(actual.getTipo() == TipoUsable.REPAIR) {
				cantidadRepair++;	
			}else if(actual.getTipo() == TipoUsable.TANQUE_EXTRA) {
				cantidadTanque++;	
			} else if(actual.getTipo() == TipoUsable.EXPLOSIVOS) {
				cantidadExplosivos++;	
			}
		}
		
		
		labelTituloUsables = new Label("Cantidad de usables");
		labelTituloUsables.setFont(Font.font(30));
		labelDinamita = new Label("Dinamita: " + cantidadDinamita);
		labelDinamita.setFont(Font.font(20));
		labelExplosivos = new Label("Explosivos: " + cantidadExplosivos);
		labelExplosivos.setFont(Font.font(20));
		labelTeleport= new Label("HullRepairNanobots: " + cantidadRepair);
		labelTeleport.setFont(Font.font(20));
		labelRepair = new Label("Teleport: " + cantidadTeleport);
		labelRepair.setFont(Font.font(20));
		labelTanque = new Label("Tanques extra: " + cantidadTanque);
		labelTanque.setFont(Font.font(20));
		
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
		cajaInventario = new VBox();
		cajaInventario.setAlignment(Pos.BOTTOM_CENTER);
		cajaInventario.getChildren().addAll(labelTituloMinerales, labelCobre, labelHierro, labelBronce, labelPlata, labelOro, labelDiamante);
		cajaInventario.getChildren().addAll(labelTituloUsables, labelDinamita, labelExplosivos, labelTeleport, labelRepair, labelTanque);
		cajaInventario.setSpacing(10);
		cajaInventario.setPrefSize(500, 600);
		cajaInventario.setBackground(Background.fill(Color.rgb(200, 200, 200, 0.5)));
		cajaInventario.setLayoutX(screenWidth/2 - 250);
		cajaInventario.setLayoutY(screenHeight/2 - 300);
		
		Button ok = new Button("Ok");
		ok.setOnAction(e -> {root.getChildren().remove(root.getChildren().size() - 1); isShowing = false;});
		ok.setPrefSize(500, 50);
		cajaInventario.getChildren().add(ok);
		root.getChildren().add(cajaInventario);
	}
	
	public void dibujarBotonInventario(GraphicsContext context) {
		context.drawImage(imagen, screenWidth - 90, 70);
	}
	
	private void cargarImagenes(){
		imagen = CreadorDeImagenes.obtenerImagen("src/rsc/Inventario/inventario2.png", 64, 64);
	}
	
}

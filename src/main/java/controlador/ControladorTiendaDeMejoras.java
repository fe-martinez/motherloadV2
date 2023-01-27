package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jugador.Jugador;
import tiendas.TiendaDeMejoras;
import tp.Alertas;
import tp.Sonidos;
import vistas.VistaTiendaDeMejoras;

public class ControladorTiendaDeMejoras {
	TiendaDeMejoras tienda;
	VistaTiendaDeMejoras vista;
	private Jugador pj;
	
	public ControladorTiendaDeMejoras(TiendaDeMejoras tienda, VistaTiendaDeMejoras vista,Jugador pj) {
		this.tienda = tienda;
		this.vista = vista;
		this.pj = pj;
	}
	
	private void informarAlUsuario(boolean vendido){
		if(vendido){
			Alertas.showAlerta("Compra exitosa");
			Sonidos.reproducir("Compra");
		}
		else{
			Alertas.showAlerta("Fallo compra mejoras");
			Sonidos.reproducir("Error");
		}
	}
	
	public void comprar() {
		vista.registrarEscuchaProcesoCompra(e -> 
		{
			if (vista.getMejoraSeleccionada() == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("No ha elegido una mejora");
				a.show();
			}
			else {
				boolean vendido = tienda.interactuar(pj,vista.getMejoraSeleccionada());
				this.informarAlUsuario(vendido);
			}
		});
	}
	
}
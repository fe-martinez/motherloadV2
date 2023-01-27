package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import jugador.Jugador;
import tiendas.TiendaDeConsumibles;
import tp.Alertas;
import tp.Sonidos;
import vistas.VistaTiendaDeConsumibles;

public class ControladorTiendaDeConsumibles {
	TiendaDeConsumibles tienda;
	VistaTiendaDeConsumibles vista;
	private Jugador pj;
	
	public ControladorTiendaDeConsumibles(TiendaDeConsumibles tienda, VistaTiendaDeConsumibles vista,Jugador pj) {
		this.tienda = tienda;
		this.vista = vista;
		this.pj = pj;
	}
	
	private void reproducirSonidoVenta(boolean vendido) {
		if(vendido) {
			Sonidos.reproducir("Comprar");
		} else {
			Sonidos.reproducir("Error");
		}
	}
	
	private void mostrarAlertaVenta(boolean vendido) {
		if(vendido) {
			Alertas.showAlerta("Compra exitosa");
		} else {
			Alertas.showAlerta("Fallo compra");
		}
	}
	
	public void vender() {
		
		vista.registrarEscuchaTanqueExtra(e ->
		{
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(vista.getInstruccion(0));
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				boolean vendido = tienda.interactuar(pj,vista.getMejora(0));	
				reproducirSonidoVenta(vendido);
				mostrarAlertaVenta(vendido);
			}
		});
		
		vista.registrarEscuchaNanobots(e->
		{
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(vista.getInstruccion(1));
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				boolean vendido = tienda.interactuar(pj,vista.getMejora(1));
				reproducirSonidoVenta(vendido);
				mostrarAlertaVenta(vendido);
			}
		});
		
		vista.registrarEscuchaDinamita(e ->
		{
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(vista.getInstruccion(2));
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				boolean vendido = tienda.interactuar(pj,vista.getMejora(2));
				reproducirSonidoVenta(vendido);
				mostrarAlertaVenta(vendido);
			}
		});
		
		vista.registrarEscuchaExplosivos(e ->
		{
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(vista.getInstruccion(3));
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				boolean vendido = tienda.interactuar(pj,vista.getMejora(3));
				reproducirSonidoVenta(vendido);
				mostrarAlertaVenta(vendido);
			}
		});
		
		vista.registrarEscuchaTeleport(e ->
		{
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(vista.getInstruccion(4));
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				boolean vendido = tienda.interactuar(pj,vista.getMejora(4));
				reproducirSonidoVenta(vendido);
				mostrarAlertaVenta(vendido);
			}
		});
	}

	public void cerrarTienda() {
		vista.registrarEscuchaClose(e -> {
			vista.ocultar();
		    Sonidos.stopReproduccion("Tiendas");
	        Sonidos.reproducir("Jugando");
		});
	}
}
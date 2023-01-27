package controlador;

import javafx.scene.control.Label;
import jugador.Jugador;
import tiendas.EstacionDeMantenimiento;
import tiendas.EstacionDeServicio;
import tp.Alertas;
import tp.Sonidos;
import vistas.VistaEstacionDeMantenimiento;

public class ControladorEstacionDeMantenimiento {
	EstacionDeMantenimiento tienda;
	VistaEstacionDeMantenimiento vista;
	private Jugador pj;
	private static int CODIGO_OPCION_FULL = 1000;
	
	public ControladorEstacionDeMantenimiento(EstacionDeMantenimiento tienda, VistaEstacionDeMantenimiento vista,Jugador pj) {
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
		}else {
			Alertas.showAlerta("Fallo compra mantenimiento");
		}
	}
	
	public void actualizarBarra() {
		if(tienda instanceof EstacionDeServicio) {
			String text = (int) pj.getNave().getNivelDeCombustible() + "/" + (int)pj.getNave().getCapacidadTanque();
			double porcentajeNafta = (pj.getNave().getNivelDeCombustible() / pj.getNave().getCapacidadTanque()) * 100;
			vista.actualizarBarra(text, porcentajeNafta);
		} else {
			String text = (int) pj.getNave().getHP() + "/" + (int)pj.getNave().getMaxHP();
			double porcentajeHP = (pj.getNave().getHP() / pj.getNave().getMaxHP()) * 100;
			vista.actualizarBarra(text, porcentajeHP);
		}
	}
	
	public void setLabels() {
		if(tienda instanceof EstacionDeServicio) {
			vista.addLabel(new Label((int) pj.getNave().getNivelDeCombustible() + "/" + (int)pj.getNave().getCapacidadTanque()));
		} else {
			vista.addLabel(new Label((int) pj.getNave().getHP() + "/" + (int)pj.getNave().getMaxHP()));
		}

		vista.reinicializar();
	}
	
	public void vender() {
		
		vista.registrarEscuchaOpcion1(e ->
		{
			boolean vendido = tienda.vender(pj,Integer.parseInt(vista.getKey(0)));
			this.actualizarBarra();
    		this.reproducirSonidoVenta(vendido);
    		this.mostrarAlertaVenta(vendido);
		});
		
		vista.registrarEscuchaOpcion2(e ->
		{
			boolean vendido = tienda.vender(pj,Integer.parseInt(vista.getKey(1)));
			this.actualizarBarra();
			this.reproducirSonidoVenta(vendido);
    		this.mostrarAlertaVenta(vendido);
		});
		
		vista.registrarEscuchaOpcion3(e ->
		{
			boolean vendido = tienda.vender(pj,Integer.parseInt(vista.getKey(2)));
			this.actualizarBarra();
			this.reproducirSonidoVenta(vendido);
    		this.mostrarAlertaVenta(vendido);
		});
		
		vista.registrarEscuchaOpcion4(e ->
		{
			boolean vendido = tienda.vender(pj,Integer.parseInt(vista.getKey(3)));
			this.actualizarBarra();
			this.reproducirSonidoVenta(vendido);
    		this.mostrarAlertaVenta(vendido);
		});
		
		vista.registrarEscuchaOpcion5(e ->
		{
			boolean vendido = tienda.vender(pj,CODIGO_OPCION_FULL);
			this.actualizarBarra();
			this.reproducirSonidoVenta(vendido);
    		this.mostrarAlertaVenta(vendido);
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


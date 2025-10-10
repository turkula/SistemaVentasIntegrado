package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Lote {
	
	private Producto producto;
	private char talle;
	private int cantidadActual;
	private int cantidadInicial;
	private LocalDate fechaIngreso;
	
	
	public Lote(Producto producto, char talle, int cantidadInicial, LocalDate fechaIngreso) {
		super();
		this.producto = producto;
		this.talle = talle;
		this.cantidadActual = cantidadInicial;
		this.cantidadInicial = cantidadInicial;
		this.fechaIngreso = fechaIngreso;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public char getTalle() {
		return talle;
	}


	public void setTalle(char talle) {
		this.talle = talle;
	}


	public int getCantidadActual() {
		return cantidadActual;
	}


	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}


	public int getCantidadInicial() {
		return cantidadInicial;
	}


	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}


	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public float calcularPorcentajeRestante() {
		if (cantidadInicial == 0) return 0;
		return (cantidadActual * 100)/cantidadInicial;
	}
	
	public long calcularDiasDesdeIngreso(LocalDate fechaActual) {
		return ChronoUnit.DAYS.between(fechaIngreso, fechaActual);
	}
	
	public boolean puedeOfertarse(LocalDate fechaActual) {
		if (calcularDiasDesdeIngreso(fechaActual) > 60) {
			return true;
		}return false;
	}
	
	public void reiniciarStock() {
		cantidadActual = cantidadInicial;
	}
	
	public int calcularCantidadVendida() {
		return cantidadInicial - cantidadActual;
	}

	@Override
	public String toString() {
		return "\nLote [producto=" + producto + ", talle=" + talle + ", cantidadActual=" + cantidadActual
				+ ", cantidadInicial=" + cantidadInicial + ", fechaIngreso=" + fechaIngreso + "]";
	}

}

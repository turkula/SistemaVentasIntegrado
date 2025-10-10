package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Lote;
import modelo.Producto;

class LoteTest {
	
	private Lote lote;
	private Producto producto;
	
	@BeforeEach
	void setUp() throws Exception {
		
		producto = mock(Producto.class);
		
		lote = new Lote(producto, 'X',60,LocalDate.of(2025, 10, 1));
	}

	@Test
	void testCalcularPorcentajeRestante() {
		lote.setCantidadActual(30);
		assertEquals(50,lote.calcularPorcentajeRestante(),"El porcentaje obtenido no coincide");
	}

	@Test
	void testCalcularDiasDesdeIngreso() {
		LocalDate fechaActual = LocalDate.of(2025, 10, 10);
		assertEquals(9,lote.calcularDiasDesdeIngreso(fechaActual),"El calculo de días es incorrecto");
	}

	@Test
	void testPuedeOfertarse() {
		LocalDate fechaActual = LocalDate.of(2025, 10, 10);
		assertFalse(lote.puedeOfertarse(fechaActual),"El lote debería poder ofertarse");
	}

	@Test
	void testReiniciarStock() {
		int reinicio = 60;
		lote.reiniciarStock();
		assertEquals(reinicio,lote.getCantidadActual(),"El reinicio setea un valor erroneo");
	}

	@Test
	void testCalcularCantidadVendida() {
		lote.setCantidadActual(30);
		assertEquals(30,lote.calcularCantidadVendida(),"la cantidad vendida no coincide");
	}

}

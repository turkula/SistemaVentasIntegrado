package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Producto;

class ProductoTest {
	
	private Producto producto;

	@BeforeEach
	void setup() {
		producto = new Producto(1, true, "Producto test", 5, LocalDate.now(), "Marca test");
	}


	@Test
	void testDesactivarProducto() {
		producto.desactivarProducto();
		boolean estado = producto.isStatus();
		assertFalse(estado,"El estado del producto debería ser falso");
	}

	@Test
	void testActivarProducto() {
		producto.activarProducto();
		boolean estado = producto.isStatus();
		assertTrue(estado,"El estado del producto debería ser verdadero");
	}

}

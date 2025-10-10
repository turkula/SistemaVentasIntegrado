package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;
import modelo.Sucursal;
import modelo.Vendedor;
import modelo.Venta;

class VentaTest {
	
	private Sucursal sucursal;
	private Producto producto1;
	private Producto producto2;
	private Pedido pedido1;
	private Pedido pedido2;
	private Vendedor vendedor;
	private Cliente cliente;
	private Venta venta;

	@BeforeEach
	void setUp() throws Exception {
		
		sucursal = mock(Sucursal.class);
		
		producto1 = mock(Producto.class);
		when(producto1.getId()).thenReturn(1);
		when(producto1.isStatus()).thenReturn(true);
		when(producto1.getDescripcion()).thenReturn("Producto 1");
		when(producto1.getPrecioUnitario()).thenReturn(10.0f);
		when(producto1.getFechaAlta()).thenReturn(LocalDate.now());
		when(producto1.getMarca()).thenReturn("Producto");
		
		producto2 = mock(Producto.class);
		when(producto2.getId()).thenReturn(2);
		when(producto2.isStatus()).thenReturn(true);
		when(producto2.getDescripcion()).thenReturn("Producto 2");
		when(producto2.getPrecioUnitario()).thenReturn(20.0f);
		when(producto2.getFechaAlta()).thenReturn(LocalDate.of(2024, 10, 9));
		when(producto2.getMarca()).thenReturn("Producto");
		
		vendedor = mock(Vendedor.class);
		when(vendedor.getDni()).thenReturn(11111111L);
		when(vendedor.getNombre()).thenReturn("Vendedor 1 Nombre");
		when(vendedor.getApellido()).thenReturn("Vendedor 1 Apellido");
		when(vendedor.getFechaNacimiento()).thenReturn(LocalDate.of(2001, 7, 31));
		when(vendedor.getHorasPorJornada()).thenReturn(8);
		when(vendedor.getPlus()).thenReturn(100.0f);
		when(vendedor.getSueldoBasico()).thenReturn(500f);
		when(vendedor.getSucursal()).thenReturn(sucursal);
		
		pedido1 = mock(Pedido.class);
		when(pedido1.getCantidad()).thenReturn(2);
		when(pedido1.getProducto()).thenReturn(producto1);
		
		pedido2 = mock(Pedido.class);
		when(pedido2.getCantidad()).thenReturn(1);
		when(pedido2.getProducto()).thenReturn(producto2);
		
		cliente = mock(Cliente.class);
		
		venta = new Venta(1,vendedor,cliente,LocalDate.now(),LocalTime.now());
		venta.getLstPedidos().add(pedido1);
		venta.getLstPedidos().add(pedido2);
	}

	@Test
	void testGenerarPedidoConStockPropio() {
		boolean agregado = venta.generarPedidoConStockPropio(producto1, 2);
		assertTrue(agregado,"El pedido debería haberse agregado");
		verify(vendedor).setPlus(101.0f);
	}

	@Test
	void testCalcularTotal() {
		venta.calcularTotal();
		
		assertEquals(40.0f,venta.precioTotal(),"El calculo total es incorrecto");
	}

}

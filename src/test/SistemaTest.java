package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;
import modelo.Sistema;
import modelo.Vendedor;
import modelo.Venta;

class SistemaTest {
	
	private Sistema sistema;
	private Producto producto1;
	private Producto producto2;
	private Cliente cliente1;
	private Cliente cliente2;
	private Venta venta;
	private Pedido pedido1;
	private Pedido pedido2;

	@BeforeEach
	void setUp() throws Exception {
		sistema = new Sistema();
		
		//Setear productos
		producto1 = mock(Producto.class);
		when(producto1.getId()).thenReturn(1);
		when(producto1.isStatus()).thenReturn(true);
		when(producto1.getDescripcion()).thenReturn("Producto 1");
		when(producto1.getPrecioUnitario()).thenReturn(5.5f);
		when(producto1.getFechaAlta()).thenReturn(LocalDate.now());
		when(producto1.getMarca()).thenReturn("Producto");
		
		producto2 = mock(Producto.class);
		when(producto2.getId()).thenReturn(2);
		when(producto2.isStatus()).thenReturn(true);
		when(producto2.getDescripcion()).thenReturn("Producto 2");
		when(producto2.getPrecioUnitario()).thenReturn(10.5f);
		when(producto2.getFechaAlta()).thenReturn(LocalDate.of(2024, 10, 9));
		when(producto2.getMarca()).thenReturn("Producto");
		
		sistema.getLstProductos().add(producto1);
		sistema.getLstProductos().add(producto2);
		
		//Setear clientes
		cliente1 = mock(Cliente.class);
		when(cliente1.getNombre()).thenReturn("Nombre");
		when(cliente1.getApellido()).thenReturn("Apellido");
		when(cliente1.getFechaNacimiento()).thenReturn(LocalDate.now());
		when(cliente1.getDni()).thenReturn(1111111L);
		when(cliente1.getMail()).thenReturn("Correo");
		
		cliente2 = mock(Cliente.class);
		when(cliente2.getNombre()).thenReturn("Nombre");
		when(cliente2.getApellido()).thenReturn("Apellido");
		when(cliente2.getFechaNacimiento()).thenReturn(LocalDate.now());
		when(cliente2.getDni()).thenReturn(2222222L);
		when(cliente2.getMail()).thenReturn("Correo");
		
		sistema.getLstClientes().add(cliente1);
		sistema.getLstClientes().add(cliente2);
		
		//Setear ventas
		pedido1 = mock(Pedido.class);
		when(pedido1.getCantidad()).thenReturn(2);
		when(pedido1.getProducto()).thenReturn(producto1);
		
		pedido2 = mock(Pedido.class);
		when(pedido2.getCantidad()).thenReturn(3);
		when(pedido2.getProducto()).thenReturn(producto2);
		
		venta = mock(Venta.class);
		when(venta.getLstPedidos()).thenReturn(List.of(pedido1,pedido2));
		
		sistema.getLstVentas().add(venta);
		
		
	}

	@Test
	void testTraerProductoInt() {
		Producto productoResultado = sistema.traerProducto(2);
		
		assertNotNull(productoResultado);
		assertEquals(2,productoResultado.getId());
	}

	@Test
	void testTraerProductoString() {
		Producto productoResultado = sistema.traerProducto("Producto 1");
		
		assertNotNull(productoResultado,"El resultado no debería ser nulo");
		assertEquals("Producto 1",productoResultado.getDescripcion(),"El producto buscado no coincide");
	}

	@Test
	void testAgregarProducto() throws Exception {
		boolean status = true;
		String descripcion = "Producto 3";
		float precioUnitario = 2.45f;
		LocalDate fechaAlta = LocalDate.now();
		String marca = "Producto";
		
		boolean agregado = sistema.agregarProducto(status, descripcion, precioUnitario, fechaAlta, marca);
		
		assertTrue(agregado,"El producto no fue agregado correctamente");
		assertThrows(Exception.class, () -> {
			sistema.agregarProducto(status, descripcion, precioUnitario, fechaAlta, marca);
		},"No debería permitir agregar el producto");
	}

	@Test
	void testEliminarProducto() throws Exception {
		boolean removido = sistema.eliminarProducto("Producto 1");
		
		assertTrue(removido,"el sistema no removió el producto");
		assertThrows(Exception.class, () -> {
			sistema.eliminarProducto("Producto 1");
		},"El sistema debería advertir que no existe el producto a eliminar");
		
	}

	@Test
	void testModificarProducto() throws Exception {
		
		sistema.modificarProducto(false, "Producto 2", 2.45f,LocalDate.of(2024, 10, 9), "Fantasma");
		
		Producto productoModificado = sistema.traerProducto("Producto 2");
		assertFalse(productoModificado.isStatus(),"El estado no se ha modificado");
		assertEquals(2.45f,productoModificado.getPrecioUnitario(),"El precio no se ha modificado");
		assertEquals(LocalDate.of(2024, 10, 9),productoModificado.getFechaAlta(),"La fecha de alta no se ha modificado");
		assertEquals("Fantasma",productoModificado.getMarca(),"La marca no se ha modificado");
		
	}

	@Test
	void testTraerCliente() {
		Cliente clienteResultado = sistema.traerCliente(1111111L);
		
		assertNotNull(clienteResultado,"El resultado no debería ser nulo");
		assertEquals(1111111L,clienteResultado.getDni(),"El dni del cliente traido no corresponde");
	}

	@Test
	void testCalcularCantidadProducto() {
		int cantidad = sistema.calcularCantidadProducto(producto1);
		assertEquals(2,cantidad,"La cantidad calculada es incorrecta");
		
	}

}

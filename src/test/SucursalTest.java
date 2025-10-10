package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Direccion;
import modelo.Gerente;
import modelo.Lote;
import modelo.Producto;
import modelo.Sucursal;
import modelo.Vendedor;

class SucursalTest {
	
	private Sucursal sucursal1;
	private Sucursal sucursal2;
	private Vendedor vendedor1;
	private Vendedor vendedor2;
	private Producto producto1;
	private Direccion direccion1;
	private Direccion direccion2;
	private Lote lote;
	

	@BeforeEach
	void setUp() throws Exception {
		
		direccion1 = mock(Direccion.class);
		when(direccion1.getLatitud()).thenReturn(0.0f);
		when(direccion1.getLongitud()).thenReturn(0.0f);
		
		direccion2 = mock(Direccion.class);
		when(direccion2.getLatitud()).thenReturn(3.0f);
		when(direccion2.getLongitud()).thenReturn(4.0f);
		
		Gerente gerente = mock(Gerente.class);
		sucursal1 = new Sucursal(direccion1,1,1111111111L,gerente);
		sucursal2 = new Sucursal(direccion2,2,2222222222L,gerente);
		
		vendedor1 = mock(Vendedor.class);
		when(vendedor1.getDni()).thenReturn(11111111L);
		when(vendedor1.getNombre()).thenReturn("Vendedor 1 Nombre");
		when(vendedor1.getApellido()).thenReturn("Vendedor 1 Apellido");
		when(vendedor1.getFechaNacimiento()).thenReturn(LocalDate.of(2001, 7, 31));
		when(vendedor1.getHorasPorJornada()).thenReturn(8);
		when(vendedor1.getPlus()).thenReturn(5.5f);
		when(vendedor1.getSueldoBasico()).thenReturn(500f);
		when(vendedor1.getSucursal()).thenReturn(sucursal1);
		
		vendedor2 = mock(Vendedor.class);
		when(vendedor2.getDni()).thenReturn(22222222L);
		when(vendedor2.getNombre()).thenReturn("Vendedor 1");
		
		sucursal1.getLstVendedores().add(vendedor1);
		sucursal1.getLstVendedores().add(vendedor2);
		
		producto1 = mock(Producto.class);
		when(producto1.getId()).thenReturn(1);
		when(producto1.isStatus()).thenReturn(true);
		when(producto1.getDescripcion()).thenReturn("Producto 1");
		when(producto1.getPrecioUnitario()).thenReturn(5.5f);
		when(producto1.getFechaAlta()).thenReturn(LocalDate.now());
		when(producto1.getMarca()).thenReturn("Producto");
		
		lote = mock(Lote.class);
		when(lote.getProducto()).thenReturn(producto1);
		when(lote.getCantidadActual()).thenReturn(8);
		when(lote.getCantidadInicial()).thenReturn(10);
		
		sucursal1.getLstLotes().add(lote);
	}

	@Test
	void testTraerVendedor() {
		Vendedor vendedorEncontrado = sucursal1.traerVendedor(11111111L);
		assertNotNull(vendedorEncontrado,"No se encontró ningún vendedor");
		assertEquals(11111111L,vendedorEncontrado.getDni(),"El vendedor traido debería tener el DNI 11 111 111");
	}

	@Test
	void testModificarVendedor() throws Exception {
		
		sucursal1.modificarVendedor("Juan","Perez",LocalDate.now(),11111111L,5,200f,2.5f);
		Vendedor vendedorModificado = sucursal1.traerVendedor(11111111L);
		
		assertEquals("Juan",vendedorModificado.getNombre(),"El nombre no se ha modificado");
		assertEquals("Perez",vendedorModificado.getApellido(),"El apellido no se ha modificado");
		assertEquals(LocalDate.now(),vendedorModificado.getFechaNacimiento(),"La fecha de nacimiento no se ha modificado");
		assertEquals(5,vendedorModificado.getHorasPorJornada(),"La cantidad de horas por jornada no se ha modificado");
		assertEquals(200f,vendedorModificado.getSueldoBasico(),"El sueldo básico no se ha modificado");
		assertEquals(2.5f,vendedorModificado.getPlus(),"El plus no se ha modificado");
	}

	@Test
	void testEliminarVendedor() throws Exception {
		boolean removido = sucursal1.eliminarVendedor(33333333L);
		assertFalse(removido,"El vendedor no se ha eliminado");
	}

	@Test
	void testTraerCantidad() {
		int cantidad = sucursal1.traerCantidad(producto1);
		assertEquals(8,cantidad,"La cantidad es incorrecta");		
	}

	@Test
	void testCalcularDistancia() {
		float distancia = sucursal1.calcularDistancia(sucursal2);
		assertEquals(5.0f,distancia,"La distancia calculada es incorrecta");
	}

}

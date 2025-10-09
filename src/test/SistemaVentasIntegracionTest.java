package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Cliente;
import modelo.Direccion;
import modelo.Gerente;
import modelo.Producto;
import modelo.Sistema;
import modelo.Sucursal;
import modelo.Vendedor;
import modelo.Lote;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaVentasIntegracionTest {

    private Sistema sistema;
    private Vendedor vendedor;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        sistema = new Sistema();

        cliente = new Cliente("María", "González", LocalDate.of(1990, 1, 1), 12345678L, "maria@test.com");

        Direccion direccion = new Direccion("Remedios de Escalada", "29 de Septiembre", 123, 0.0f, 0.0f);
        Gerente gerente = new Gerente("Laura", "Pérez", LocalDate.of(1985, 5, 20), 22333444L, 40, 80000f);
        Sucursal sucursal = new Sucursal(direccion, 1, 1144445555L, gerente);

        vendedor = new Vendedor("Juan", "López", LocalDate.of(1995, 10, 5), 33444555L, 40, 50000f, sucursal);
        sucursal.getLstVendedores().add(vendedor);

        sistema.getLstClientes().add(cliente);
        sistema.getLstSucursales().add(sucursal);
    }

    // 1. Inicialización del sistema
    @Test
    void test01SistemaInicializado() {
        assertNotNull(sistema);
        assertNotNull(cliente);
        assertNotNull(vendedor);
        Sucursal s = sistema.getLstSucursales().get(0);
        assertNotNull(s);
        assertNotNull(s.getGerente());
        assertTrue(s.getLstVendedores().contains(vendedor));
    }

    // 2. Cliente y producto compatibles
    @Test
    void test02ClienteYProductoCompatibles() throws Exception {
        sistema.agregarProducto(true, "Laptop Test", 50000f, LocalDate.of(2023, 1, 15), "Test");
        Producto p = sistema.traerProducto("Laptop Test");
        assertNotNull(p);
        assertTrue(cliente.getNombre().length() > 0);
        assertTrue(p.getDescripcion().length() > 0);
        assertTrue(p.getPrecioUnitario() > 0);
    }

    // 3. Asignación del vendedor a la sucursal
    @Test
    void test03VendedorAsignadoASucursal() {
        Sucursal s = sistema.getLstSucursales().get(0);
        assertTrue(s.getLstVendedores().contains(vendedor));
        assertEquals(s, vendedor.getSucursal());
    }

    // 4. Agregar producto al sistema
    @Test
    void test04AgregarProductoASistema() throws Exception {
        assertTrue(sistema.agregarProducto(true, "Laptop Test", 50000f, LocalDate.of(2023, 1, 15), "Test"));
        Producto p = sistema.traerProducto("Laptop Test");
        assertNotNull(p);
        assertEquals("Laptop Test", p.getDescripcion());
    }

 // 5. Agregar un cliente al sistema
    @Test
    void test05AgregarClienteASistema() {
        Cliente nuevoCliente = new Cliente("Pedro", "Gómez", LocalDate.of(1988, 6, 15), 87654321L, "pedro@test.com");
        sistema.getLstClientes().add(nuevoCliente);

        assertTrue(sistema.getLstClientes().contains(nuevoCliente));
        assertEquals("Pedro", sistema.getLstClientes().get(sistema.getLstClientes().size() - 1).getNombre());
    }

// 6. Agregar una nueva sucursal
	@Test
	void test06AgregarSucursal() throws Exception {
		assertTrue(sistema.agregarSucursal("La Plata", "7", 123, -34.921f, -57.954f, 2214440000L, "Carlos", "Pérez",
				LocalDate.of(1980, 5, 10), 12345678L, 40, 200000f));

		// Obtener la última sucursal agregada de forma segura
		Sucursal s = sistema.getLstSucursales().get(sistema.getLstSucursales().size() - 1);
		assertNotNull(s);
		assertEquals("La Plata", s.getUbicacion().getCiudad());
	}

    // 7. Asignar un nuevo gerente a sucursal existente
    @Test
    void test07AsignarGerenteASucursal() throws Exception {
        sistema.agregarSucursal("La Plata", "7", 123, -34.921f, -57.954f, 2214440000L,
                "Carlos", "Pérez", LocalDate.of(1980, 5, 10), 12345678L, 40, 200000f);
        Sucursal s = sistema.traerSucursal(1);

        Gerente g = new Gerente("Lucía", "Fernández", LocalDate.of(1985, 3, 15), 87654321L, 40, 180000f);
        s.setGerente(g);
        assertEquals(g, s.getGerente());
    }

    // 8. Agregar un lote a un producto existente
    @Test
    void test08AgregarLoteAProducto() throws Exception {
        sistema.agregarProducto(true, "Laptop Test", 50000f, LocalDate.of(2023, 1, 15), "Test");
        Producto p = sistema.traerProducto("Laptop Test");

        sistema.agregarSucursal("La Plata", "7", 123, -34.921f, -57.954f, 2214440000L,
                "Carlos", "Pérez", LocalDate.of(1980, 5, 10), 12345678L, 40, 200000f);
        Sucursal s = sistema.traerSucursal(1);

        Lote l = new Lote(p, 'M', 10, LocalDate.of(2024, 3, 1));
        s.getLstLotes().add(l);

        assertTrue(s.getLstLotes().contains(l));
        assertEquals(p, l.getProducto());
    }



 // 9. Buscar producto por descripción
    @Test
    void test09BuscarProductoPorDescripcion() throws Exception {
        sistema.agregarProducto(true, "Remera Azul", 15000f, LocalDate.now(), "Nike");

        Producto resultado = sistema.traerProducto("Remera Azul"); 

        assertNotNull(resultado);
        assertEquals("Remera Azul", resultado.getDescripcion());
    }


 // 10. Intentar eliminar un producto inexistente
    @Test
    void test10EliminarProductoInexistente() {
        String descripcionInexistente = "Producto Fantasma";
        
        Exception exception = assertThrows(Exception.class, () -> {
            sistema.eliminarProducto(descripcionInexistente);
        });
        
        assertTrue(exception.getMessage().contains("Error: No se encontró el producto"));
    }
}



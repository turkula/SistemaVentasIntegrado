package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaVentasIntegracionTest {

    private Sistema sistema;
    private Cliente cliente;
    private Producto producto;
    private Sucursal sucursal;
    private Vendedor vendedor;

    @BeforeEach
    void setUp() {
        sistema = new Sistema();

        LocalDate fechaNac = LocalDate.of(1990, 1, 1);
        cliente = new Cliente("María", "González", fechaNac, 12345678L, "maria@test.com");

        LocalDate fechaAlta = LocalDate.of(2023, 1, 15);
        producto = new Producto(1, true, "Laptop Test", 50000f, fechaAlta, "Test");
    }

    @Test
    void testSistemaInicializado() {
        assertNotNull(sistema);
        assertNotNull(cliente);
        assertNotNull(producto);
    }

    @Test
    void testClienteYProductoCompatibles() {
        assertTrue(cliente.getNombre().length() > 0);
        assertTrue(producto.getDescripcion().length() > 0);
        assertTrue(producto.getPrecioUnitario() > 0);
    }

    @Test
    void testFechasValidas() {
        assertNotNull(cliente.getFechaNacimiento());
        assertNotNull(producto.getFechaAlta());
        assertTrue(producto.getFechaAlta().isAfter(LocalDate.of(2020, 1, 1)));
    }

}

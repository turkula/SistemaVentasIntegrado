package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoUnitTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        LocalDate fechaAlta = LocalDate.of(2023, 1, 15);
        producto = new Producto(1, true, "Laptop Dell", 80000f, fechaAlta, "Dell");
    }

    @Test
    void testProductoCreation() {
        assertEquals(1, producto.getId());
        assertTrue(producto.isStatus());
        assertEquals("Laptop Dell", producto.getDescripcion());
        assertEquals(80000f, producto.getPrecioUnitario());
        assertEquals("Dell", producto.getMarca());
    }

    @Test
    void testActivarYDesactivarProducto() {
        producto.desactivarProducto();
        assertFalse(producto.isStatus());

        producto.activarProducto();
        assertTrue(producto.isStatus());
    }

    @Test
    void testSetters() {
        producto.setDescripcion("Laptop HP");
        producto.setPrecioUnitario(75000f);
        producto.setMarca("HP");

        assertEquals("Laptop HP", producto.getDescripcion());
        assertEquals(75000f, producto.getPrecioUnitario());
        assertEquals("HP", producto.getMarca());
    }

   
}

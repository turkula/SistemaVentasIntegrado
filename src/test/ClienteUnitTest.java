package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteUnitTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        LocalDate fechaNac = LocalDate.of(1990, 5, 15);
        cliente = new Cliente("María", "González", fechaNac, 12345678L, "maria@test.com");
    }

    @Test
    void testClienteCreation() {
        assertEquals("María", cliente.getNombre());
        assertEquals("González", cliente.getApellido());
        assertEquals(12345678L, cliente.getDni());
        assertEquals("maria@test.com", cliente.getMail());
    }

    @Test
    void testSetters() {
        cliente.setNombre("Ana");
        cliente.setApellido("López");
        cliente.setMail("ana@test.com");

        assertEquals("Ana", cliente.getNombre());
        assertEquals("López", cliente.getApellido());
        assertEquals("ana@test.com", cliente.getMail());
    }

    @Test
    void testToString() {
        String resultado = cliente.toString();
        assertTrue(resultado.contains("María"));
        assertTrue(resultado.contains("González"));
        assertTrue(resultado.contains("maria@test.com"));
    }

    @Test
    void testHerenciaDePersona() {
        assertNotNull(cliente.getNombre());
        assertNotNull(cliente.getApellido());
        assertNotNull(cliente.getFechaNacimiento());
        assertTrue(cliente.getDni() > 0);
    }

    @Test
    void testMailValidation() {
        assertDoesNotThrow(() -> {
            new Cliente("Juan", "Pérez", LocalDate.now(), 98765432L, "juan@test.com");
        });
    }
}

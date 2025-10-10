package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Empleado;

class EmpleadoTest {
	
	private Empleado empleado;

	@BeforeEach
	void setUp() throws Exception {
		empleado = new Empleado("Empleado Nombre","Empleado Apellido",LocalDate.of(2000, 10,15),12345678L,8,1000f);
	}

	@Test
	void testCalcularSueldoPorHoras() {
		assertEquals(625f, empleado.calcularSueldoPorHoras(5),"El calculo del sueldo es incorrecto");
	}

	@Test
	void testCalcularDiasHastaCumpleanios() {
		LocalDate actual = LocalDate.of(2025, 10, 8);
		assertEquals(7, empleado.calcularDiasHastaCumpleanios(actual),"El calculo de dias es incorrecto");
	}

	@Test
	void testCalcularAntiguedad() {
		LocalDate inicio = LocalDate.of(2020, 1, 1);
		LocalDate actual = LocalDate.of(2025, 10, 8);
		
		assertEquals(5,empleado.calcularAntiguedad(inicio, actual),"El calculo de antiguedad es incorrecto");
	}

}

package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Empleado extends Persona{
	
	protected int horasPorJornada;
	protected float sueldoBasico;
	
	public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.horasPorJornada = horasPorJornada;
		this.sueldoBasico = sueldoBasico;
	}


	public int getHorasPorJornada() {
		return horasPorJornada;
	}


	public void setHorasPorJornada(int horasPorJornada) {
		this.horasPorJornada = horasPorJornada;
	}


	public float getSueldoBasico() {
		return sueldoBasico;
	}


	public void setSueldoBasico(float sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}
	
	public float calcularSueldoPorHoras(int horas) {
		float sueldoPorHora = this.sueldoBasico / this.horasPorJornada;
		return sueldoPorHora * horas;
	}
	
	public long calcularDiasHastaCumpleanios(LocalDate fechaActual) {
		LocalDate fechaDeCumpleanios = LocalDate.of(fechaActual.getYear(), this.fechaNacimiento.getMonth(),this.fechaNacimiento.getDayOfMonth());
	
		if (fechaDeCumpleanios.isBefore(fechaActual) || fechaDeCumpleanios.equals(fechaActual)) {
			fechaDeCumpleanios = fechaDeCumpleanios.plusYears(1);
		}
		
		return ChronoUnit.DAYS.between(fechaActual, fechaDeCumpleanios);
	}
	
	public long calcularAntiguedad(LocalDate fechaIngreso, LocalDate fechaActual) {
		return ChronoUnit.YEARS.between(fechaIngreso, fechaActual);
	}
	
	

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

/**
 *
 * @author bruno
 */

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa a un cliente del sistema de descuentos.
 * Almacena sus datos personales y calcula los descuentos aplicables.
 */
public class Cliente {
    private String nombre;
    private LocalDate fechaNacimiento;
    
    /**
     * Constructor que inicializa un cliente con su nombre y fecha de nacimiento.
     * @param nombre Nombre completo del cliente
     * @param fechaNacimiento Fecha de nacimiento del cliente (LocalDate)
     */
    public Cliente(String nombre, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Obtiene el nombre del cliente.
     * @return Nombre completo del cliente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Calcula la edad actual del cliente en años cumplidos.
     * Usa Period.between() para calcular la diferencia exacta entre fechas.
     * @param fechaActual La fecha actual para calcular la edad
     * @return Edad del cliente en años (solo años cumplidos)
     */
    public int calcularEdad(LocalDate fechaActual) {
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }
    
    /**
     * Calcula cuántos días faltan para el próximo cumpleaños del cliente.
     * Si el cumpleaños ya pasó este año, calcula para el próximo año.
     * @param fechaActual La fecha actual de referencia
     * @return Número de días hasta el próximo cumpleaños
     */
    public long diasHastaCumple(LocalDate fechaActual) {
        // Creamos la fecha del cumpleaños en el año actual
        LocalDate cumpleEsteAño = fechaNacimiento.withYear(fechaActual.getYear());
        
        // Si ya pasó el cumpleaños este año (o es hoy), calculamos para el año que viene
        if (cumpleEsteAño.isBefore(fechaActual) || cumpleEsteAño.isEqual(fechaActual)) {
            cumpleEsteAño = cumpleEsteAño.plusYears(1);
        }
        
        // Calculamos los días de diferencia entre hoy y el próximo cumpleaños
        return ChronoUnit.DAYS.between(fechaActual, cumpleEsteAño);
    }
    
    /**
     * Calcula el porcentaje de descuento según la edad del cliente.
     * - Menores de 18 años: 10% de descuento
     * - Mayores de 65 años: 15% de descuento
     * - Entre 18 y 65 años: sin descuento por edad
     * @param fechaActual La fecha actual para calcular la edad
     * @return Porcentaje de descuento (0, 10 o 15)
     */
    public double calcularDescuentoEdad(LocalDate fechaActual) {
        int edad = calcularEdad(fechaActual);
        
        if (edad < 18) {
            return 10.0; // 10% de descuento para menores
        } else if (edad > 65) {
            return 15.0; // 15% de descuento para mayores
        }
        return 0.0; // Sin descuento para edades entre 18 y 65
    }
    
    /**
     * Calcula el porcentaje de descuento según la proximidad del cumpleaños.
     * - Si faltan 7 días o menos: 20% de descuento
     * - Si faltan entre 8 y 14 días: 10% de descuento
     * - En cualquier otro caso: sin descuento
     * @param fechaActual La fecha actual de referencia
     * @return Porcentaje de descuento (0, 10 o 20)
     */
    public double calcularDescuentoCumple(LocalDate fechaActual) {
        long dias = diasHastaCumple(fechaActual);
        
        if (dias <= 7) {
            return 20.0; // 20% si falta una semana o menos
        } else if (dias <= 14) {
            return 10.0; // 10% si faltan entre 8 y 14 días
        }
        return 0.0; // Sin descuento si falta más de 2 semanas
    }
}
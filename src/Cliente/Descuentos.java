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
import java.util.Scanner;

/**
 * Clase principal del sistema de descuentos.
 * Coordina la interacción con el usuario y el proceso de compra.
 */
public class Descuentos {

    /**
     * Método principal que ejecuta todo el flujo del programa.
     * @param args Argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("===== SISTEMA DE DESCUENTOS =====\n");
        
        // ========== 1. RECOGER DATOS DEL CLIENTE ==========
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        
        // Solicitar fecha de nacimiento (día, mes y año por separado)
        System.out.print("Fecha de nacimiento (día): ");
        int dia = sc.nextInt();
        System.out.print("Mes: ");
        int mes = sc.nextInt();
        System.out.print("Año: ");
        int anio = sc.nextInt();
        
        // Crear objeto LocalDate con la fecha de nacimiento
        LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
        // Crear el objeto Cliente con sus datos
        Cliente cliente = new Cliente(nombre, fechaNacimiento);
        
        // ========== 2. RECOGER FECHA ACTUAL ==========
        System.out.print("\nFecha actual (día): ");
        int diaActual = sc.nextInt();
        System.out.print("Mes: ");
        int mesActual = sc.nextInt();
        System.out.print("Año: ");
        int anioActual = sc.nextInt();
        
        // Crear objeto LocalDate con la fecha actual
        LocalDate fechaActual = LocalDate.of(anioActual, mesActual, diaActual);
        
        // ========== 3. CREAR CATÁLOGO DE PRODUCTOS ==========
        Item item1 = new Item("Camisa", 25.00);
        Item item2 = new Item("Pantalón", 40.50);
        Item item3 = new Item("Zapatos", 60.00);
        
        // Mostrar productos disponibles al usuario
        System.out.println("\n----- PRODUCTOS DISPONIBLES -----");
        System.out.println("1. " + item1.getNombre() + " - €" + item1.getPrecio());
        System.out.println("2. " + item2.getNombre() + " - €" + item2.getPrecio());
        System.out.println("3. " + item3.getNombre() + " - €" + item3.getPrecio());
        
        // ========== 4. CONSTRUIR EL CARRITO DE COMPRAS ==========
        Carrito carrito = new Carrito();
        
        // Preguntar cuántos productos diferentes quiere comprar
        System.out.print("\n¿Cuántos productos diferentes deseas comprar? ");
        int numProductos = sc.nextInt();
        
        // Para cada producto, solicitar cuál es y la cantidad
        for (int i = 0; i < numProductos; i++) {
            System.out.print("\nProducto " + (i+1) + " (1-3): ");
            int opcion = sc.nextInt();
            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            
            // Agregar el producto seleccionado al carrito
            switch(opcion) {
                case 1: carrito.agregarItem(item1, cantidad); break;
                case 2: carrito.agregarItem(item2, cantidad); break;
                case 3: carrito.agregarItem(item3, cantidad); break;
            }
        }
        
        // ========== 5. CALCULAR TODOS LOS DESCUENTOS ==========
        // Calcular el importe total sin descuentos
        double importeBruto = carrito.calcularImporteBruto();
        
        // Obtener el porcentaje de descuento por edad
        double descEdad = cliente.calcularDescuentoEdad(fechaActual);
        
        // Obtener el porcentaje de descuento por cumpleaños
        double descCumple = cliente.calcularDescuentoCumple(fechaActual);
        
        // Sumar ambos descuentos, pero con un máximo del 30%
        double descTotal = Math.min(descEdad + descCumple, 30.0);
        
        // Calcular el monto del descuento en euros
        double montoDescuento = importeBruto * (descTotal / 100);
        
        // Calcular el total final a pagar (restando el descuento)
        double totalPagar = importeBruto - montoDescuento;
        
        // ========== 6. MOSTRAR EL TICKET FINAL ==========
        mostrarTicket(cliente, fechaActual, carrito, importeBruto, 
                      descEdad, descCumple, descTotal, totalPagar);
        
        sc.close();
    }
    
    /**
     * Muestra el ticket de compra completo con todos los detalles.
     * Presenta de forma clara: productos, precios, descuentos y total.
     * @param cliente El cliente que realiza la compra
     * @param fechaActual Fecha de la compra
     * @param carrito Carrito con los productos
     * @param importeBruto Total sin descuentos
     * @param descEdad Porcentaje de descuento por edad
     * @param descCumple Porcentaje de descuento por cumpleaños
     * @param descTotal Descuento total aplicado (máximo 30%)
     * @param totalPagar Cantidad final a pagar
     */
    public static void mostrarTicket(Cliente cliente, LocalDate fechaActual, 
                                     Carrito carrito, double importeBruto,
                                     double descEdad, double descCumple, 
                                     double descTotal, double totalPagar) {
        
        // Encabezado del ticket
        System.out.println("\n\n========== TICKET DE COMPRA ==========");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Edad: " + cliente.calcularEdad(fechaActual) + " años");
        
        // Detalle de productos comprados
        System.out.println("\n----- PRODUCTOS -----");
        for (Carrito.ItemCarrito ic : carrito.getItems()) {
            // Muestra: NombreProducto x Cantidad - €Precio c/u = €Subtotal
            System.out.printf("%s x%d - €%.2f c/u = €%.2f\n",
                ic.getItem().getNombre(),
                ic.getCantidad(),
                ic.getItem().getPrecio(),
                ic.getSubtotal());
        }
        
        // Importe bruto (sin descuentos)
        System.out.printf("\nImporte Bruto: €%.2f\n", importeBruto);
        
        // Desglose de descuentos aplicados
        System.out.println("\n----- DESCUENTOS -----");
        System.out.printf("Por edad: %.0f%%\n", descEdad);
        System.out.printf("Por cumpleaños: %.0f%%\n", descCumple);
        System.out.printf("DESCUENTO TOTAL: %.0f%%\n", descTotal);
        
        // Total final a pagar (destacado)
        System.out.printf("\n★ TOTAL A PAGAR: €%.2f ★\n", totalPagar);
        
        // Mensaje especial si el cumpleaños está cerca (7 días o menos)
        long diasCumple = cliente.diasHastaCumple(fechaActual);
        if (diasCumple <= 7) {
            System.out.println("\n🎉 ¡Feliz cumpleaños adelantado! Disfruta tu descuento especial.");
        }
        
        System.out.println("======================================");
    }
}
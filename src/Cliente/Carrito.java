/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente; 

/**
 *
 * @author bruno
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el carrito de compras.
 * Almacena los productos seleccionados con sus cantidades.
 */
public class Carrito {
    private List<ItemCarrito> items;
    
    /**
     * Constructor que inicializa un carrito vacío.
     * Crea una nueva lista ArrayList para almacenar los items.
     */
    public Carrito() {
        items = new ArrayList<>();
    }
    
    /**
     * Agrega un producto al carrito con su cantidad.
     * Crea un nuevo ItemCarrito que combina el producto y la cantidad.
     * @param item El producto a agregar (objeto Item)
     * @param cantidad La cantidad de unidades del producto
     */
    public void agregarItem(Item item, int cantidad) {
        items.add(new ItemCarrito(item, cantidad));
    }
    
    /**
     * Calcula el importe total bruto de todos los productos en el carrito.
     * Suma los subtotales (precio × cantidad) de cada item.
     * @return El importe total sin aplicar descuentos
     */
    public double calcularImporteBruto() {
        double total = 0;
        // Recorremos todos los items del carrito
        for (ItemCarrito ic : items) {
            total += ic.getSubtotal(); // Sumamos el subtotal de cada item
        }
        return total;
    }
    
    /**
     * Obtiene la lista completa de items en el carrito.
     * Útil para mostrar el detalle de la compra.
     * @return Lista de ItemCarrito con todos los productos
     */
    public List<ItemCarrito> getItems() {
        return items;
    }
    
    /**
     * Clase interna que representa un item dentro del carrito.
     * Combina un producto (Item) con su cantidad comprada.
     */
    public static class ItemCarrito {
        private Item item;      // El producto
        private int cantidad;   // Cantidad de unidades
        
        /**
         * Constructor que inicializa un item del carrito.
         * @param item El producto
         * @param cantidad Número de unidades
         */
        public ItemCarrito(Item item, int cantidad) {
            this.item = item;
            this.cantidad = cantidad;
        }
        
        /**
         * Obtiene el producto asociado a este item.
         * @return El objeto Item (producto)
         */
        public Item getItem() {
            return item;
        }
        
        /**
         * Obtiene la cantidad de unidades del producto.
         * @return Número de unidades
         */
        public int getCantidad() {
            return cantidad;
        }
        
        /**
         * Calcula el subtotal de este item (precio × cantidad).
         * Por ejemplo: 3 camisas a €25 = €75
         * @return Precio total de este item
         */
        public double getSubtotal() {
            return item.getPrecio() * cantidad;
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;  
/**
 * Clase que representa un producto en el inventario.
 * @author bruno
*/
public class Item {
    private String nombre;
    private double precio;

    /**
     * Constructor que inicializa un producto con nombre y precio.
     * @param nombre Nombre del producto
     * @param precio Precio del producto en euros
     */
    public Item(String nombre, double precio) {
        this.precio = precio;    
        this.nombre = nombre;
    }
 
    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Obtiene el precio del producto.
     * @return Precio en euros
     */
    public double getPrecio() { 
        return precio; 
    }
    
    /**
     * Modifica el precio del producto.
     * @param precio Nuevo precio en euros
     */
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    /**
     * Representación en texto del producto.
     * @return String con formato "Producto: nombre | Precio: €precio"
     */
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Precio: €" + precio;
    }
}
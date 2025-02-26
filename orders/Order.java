package orders;
/**
 * Esta clase abstracta gestiona los pedidos
 *
 * @author Diego Lesma, Diego Gonzalez
 */

public abstract class Order {
    private String item;
    private double basePrice;
    private String customer;
    /**
     * Es el constructor de la clase Order, no se puede llamar por el usuario, pero las subclases lo utilizan
     *
     * @param item String con el nombre del objeto pedido
     * @param price Variable con el precio del item
     * @param customer String con el nombre del cliente
     */
    public Order(String item, double price, String customer) {
        this.item = item;
        this.basePrice = price;
        this.customer = customer;
    }

    /**
     * Metodo para calcular el precio final, será overrideado por las subclases
     *
     * @return precio final del pedido
     */
    public double totalPrice() {
        return this.basePrice;
    }

    /**
     * Metodo para imprimir las orders
     *
     * @return string con la informacion del pedido
     */
    @Override
    public String toString() {
        return "Order of '"+this.item+"' for "+this.customer+" ("+this.totalPrice()+"$)";
    }

    /**
     * Metodo para aplicar un descuento a un pedido
     *
     * @param discount porcentaje a descontar
     * @return precio modificado despues del descuento
     */
    public double setDiscount(double discount) {
        if(discount >= 100) {
            this.basePrice = 0;
        } else if(discount > 0) {
            this.basePrice -= (this.basePrice * discount / 100);
        }
        return this.basePrice;
    }
}


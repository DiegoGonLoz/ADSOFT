package orders;

/**
 * Esta clase gestiona los pedidos en tienda
 *
 * @author Diego Lesma, Diego Gonzalez
 */

public class InStoreOrder extends Order {
    private BookStore store;
    private double baseprice;

    /**
     * Es el constructor de la clase InStoreOrder
     *
     * @param item String con el nombre del objeto pedido
     * @param price Variable con el precio del item
     * @param customer String con el nombre del cliente
     * @param store Enumerado con las distintas tiendas donde realizar el pedido
     */
    public InStoreOrder(String item, double price, String customer, BookStore store){
        super(item, price, customer);
        this.store = store;
        this.baseprice=price;
    }

    /**
     * Metodo para imprimir las InStoreOrders, anyadiendo el parametro store
     *
     * @return string con la informacion del pedido
     */
    @Override
    public String toString() {
        return super.toString()+"\nStore: "+this.store;
    }

    /**
     * Metodo para aplicar un descuento automatico en segun que tienda se haya realizado el pedido
     *
     * @return nuevo precio despues de aplicar el descuento
     */
    @Override
    public double totalPrice() {
        if(this.store == BookStore.BROOKLYN){
            return this.baseprice-BookStore.descuentoBrooklyn;
        } else if (this.store==BookStore.NEWARK) {
            return this.baseprice-BookStore.descuentoNewark;
        }
        return this.baseprice-BookStore.descuentoMain;
    }
}

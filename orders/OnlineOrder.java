package orders;

/**
 * Esta clase gestiona los pedidos online
 *
 * @author Diego Lesma, Diego Gonzalez
 */

public class OnlineOrder extends Order{
    String email;
    static int recargo = 5;
    /**
     * Es el constructor de la clase OnlineOrder
     *
     * @param item String con el nombre del objeto pedido
     * @param price Variable con el precio del item
     * @param customer String con el nombre del cliente
     * @param email String con el email del usuario
     */
    public OnlineOrder(String item, double price, String customer, String email){
        super(item, price + recargo, customer);
        this.email = email;
    }

    /**
     * Metodo para imprimir las OnlineOrders, anyadiendo el parametro email
     *
     * @return string con la informacion del pedido
     */
    @Override
    public String toString() {
        return super.toString()+"\nEmail: "+this.email;
    }
}

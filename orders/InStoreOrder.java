package orders;
public class InStoreOrder extends Order {
    private BookStore store;
    private double baseprice;
    public InStoreOrder(String item, double price, String customer, BookStore store){
        super(item, price, customer);
        this.store = store;
        this.baseprice=price;
    }
    @Override
    public String toString() {
        return super.toString()+"\nStore: "+this.store;
    }

    @Override
    public double totalPrice() {
        if(this.store == BookStore.BROOKLYN){
            return this.baseprice-1;
        } else if (this.store==BookStore.NEWARK) {
            return this.baseprice-2;
        }
        return this.baseprice;
    }
}

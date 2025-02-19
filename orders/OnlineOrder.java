package orders;

public class OnlineOrder extends Orders{
    private BookStore store;
    public OnlineOrder(String item, double price, String customer, BookStore store){
        super(item, price, customer);
        this.store = store;
    }
    @Override
    public String toString() {
        return super.toString()+"\nStore: "+this.store;
    }
}

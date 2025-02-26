package orders;

public class OnlineOrder extends Order{
    String email;

    public OnlineOrder(String item, double price, String customer, String email){
        super(item, price+5, customer);
        this.email = email;
    }
    @Override
    public String toString() {
        return super.toString()+"\nEmail: "+this.email;
    }
}

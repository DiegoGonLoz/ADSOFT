package orders;
public abstract class Order {
    private String item;
    private double basePrice;
    private String customer;
    public Order(String item, double price, String customer) {
        this.item = item;
        this.basePrice = price;
        this.customer = customer;
    }
    public double totalPrice() {
        return this.basePrice;
    }
    @Override
    public String toString() {
        return "Order of '"+this.item+"' for "+this.customer+" ("+this.totalPrice()+"$)";
    }

    public double setDiscount(double discount) {
        if(discount >= 100) {
            this.basePrice = 0;
        } else if(discount > 0) {
            this.basePrice -= (this.basePrice * discount / 100);
        }
        return this.basePrice;
    }
}


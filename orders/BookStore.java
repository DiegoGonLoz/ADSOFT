package orders;
public enum BookStore {
    MAIN("Fifth Avenue. 73, Manhattan, NYC"),
    BROOKLYN("Bedford Avenue 24, Brooklyn, NYC"),
    NEWARK("Broad Street 11, Newark, NJ");
    private String address;
    static int descuentoMain = 0;
    static int descuentoBrooklyn = 1;
    static int descuentoNewark = 2;

    private BookStore(String address) {
        this.address = address;
    }
    public String toString() {
        return this.address;
    }
}

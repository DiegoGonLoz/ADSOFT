package orders;

/**
 * Esta enumeracion gestiona las distintas sedes de tienda
 *
 * @author Diego Lesma, Diego Gonzalez
 */

public enum BookStore {
    /**
     * Esta enumeracion crea la tienda principal
     *
     * @author Diego Lesma, Diego Gonzalez
     */
    MAIN("Fifth Avenue. 73, Manhattan, NYC"),
    /**
     * Esta enumeracion crea la tienda de Brooklyn
     *
     * @author Diego Lesma, Diego Gonzalez
     */
    BROOKLYN("Bedford Avenue 24, Brooklyn, NYC"),
    /**
     * Esta enumeracion crea la tienda de Newark
     *
     * @author Diego Lesma, Diego Gonzalez
     */
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

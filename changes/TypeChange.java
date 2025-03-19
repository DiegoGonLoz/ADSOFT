package changes;

/**
 * Enumeración que representa los tipos de cambios posibles en un archivo.
 * @author Diego González y Diego Lesma
 */
public enum TypeChange {
    /**
     * Tipo para ADD changes
     */
    ADD('+'),
    /**
     * Tipo para REMOVE changes
     */
    REMOVE('-'),
    /**
     * Tipo para MODIFY changes
     */
    MODIFY('/');

    private final char symbol;

    /**
     * Constructor para crear un tipo de cambio.
     * @param ch Símbolo que representa el tipo de cambio.
     */
    TypeChange(char ch) {
        this.symbol = ch;
    }

    /**
     * Obtiene el símbolo del tipo de cambio.
     * @return Símbolo del tipo de cambio.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Representación en cadena del tipo de cambio.
     * @return Cadena que representa el tipo de cambio.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}

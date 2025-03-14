package changes;

public enum TypeChange {
    ADD('+'),
    REMOVE('-'),
    MODIFY('/');

    private final char symbol;

    TypeChange(char ch) {
        this.symbol = ch;
    }

    @Override
    public String toString(){
        return String.valueOf(symbol);
    }
}

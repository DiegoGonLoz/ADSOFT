package changes;

public abstract class Change {
    protected final String filePath;
    protected final int firstLine;
    protected final TypeChange type;

    public Change(String filePath, int firstLine, TypeChange type) {
        this.filePath = filePath;
        this.firstLine = firstLine;
        this.type = type;
    }

    public abstract int getNumberOfLines();

    @Override
    public abstract String toString();

    public String printForCommit(){
        String symbol = "";
        if(getNumberOfLines() >= 0){
            symbol = "+";
        }

        return "\n"+this.type.toString()+
                ": "+this.filePath +
                " ("+symbol+this.getNumberOfLines()+
                ")"+
                "\n\n";
    }

}

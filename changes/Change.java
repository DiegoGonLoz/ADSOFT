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

    public abstract String getFilePath();

    @Override
    public abstract String toString();

    public String printForCommit(){
        String sign = "";
        if(getNumberOfLines() >= 0){
            sign = "+";
        }

        return "\n"+this.type.toString()+
                ": "+this.filePath +
                " ("+sign+this.getNumberOfLines()+
                ")"+
                "\n\n";
    }

}

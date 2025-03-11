package changes;

public class RemoveChange  extends Change{
    private final int endLine;

    public RemoveChange(int firstLine, int endLine, String filePath) {
        super(filePath, firstLine, TypeChange.REMOVE);
        this.endLine = endLine;
    }

    @Override
    public int getNumberOfLines(){
        return (this.endLine - this.firstLine + 1);
    }

    @Override
    public String toString(){
        return "{\ntype="+this.type.toString()+
                "\nstart line="+this.firstLine+
                "\nfile path="+this.filePath+
                "\nend line = "+this.endLine+
                "\n}\n";
    }
}
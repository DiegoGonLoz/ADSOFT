package changes;

public class ModifyChange  extends Change{
    private final String content;
    private final int endLine;

    public ModifyChange(int firstLine, int endLine, String filePath, String content) {
        super(filePath, firstLine, TypeChange.MODIFY);
        this.content = content;
        this.endLine = endLine;
    }

    @Override
    public int getNumberOfLines(){
        return (endLine - firstLine + 1);
    }

    @Override
    public String toString(){
        return "{\ntype="+this.type.toString()+
                "\nstart line="+this.firstLine+
                "\nfile path="+this.filePath+
                "\ncontent="+this.content+
                "\nnumber of lines="+this.getNumberOfLines()+
                "\nend line="+this.endLine+
                "\n}\n";
    }
}
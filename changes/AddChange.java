package changes;

public class AddChange  extends Change{
    private final String content;

    public AddChange(int firstLine, String filePath, String content) {
        super(filePath, firstLine, TypeChange.ADD);
        this.content = content;
    }

    @Override
    public int getNumberOfLines(){
        String[] lines = content.split("\n");
        return lines.length;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString(){
        return "{\ntype="+this.type.toString()+
                "\nstart line="+this.firstLine+
                "\nfile path="+this.filePath+
                "\ncontent="+this.content+
                "\nnumber of lines="+this.getNumberOfLines()+
                "\n}\n";
    }
}

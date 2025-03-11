package changes;

public class AddChange  extends Change{
    private String content;

    public AddChange(String filePath,int firstLine,TypeChange type, String content){
        super(filePath, firstLine, type);
        this.content = content;
    }

    @Override
    public String toString(){
        System.out.println("{\ntype="+this.type.toString()+
                "\nstart line="+this.firstLine+
                "\nfile path="+this.filePath+
                "\ncontent="+this.content+
                "\nnumber of lines=");
    }
}

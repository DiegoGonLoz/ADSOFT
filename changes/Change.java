package changes;

public abstract class Change {
    protected String filePath;
    protected int firstLine;
    protected TypeChange type;

    public Change(String filePath, int firstLine, TypeChange type) {
        this.filePath = filePath;
        this.firstLine = firstLine;
        this.type = type;
    }

    @Override
    public abstract String toString();
}

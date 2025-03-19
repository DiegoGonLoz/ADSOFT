package changes;

/**
 * Clase que representa un cambio de tipo "añadir" en un archivo.
 * @author Diego González y Diego Lesma
 */
public class AddChange extends Change {
    private final String content;

    /**
     * Constructor para crear un cambio de tipo "añadir".
     * @param firstLine Línea inicial donde se añade el contenido.
     * @param filePath Ruta del archivo donde se realiza el cambio.
     * @param content Contenido que se añade.
     */
    public AddChange(int firstLine, String filePath, String content) {
        super(filePath, firstLine, TypeChange.ADD);
        this.content = content;
    }

    /**
     * Obtiene el número de líneas añadidas.
     * @return Número de líneas añadidas.
     */
    @Override
    public int getNumberOfLines() {
        String[] lines = content.split("\n");
        return lines.length;
    }

    /**
     * Obtiene la ruta del archivo.
     * @return Ruta del archivo.
     */
    @Override
    public String getFilePath() {
        return filePath;
    }

    /**
     * Representación en cadena del cambio.
     * @return Cadena que describe el cambio.
     */
    @Override
    public String toString() {
        return "{\ntype=" + this.type.toString() +
                "\nstart line=" + this.firstLine +
                "\nfile path=" + this.filePath +
                "\ncontent=" + this.content +
                "\nnumber of lines=" + this.getNumberOfLines() +
                "\n}\n";
    }
}

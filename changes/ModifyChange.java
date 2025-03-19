package changes;

/**
 * Clase que representa un cambio de tipo "modificar" en un archivo.
 * @author Diego González y Diego Lesma
 */
public class ModifyChange extends Change {
    private final String content;
    private final int endLine;

    /**
     * Constructor para crear un cambio de tipo "modificar".
     * @param firstLine Línea inicial donde se realiza la modificación.
     * @param endLine Línea final donde se realiza la modificación.
     * @param filePath Ruta del archivo donde se realiza el cambio.
     * @param content Contenido que se modifica.
     */
    public ModifyChange(int firstLine, int endLine, String filePath, String content) {
        super(filePath, firstLine, TypeChange.MODIFY);
        this.content = content;
        this.endLine = endLine;
    }

    /**
     * Obtiene el número de líneas afectadas por la modificación.
     * @return Número de líneas afectadas.
     */
    @Override
    public int getNumberOfLines() {
        String[] lines = this.content.split("\n");
        return (lines.length - (endLine - firstLine + 1));
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
                "\nend line=" + this.endLine +
                "\n}\n";
    }
}
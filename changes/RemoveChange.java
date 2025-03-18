package changes;

/**
 * Clase que representa un cambio de tipo "eliminar" en un archivo.
 * Autor: Diego González y Diego Lesma
 */
public class RemoveChange extends Change {
    private final int endLine;

    /**
     * Constructor para crear un cambio de tipo "eliminar".
     * @param firstLine Línea inicial donde se realiza la eliminación.
     * @param endLine Línea final donde se realiza la eliminación.
     * @param filePath Ruta del archivo donde se realiza el cambio.
     */
    public RemoveChange(int firstLine, int endLine, String filePath) {
        super(filePath, firstLine, TypeChange.REMOVE);
        this.endLine = endLine;
    }

    /**
     * Obtiene el número de líneas eliminadas.
     * @return Número de líneas eliminadas.
     */
    @Override
    public int getNumberOfLines() {
        return (this.endLine - this.firstLine + 1);
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
                "\nend line=" + this.endLine +
                "\n}\n";
    }
}
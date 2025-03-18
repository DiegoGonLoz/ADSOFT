package changes;

/**
 * Clase abstracta que representa un cambio en un archivo.
 * Autor: Diego González y Diego Lesma
 */
public abstract class Change {
    protected final String filePath;
    protected final int firstLine;
    protected final TypeChange type;

    /**
     * Constructor para crear un cambio.
     * @param filePath Ruta del archivo donde se realiza el cambio.
     * @param firstLine Línea inicial del cambio.
     * @param type Tipo de cambio.
     */
    public Change(String filePath, int firstLine, TypeChange type) {
        this.filePath = filePath;
        this.firstLine = firstLine;
        this.type = type;
    }

    /**
     * Metodo abstracto para obtener el número de líneas afectadas.
     * @return Número de líneas afectadas.
     */
    public abstract int getNumberOfLines();

    /**
     * Metodo abstracto para obtener la ruta del archivo.
     * @return Ruta del archivo.
     */
    public abstract String getFilePath();

    /**
     * Representación en cadena del cambio.
     * @return Cadena que describe el cambio.
     */
    @Override
    public abstract String toString();

    /**
     * Metodo para imprimir el cambio en el contexto de un commit.
     * @return Cadena que describe el cambio para un commit.
     */
    public String printForCommit() {
        String sign = "";
        if (getNumberOfLines() >= 0) {
            sign = "+";
        }

        return this.type.toString() +
                ": " + this.filePath +
                " (" + sign + this.getNumberOfLines() +
                ")";
    }
}

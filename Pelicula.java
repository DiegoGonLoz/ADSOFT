/**
 * Este archivo contiene toda la funcionalidad de la clase Pelicula
 *
 * @author Diego González, Diego Lesma
 */

public class Pelicula {
    private final String titulo;
    private final String genero;
    private final int anyo;
    private final String director;

    /**
     * Constructor de la clase pelicula
     *
     * @param titulo Un string con el titulo de la pelicula
     * @param genero Un string con el genero de la pelicula
     * @param anyo  Un int con el anyo de la pelicula
     * @param director Un string con el nombre del director de la pelicula
     */
    public Pelicula (String titulo, String genero, int anyo, String director) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyo = anyo;
        this.director = director;
    }

    /**
     * Refinamiento del metodo toString() para la clase pelicula
     *
     * @return Atributos de Pelicula en un formato adecuado
     */
    @Override
    public String toString() {
        return this.titulo+" - dirigida por: "+ this.director+" ("+this.genero+"): "+this.anyo;
    }

    /**
     * Metodo que permite obtener el titulo de una Pelicula
     *
     * @return String con el titulo de una Pelicula
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Metodo que permite obtener el genero de una Pelicula
     *
     * @return String con el genero de una Pelicula
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Metodo que permite obtener el anyo de una Pelicula
     *
     * @return Int con el anyo de una Pelicula
     */
    public int getAnyo() {
        return anyo;
    }

    /**
     * Metodo que permite obtener el director de una Pelicula
     *
     * @return String con el director de una Pelicula
     */
    public String getDirector() {
        return director;
    }
}

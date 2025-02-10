public class Pelicula {
    private final String titulo;
    private final String genero;
    private final int anyo;
    public Pelicula (String titulo, String genero, int anyo) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyo = anyo;
    }
    @Override
    public String toString() {
        return this.titulo+" ("+this.genero+"): "+this.anyo;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getGenero() {
        return genero;
    }
    public int getAnyo() {
        return anyo;
    }
}

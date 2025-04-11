package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar apoyar un proyecto de hace más de 60 días
 *
 * @author Diego Lesma
 */
public class ProyectoMasDe60Dias extends IllegalArgumentException {
    /**Dias transcurridos*/
    long diasTranscurridos;

    /**
     * Constructor de la clase ProyectoMasDe60Dias
     * @param message mensaje de error
     * @param diasTranscurridos long con los dias transcurridos
     */
    public ProyectoMasDe60Dias(String message, long diasTranscurridos) {
        super(message);
        this.diasTranscurridos = diasTranscurridos;
    }

    /**
     * Metodo toString de la clase ProyectoMasDe60Dias
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nProyecto transcurrido durante " + diasTranscurridos;
    }
}

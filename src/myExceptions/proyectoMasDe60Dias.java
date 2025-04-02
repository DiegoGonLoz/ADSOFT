package myExceptions;

public class proyectoMasDe60Dias extends IllegalArgumentException {
    long diasTranscurridos;
    public proyectoMasDe60Dias(String message, long diasTranscurridos) {
        super(message);
        this.diasTranscurridos = diasTranscurridos;
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nProyecto transcurrido durante " + diasTranscurridos;
    }
}

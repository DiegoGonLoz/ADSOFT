package proponentes;

public abstract class Proponente {
    protected final String name;
    protected final String contrasena;

    Proponente(String name, String contrasena){
        this.name = name;
        this.contrasena = contrasena;
    }

    public abstract void proponer(Proponente proponente);
}

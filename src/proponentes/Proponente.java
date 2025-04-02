package proponentes;

import java.util.List;

public abstract class Proponente {
    protected final String nombre;
    protected final String contrasena;

    Proponente(String nombre, String contraseña) throws NullPointerException{
        if(nombre == null){
            throw new NullPointerException("Error en el constructor Proponente: \nNombre es null");
        }
        if(contraseña == null){
            throw new NullPointerException("Error en el constructor Proponente: \nContraseña es null");
        }
        this.nombre = nombre;
        this.contrasena = contraseña;
    }

    public String getNombre(){
        return this.nombre;
    }

    public abstract String toString();

    public abstract List<Ciudadano> todosLosCiudadanos();

    @Override
    public abstract boolean equals(Object obj);
}

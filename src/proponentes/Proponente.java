package proponentes;

import myExceptions.errorAnadiendoProponente;
import proyectos.ProyectoCiudadano;

import java.util.Set;

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

    public abstract Set<Ciudadano> todosLosCiudadanos();

    public abstract errorAnadiendoProponente repetido();

    @Override
    public abstract boolean equals(Object obj);
}

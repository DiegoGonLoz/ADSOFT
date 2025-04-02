package proponentes;

import java.util.List;

public abstract class Proponente {
    protected final String nombre;
    protected final String contrasena;

    Proponente(String nombre, String contraseña){
        if(nombre == null){
            throw Exception
        }
        if(contraseña == null){
            throw Exception
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

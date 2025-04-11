package proponentes;

import myExceptions.ErrorAnadiendoProponente;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.Set;

/**
 * Clase que representa a un proponente
 *
 * @author Diego Gonzalez
 */
public abstract class Proponente {
    /**Nombre del proponente*/
    protected final String nombre;
    /**Contraseña del proponente*/
    protected final String contrasena;

    /**
     * Constructor de Proponente
     * @param nombre nombre
     * @param contraseña contraseña
     * @throws NullPointerException excepcion parametros no null
     */
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

    /**
     * Getter de nombre
     * @return nombre del proponente
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Metodo toString
     * @return String con la información de proponente
     */
    public abstract String toString();

    /**
     * Metodo pata obtener todos los ciudadanos
     * @return set de ciudadanos
     */
    public abstract Set<Ciudadano> todosLosCiudadanos();

    /**
     * Metodo que se invoca si el proponente esta repetido
     * @return excepcion error añadiendo proponente
     */
    public abstract ErrorAnadiendoProponente repetido();

    /**
     * Metodo para proponer un proyecto
     * @param proyecto proyecto a proponer
     * @return true si se completa con exito y false si no
     */
    public boolean proponer(ProyectoParticipativo proyecto){
        return Sistema.getInstance().proponerProyecto(proyecto);
    }

    /**
     * Metodo equals
     * @param obj objeto a comparar
     * @return true o false según sea igual o no
     */
    @Override
    public abstract boolean equals(Object obj);
}

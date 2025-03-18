package repositories;

/**
 * Clase que representa un usuario en el sistema.
 * Autor: Diego González y Diego Lesma
 */

public class User {
    private final String username;

    /**
     * Constructor para crear un usuario.
     * @param username Nombre del usuario.
     */
    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}

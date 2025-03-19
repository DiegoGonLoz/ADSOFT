package commits;

import changes.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Clase abstracta que representa un commit en un sistema de control de versiones.
 * @author Diego González y Diego Lesma
 */
public abstract class Commit {
    private static int lastId = 0;
    private static String defaultUserName = "defaultUser";
    private static String defaultDescription = "No description provided";

    /**
     * Atributo que guarda el username del usuario que realizo el commit
     */
    protected final String userName;
    /**
     * Atributo que guarda la fecha en que fue realizado el commit
     */
    protected final LocalDate date;
    /**
     * Atributo que guarda la descripcion proporcionada para el commit
     */
    protected final String description;
    /**
     * Atributo que identifica de manera unica el commit
     */
    protected final int id;
    /**
     * Atributo que guarda un codigo unico para cada commit
     */
    protected final String code;

    /**
     * Constructor para crear un commit.
     * @param userName Nombre del usuario que realiza el commit.
     * @param description Descripción del commit.
     */
    public Commit(String userName, String description) {
        this.userName = (userName != null && !userName.isEmpty()) ? userName : defaultUserName;
        this.date = LocalDate.now();
        this.description = (description != null && !description.isEmpty()) ? description : defaultDescription;
        this.id = generateId();
        this.code = generateCode();
    }

    /**
     * Constructor por defecto para crear un commit con valores predeterminados.
     */
    public Commit() {
        this(null, null);
    }

    /**
     * Genera un ID único para el commit.
     * @return ID único.
     */
    private synchronized int generateId() {
        lastId++;
        if (lastId > 99999) {
            throw new IllegalStateException("Se ha alcanzado el límite de IDs únicos (99999).");
        }
        return lastId;
    }

    /**
     * Genera un código único para el commit.
     * @return Código único.
     */
    public String generateCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 15);
    }

    /**
     * Obtiene el ID del commit.
     * @return ID del commit.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el código del commit.
     * @return Código del commit.
     */
    public String getCode() {
        return code;
    }

    /**
     * Obtiene la descripción del commit.
     * @return Descripción del commit.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene la fecha del commit.
     * @return Fecha del commit.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Establece el nombre de usuario predeterminado.
     * @param userName Nombre de usuario predeterminado.
     */
    public void setDefaultUserName(String userName) {
        defaultUserName = userName;
    }

    /**
     * Establece la descripción predeterminada.
     * @param description Descripción predeterminada.
     */
    public void setDefaultDescription(String description) {
        defaultDescription = description;
    }

    /**
     * Metodo abstracto para obtener la lista de cambios del commit.
     * @return Lista de cambios.
     */
    public abstract List<Change> changes();

    /**
     * Representación en cadena del commit.
     * @return Cadena que describe el commit.
     */
    @Override
    public abstract String toString();

    /**
     * Metodo abstracto para obtener el número total de líneas afectadas por los cambios.
     * @return Número total de líneas afectadas.
     */
    public abstract int totalLinesMoved();
}

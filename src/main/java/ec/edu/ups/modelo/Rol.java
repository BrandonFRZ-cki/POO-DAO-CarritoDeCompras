package ec.edu.ups.modelo;

/**
 * Enumeración que representa los posibles roles de un usuario dentro del sistema.
 * Los roles determinan los permisos y accesos disponibles para cada tipo de usuario.
 *
 * <ul>
 *     <li>{@link #ADMINISTRADOR} – Tiene acceso total al sistema, puede gestionar productos y usuarios.</li>
 *     <li>{@link #USUARIO} – Rol estándar para clientes, puede agregar productos al carrito y comprar.</li>
 * </ul>
 *
 * @author Brandon
 * @version 1.0
 */
public enum Rol {
    ADMINISTRADOR,
    USUARIO
}

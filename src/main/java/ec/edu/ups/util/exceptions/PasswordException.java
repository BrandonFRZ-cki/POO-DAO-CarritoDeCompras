package ec.edu.ups.util.exceptions;

/**
 * Excepción personalizada para errores de validación de contraseñas.
 * Se lanza cuando una contraseña no cumple con los criterios de seguridad
 * definidos por el sistema (longitud mínima, uso de mayúsculas, minúsculas y caracteres especiales).
 *
 * El mensaje recibido representa un código de error que se utiliza para
 * mostrar un mensaje internacionalizado al usuario.
 *
 * @author Brandon
 * @version 1.0
 */
public class PasswordException extends RuntimeException {

    /**
     * Crea una nueva excepción de validación de contraseña con un mensaje específico.
     *
     * @param message Código de mensaje o descripción del error.
     */
    public PasswordException(String message) {
        super(message);
    }
}

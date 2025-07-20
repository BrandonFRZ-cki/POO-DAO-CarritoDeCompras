package ec.edu.ups.util.exceptions;

/**
 * Excepción personalizada para errores en la validación de correos electrónicos.
 * Se lanza cuando el correo no contiene los caracteres mínimos necesarios como '@' o '.'.
 *
 * El mensaje recibido usualmente representa un código de error que será utilizado
 * para mostrar un mensaje traducido mediante el sistema de internacionalización.
 *
 * @author Brandon
 * @version 1.0
 */
public class CorreoValidationException extends RuntimeException {

    /**
     * Construye una nueva excepción para errores de validación de correo electrónico.
     *
     * @param message Código del mensaje o descripción del error.
     */
    public CorreoValidationException(String message) {
        super(message);
    }
}

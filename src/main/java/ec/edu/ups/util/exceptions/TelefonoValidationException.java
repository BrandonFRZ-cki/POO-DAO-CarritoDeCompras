package ec.edu.ups.util.exceptions;

/**
 * Excepción personalizada para errores en la validación del número de teléfono.
 * Se lanza cuando el teléfono no cumple con los requisitos del sistema,
 * como tener exactamente 10 dígitos y contener únicamente caracteres numéricos.
 *
 * El mensaje recibido representa un código de error utilizado
 * para mostrar un mensaje internacionalizado al usuario.
 *
 * @author Brandon
 * @version 1.0
 */
public class TelefonoValidationException extends RuntimeException {

    /**
     * Crea una nueva excepción de validación de teléfono con un mensaje específico.
     *
     * @param message Código de mensaje o descripción del error.
     */
    public TelefonoValidationException(String message) {
        super(message);
    }
}

package ec.edu.ups.util.exceptions;

/**
 * Excepción personalizada para errores de validación de cédula.
 * Se lanza cuando la cédula del usuario no cumple con las reglas establecidas
 * como longitud, dígitos válidos, verificador correcto, etc.
 *
 * El mensaje recibido generalmente representa un código que se utiliza
 * para obtener el mensaje traducido mediante el sistema de internacionalización.
 *
 * @author Brandon
 * @version 1.0
 */
public class CedulaValidationException extends RuntimeException {

    /**
     * Construye una nueva excepción de validación de cédula con el mensaje especificado.
     *
     * @param message Código de mensaje de error o mensaje descriptivo.
     */
    public CedulaValidationException(String message) {
        super(message);
    }
}

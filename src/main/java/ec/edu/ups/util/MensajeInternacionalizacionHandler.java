package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase encargada de gestionar la internacionalización del sistema.
 * Permite obtener mensajes traducidos desde archivos de propiedades
 * (.properties) en base al idioma y país seleccionados.
 *
 * Utiliza {@link ResourceBundle} y {@link Locale} para acceder
 * a los recursos localizados.
 *
 * @author Brandon
 * @version 1.0
 */
public class MensajeInternacionalizacionHandler {

    /** Recurso cargado según el idioma y país */
    private ResourceBundle bundle;

    /** Configuración regional actual (idioma y país) */
    private Locale locale;

    /**
     * Constructor que inicializa el handler con un lenguaje y país específicos.
     *
     * @param lenguaje Código del idioma (por ejemplo: "es", "en").
     * @param pais Código del país (por ejemplo: "EC", "US").
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el mensaje traducido correspondiente a la clave dada.
     *
     * @param key Clave del mensaje en el archivo de propiedades.
     * @return El mensaje traducido correspondiente.
     */
    public String get(String key) {
        return bundle.getString(key);
    }

    /**
     * Cambia el idioma y país del sistema y actualiza el bundle cargado.
     *
     * @param lenguaje Nuevo código de idioma.
     * @param pais Nuevo código de país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Devuelve el objeto {@link Locale} actual.
     *
     * @return La configuración regional activa.
     */
    public Locale getLocale() {
        return locale;
    }
}

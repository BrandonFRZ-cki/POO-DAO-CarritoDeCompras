package ec.edu.ups.util;

import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.dao.impl.DAOArchivoBinario.*;
import ec.edu.ups.dao.impl.DAOArchivoTexto.*;

import javax.swing.*;
import java.io.File;

/**
 * Clase auxiliar encargada de configurar el tipo de almacenamiento del sistema
 * (en memoria, archivos de texto o archivos binarios) antes de iniciar la aplicación.
 *
 * Muestra un diálogo gráfico al usuario para seleccionar el tipo de persistencia
 * y, en caso de ser archivos, solicita una carpeta de almacenamiento.
 *
 * La información se guarda en las variables públicas estáticas {@code tipoAlmacenamiento} y {@code rutaSeleccionada}.
 *
 * @author Brandon
 * @version 1.0
 */
public class DAOConfigUtil {

    /**
     * Variable estática que representa el tipo de almacenamiento elegido:
     * puede ser "MEMORIA", "TEXTO" o "BINARIO".
     */
    public static String tipoAlmacenamiento = "MEMORIA";

    /**
     * Ruta seleccionada por el usuario cuando el tipo de almacenamiento es por archivos.
     */
    public static String rutaSeleccionada = "";

    /**
     * Muestra una ventana de selección para configurar el almacenamiento.
     * El usuario podrá elegir entre:
     * <ul>
     *     <li>Memoria (sin persistencia)</li>
     *     <li>Archivos de texto</li>
     *     <li>Archivos binarios</li>
     * </ul>
     * En caso de seleccionar archivos, se abrirá un diálogo para elegir la carpeta de almacenamiento.
     * Si no se selecciona ninguna carpeta, la aplicación se cierra automáticamente.
     */
    public static void seleccionarConfiguracionAlmacenamiento() {
        String[] opciones = {"Memoria", "Archivos de Texto", "Archivos Binarios"};
        int opcion = JOptionPane.showOptionDialog(null,
                "Seleccione el tipo de almacenamiento:",
                "Configuración Inicial",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        switch (opcion) {
            case 1: // Archivos de Texto
            case 2: // Archivos Binarios
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccione carpeta de almacenamiento");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int seleccion = fileChooser.showOpenDialog(null);

                if (seleccion != JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "No se seleccionó una carpeta. Cerrando aplicación.");
                    System.exit(0);
                }

                rutaSeleccionada = fileChooser.getSelectedFile().getAbsolutePath();
                tipoAlmacenamiento = (opcion == 1) ? "TEXTO" : "BINARIO";
                break;

            default: // Memoria
                tipoAlmacenamiento = "MEMORIA";
        }
    }
}

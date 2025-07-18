package ec.edu.ups.util;

import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.dao.impl.DAOArchivoBinario.*;
import ec.edu.ups.dao.impl.DAOArchivoTexto.*;

import javax.swing.*;
import java.io.File;

/**
 * Clase auxiliar para configurar el tipo de almacenamiento a usar.
 */
public class DAOConfigUtil {

    public static String tipoAlmacenamiento = "MEMORIA";
    public static String rutaSeleccionada = "";

    /**
     * Lanza un diálogo para que el usuario elija el tipo de almacenamiento
     * y si corresponde, selecciona una carpeta para archivos.
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
            case 1:
            case 2:
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
            default:
                tipoAlmacenamiento = "MEMORIA";
        }
    }
}

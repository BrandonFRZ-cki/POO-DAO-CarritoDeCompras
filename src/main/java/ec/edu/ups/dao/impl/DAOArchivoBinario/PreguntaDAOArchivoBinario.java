package ec.edu.ups.dao.impl.DAOArchivoBinario;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link PreguntaDAO} utilizando archivos binarios con {@link RandomAccessFile}.
 * Cada pregunta se guarda con el siguiente formato:
 * <ul>
 *     <li>{@code int} → código de la pregunta</li>
 *     <li>{@code UTF} → respuesta correspondiente (cadena de texto)</li>
 * </ul>
 * La escritura y lectura es directa mediante posición en archivo.
 */
public class PreguntaDAOArchivoBinario implements PreguntaDAO {

    private final String ruta;

    /**
     * Constructor que establece la ruta del archivo binario.
     * Si el archivo no existe, inicializa automáticamente 10 preguntas con respuestas vacías.
     *
     * @param ruta Ruta absoluta del archivo binario donde se almacenarán las preguntas.
     */
    public PreguntaDAOArchivoBinario(String ruta) {
        this.ruta = ruta;

        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
                for (int i = 1; i <= 10; i++) {
                    raf.writeInt(i);
                    raf.writeUTF("");
                }
            } catch (IOException e) {
                System.err.println("Error al inicializar archivo de preguntas: " + e.getMessage());
            }
        }
    }

    /**
     * Crea o actualiza una pregunta en el archivo.
     * Si el código ya existe, se reemplaza únicamente la respuesta.
     * Si no existe, se agrega al final (opcional y no recomendable si el sistema usa 10 fijas).
     *
     * @param pregunta Pregunta a guardar o actualizar.
     */
    @Override
    public void crear(Pregunta pregunta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                long posicion = raf.getFilePointer();
                int codigoArchivo = raf.readInt();
                raf.readUTF(); // Saltar la respuesta
                if (codigoArchivo == pregunta.getCodigo()) {
                    raf.seek(posicion + 4); // Reposicionar después del int
                    raf.writeUTF(pregunta.getRespuesta());
                    return;
                }
            }
            // Si no se encontró el código, agregar al final (caso no previsto para preguntas fijas)
            raf.writeInt(pregunta.getCodigo());
            raf.writeUTF(pregunta.getRespuesta());
        } catch (IOException e) {
            System.err.println("Error al crear/actualizar pregunta: " + e.getMessage());
        }
    }

    /**
     * Busca una pregunta por su código.
     *
     * @param codigo Código único de la pregunta.
     * @return Objeto {@link Pregunta} si se encuentra, o {@code null} si no.
     */
    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int codigoArchivo = raf.readInt();
                String respuesta = raf.readUTF();
                if (codigoArchivo == codigo) {
                    Pregunta p = new Pregunta(codigoArchivo);
                    p.setRespuesta(respuesta);
                    return p;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al buscar pregunta: " + e.getMessage());
        }
        return null;
    }

    /**
     * Lista todas las preguntas almacenadas en el archivo binario.
     *
     * @return Lista de objetos {@link Pregunta}.
     */
    @Override
    public List<Pregunta> listar() {
        List<Pregunta> preguntas = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int codigo = raf.readInt();
                String respuesta = raf.readUTF();
                Pregunta p = new Pregunta(codigo);
                p.setRespuesta(respuesta);
                preguntas.add(p);
            }
        } catch (IOException e) {
            System.err.println("Error al listar preguntas: " + e.getMessage());
        }
        return preguntas;
    }

    /**
     * Actualiza la respuesta de una pregunta ya existente en el archivo.
     *
     * @param codigo    Código de la pregunta a actualizar.
     * @param respuesta Nueva respuesta que se desea registrar.
     */
    @Override
    public void responder(int codigo, String respuesta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                long posicion = raf.getFilePointer();
                int codigoArchivo = raf.readInt();
                raf.readUTF(); // Saltar la respuesta antigua
                if (codigoArchivo == codigo) {
                    raf.seek(posicion + 4);
                    raf.writeUTF(respuesta);
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al responder pregunta: " + e.getMessage());
        }
    }
}

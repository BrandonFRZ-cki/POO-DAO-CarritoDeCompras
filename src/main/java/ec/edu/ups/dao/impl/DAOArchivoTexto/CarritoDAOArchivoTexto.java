package ec.edu.ups.dao.impl.DAOArchivoTexto;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Implementación del DAO para la entidad Carrito usando archivos de texto.
 * Cada carrito se almacena como una línea en el archivo, con atributos separados por comas.
 * Esta clase permite guardar, actualizar, eliminar y listar carritos desde un archivo.
 */
public class CarritoDAOArchivoTexto implements CarritoDAO {

    private final String ruta;
    private final Function<String, Usuario> obtenerUsuarioPorCedula;

    /**
     * Constructor del DAO que recibe la ruta del archivo y una función para obtener
     * un objeto Usuario a partir de su cédula.
     *
     * @param ruta Ruta absoluta o relativa del archivo donde se almacenan los carritos.
     * @param obtenerUsuarioPorCedula Función que recibe una cédula y retorna el Usuario correspondiente.
     */
    public CarritoDAOArchivoTexto(String ruta, Function<String, Usuario> obtenerUsuarioPorCedula) {
        this.ruta = ruta;
        this.obtenerUsuarioPorCedula = obtenerUsuarioPorCedula;

        // Crear el archivo si no existe
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el archivo: " + ruta, e);
            }
        }
    }


    /**
     * Guarda un nuevo carrito en el archivo.
     * El carrito se serializa usando el método `toArchivo()` de la clase Carrito.
     *
     * @param carrito Objeto Carrito a guardar.
     */
    @Override
    public void crear(Carrito carrito) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(carrito.toArchivo() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar carrito", e);
        }
    }

    /**
     * Busca un carrito en el archivo por su código único.
     *
     * @param codigo Código del carrito a buscar.
     * @return Carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : listarTodos()) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en el archivo.
     * Reemplaza el carrito con el mismo código.
     *
     * @param carrito Objeto Carrito con los datos actualizados.
     */
    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == carrito.getCodigo()) {
                lista.set(i, carrito);
                break;
            }
        }
        sobrescribirArchivo(lista);
    }

    /**
     * Elimina un carrito del archivo según su código.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> lista = listarTodos();
        lista.removeIf(c -> c.getCodigo() == codigo);
        sobrescribirArchivo(lista);
    }

    /**
     * Lee todos los carritos almacenados en el archivo.
     *
     * @return Lista de carritos encontrados en el archivo.
     */
    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String cedula = partes[1];
                Usuario usuario = obtenerUsuarioPorCedula.apply(cedula);
                if (usuario != null) {
                    lista.add(Carrito.fromArchivo(linea, usuario));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer archivo", e);
        }
        return lista;
    }

    /**
     * Método auxiliar privado para sobrescribir completamente el archivo
     * con una nueva lista de carritos. Se usa en actualización y eliminación.
     *
     * @param lista Lista de carritos a escribir en el archivo.
     */
    private void sobrescribirArchivo(List<Carrito> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Carrito c : lista) {
                bw.write(c.toArchivo() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al sobrescribir archivo", e);
        }
    }
}

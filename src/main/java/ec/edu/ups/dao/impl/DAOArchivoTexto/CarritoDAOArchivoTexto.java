package ec.edu.ups.dao.impl.DAOArchivoTexto;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementación del DAO para la entidad Carrito utilizando archivos de texto.
 * Cada carrito se representa como una línea en el archivo, con atributos separados por comas.
 * Los items se representan dentro de corchetes [] y el usuario ya está incluido dentro del carrito.
 */
public class CarritoDAOArchivoTexto implements CarritoDAO {

    private final String ruta;

    /**
     * Constructor del DAO que recibe la ruta del archivo.
     * @param ruta Ruta absoluta o relativa del archivo donde se almacenan los carritos.
     */
    public CarritoDAOArchivoTexto(String ruta) {
        this.ruta = ruta;

        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el archivo: " + ruta, e);
            }
        }
    }

    @Override
    public void crear(Carrito carrito) {
        if (buscarPorCodigo(carrito.getCodigo()) == null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
                bw.write(carrito.toString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar carrito", e);
            }
        }
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listarTodos()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

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

    @Override
    public void eliminar(int codigo) {
        List<Carrito> lista = listarTodos();
        lista.removeIf(c -> c.getCodigo() == codigo);
        sobrescribirArchivo(lista);
    }

    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Carrito carrito = fromArchivo(linea);
                if (carrito != null && buscarPorCodigo(carrito.getCodigo()) == null) {
                    lista.add(carrito);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer archivo de carritos", e);
        }
        return lista;
    }

    private Carrito fromArchivo(String linea) {
        try {
            String[] partes = linea.split(",", 5);
            int codigo = Integer.parseInt(partes[0]);
            double iva = Double.parseDouble(partes[1]);
            String fechaStr = partes[2];
            GregorianCalendar fecha = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(sdf.parse(fechaStr));
            String itemsStr = partes[4];

            Carrito carrito = new Carrito(null);
            carrito.setCodigo(codigo);
            carrito.setFechaCreacion(fecha);

            if (itemsStr.contains("[") && itemsStr.contains("]")) {
                String contenido = itemsStr.substring(itemsStr.indexOf("[") + 1, itemsStr.lastIndexOf("]"));
                String[] itemsSeparados = contenido.split(":");
                for (String itemLinea : itemsSeparados) {
                    if (itemLinea.isEmpty()) continue;
                    String[] datos = itemLinea.split(",");
                    int codigoProd = Integer.parseInt(datos[0]);
                    String nombreProd = datos[1];
                    double precioProd = Double.parseDouble(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);
                    Producto producto = new Producto(codigoProd, nombreProd, precioProd);
                    carrito.agregarProducto(producto, cantidad);
                }
            }
            return carrito;
        } catch (Exception e) {
            System.out.println("Error al parsear carrito: " + e.getMessage());
            return null;
        }
    }

    private void sobrescribirArchivo(List<Carrito> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Carrito c : lista) {
                bw.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al sobrescribir archivo", e);
        }
    }
}

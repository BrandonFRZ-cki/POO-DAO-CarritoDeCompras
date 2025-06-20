package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {
    Usuario autenticar(String username, String password);
    void crear(Usuario usuario);
    Usuario buscarPorUserName(String userName);
    void eliminar(String userName);
    void actualizar(Usuario usuario);
    List<Usuario> listarTodos();
    List<Usuario> listarPorRol(Rol rol);


}

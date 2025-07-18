package ec.edu.ups.modelo;

import ec.edu.ups.util.exceptions.CedulaValidationException;
import ec.edu.ups.util.exceptions.PasswordException;
import ec.edu.ups.util.exceptions.CorreoValidationException;
import ec.edu.ups.util.exceptions.TelefonoValidationException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Clase que representa un usuario del sistema.
 * Contiene datos personales, credenciales, listas de carritos y preguntas respondidas.
 * Incluye validaciones para la cédula y contraseña conforme a reglas locales.
 */
public class Usuario {

    private String username;
    private String contrasenia;
    private Rol rol;
    private List<Carrito> carritos;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private GregorianCalendar fechaNacimiento;
    private List<Pregunta> preguntasRespondidas;

    /**
     * Constructor por defecto que inicializa las listas vacías y la fecha actual como fecha de nacimiento.
     */
    public Usuario() {
        this.carritos = new ArrayList<>();
        this.preguntasRespondidas = new ArrayList<>();
        this.fechaNacimiento = new GregorianCalendar();
    }

    /**
     * Constructor principal con todos los atributos obligatorios del usuario.
     *
     * @param nombreDeUsuario Cédula del usuario (10 dígitos con validación).
     * @param contrasenia Contraseña con validaciones.
     * @param rol Rol del usuario (ADMINISTRADOR o USUARIO).
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param email Correo electrónico.
     * @param telefono Teléfono del usuario.
     */
    public Usuario(String nombreDeUsuario, String contrasenia, Rol rol, String nombre, String apellido, String email, String telefono) {
        setUsername(nombreDeUsuario);
        setContrasenia(contrasenia);
        setEmail(email);
        setTelefono(telefono);
        this.carritos = new ArrayList<>();
        this.preguntasRespondidas = new ArrayList<>();
        this.fechaNacimiento = new GregorianCalendar();
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Devuelve la cédula (username) del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Asigna y valida la cédula ecuatoriana del usuario.
     *
     * @param username Cédula de 10 dígitos.
     * @throws CedulaValidationException si no cumple las reglas de validación.
     */
    public void setUsername(String username) {
        if (username != null && username.length() == 10) {
            try {
                for (int i = 0; i < username.length(); i++) {
                    Integer.parseInt(username.charAt(i) + "");
                }
            } catch (NumberFormatException e) {
                throw new CedulaValidationException("79"); // Solo números
            }

            int[] multiplicadores = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;

            for (int i = 0; i < 9; i++) {
                int digito = Integer.parseInt(username.charAt(i) + "");
                int producto = digito * multiplicadores[i];
                if (producto >= 10) producto -= 9;
                suma += producto;
            }

            int digitoVerificador = Integer.parseInt(username.charAt(9) + "");
            int resultado = (suma % 10 == 0) ? 0 : 10 - (suma % 10);

            if (resultado != digitoVerificador) throw new CedulaValidationException("80");
            int provincia = Integer.parseInt(username.substring(0, 2));
            if (provincia < 0 || provincia > 24) throw new CedulaValidationException("81");
            int tercerDigito = Integer.parseInt(username.charAt(2) + "");
            if (tercerDigito >= 6) throw new CedulaValidationException("82");

            this.username = username;
        } else {
            throw new CedulaValidationException("78"); // No tiene 10 dígitos
        }
    }

    /**
     * Devuelve la contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Asigna la contraseña del usuario con validaciones de seguridad:
     * mínimo 6 caracteres, al menos una mayúscula, una minúscula y un carácter especial (@ _ -).
     *
     * @param contrasenia Contraseña ingresada.
     * @throws PasswordException si no cumple las reglas.
     */
    public void setContrasenia(String contrasenia) {
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneEspecial = false;

        if (contrasenia != null && contrasenia.length() >= 6) {
            for (int i = 0; i < contrasenia.length(); i++) {
                char c = contrasenia.charAt(i);
                if (Character.isUpperCase(c)) tieneMayuscula = true;
                else if (Character.isLowerCase(c)) tieneMinuscula = true;
                else if (c == '@' || c == '_' || c == '-') tieneEspecial = true;
            }
            if (!tieneMayuscula) throw new PasswordException("84");
            if (!tieneMinuscula) throw new PasswordException("85");
            if (!tieneEspecial)  throw new PasswordException("86");

            this.contrasenia = contrasenia;
        } else {
            throw new PasswordException("83");
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void agregarCarrito(Carrito carrito) {
        this.carritos.add(carrito);
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new CorreoValidationException("87"); // Falta @
        }

        boolean contieneArroba = false;
        boolean contienePunto = false;

        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (c == '@') contieneArroba = true;
            if (c == '.') contienePunto = true;
        }

        if (!contieneArroba) throw new CorreoValidationException("87");
        if (!contienePunto) throw new CorreoValidationException("88");

        this.email = email;
    }




    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() != 10) {
            throw new TelefonoValidationException("90"); // No tiene 10 dígitos
        }

        for (int i = 0; i < telefono.length(); i++) {
            char c = telefono.charAt(i);
            if (!Character.isDigit(c)) {
                throw new TelefonoValidationException("89"); // No es numérico
            }
        }

        this.telefono = telefono;
    }




    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Pregunta> getPreguntasRespondidas() {
        return preguntasRespondidas;
    }

    public void agregarPregunta(Pregunta pregunta) {
        this.preguntasRespondidas.add(pregunta);
    }

    public void setPreguntasRespondidas(List<Pregunta> preguntasRespondidas) {
        this.preguntasRespondidas = preguntasRespondidas;
    }

    @Override
    public String toString() {
        return username + "," +
                contrasenia + "," +
                rol + "," +
                nombre + "," +
                apellido + "," +
                email + "," +
                telefono + "<" + carritosParaArchivo() + ">" + "<" + preguntasParaArchivo() + ">";
    }

    private String preguntasParaArchivo() {
        if (preguntasRespondidas.isEmpty()) {
            return "sin preguntas";
        }
        String texto = "";
        for (Pregunta p : preguntasRespondidas) {
            texto += p.getCodigo() + ":" + p.getRespuesta() + "|";
        }
        return texto;
    }

    private String carritosParaArchivo(){
        String carritosString = "<";
        for (Carrito carrito : carritos) {
            carritosString += carrito+":";
        }
        carritosString +=  ">";
        return "sin carritos";
    }

}

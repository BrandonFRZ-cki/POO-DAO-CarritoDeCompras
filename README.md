# 🛒 Carrito de Compras — Proyecto Java Swing (MVC | DAO | SOLID | MDI | I18N)

Este proyecto fue desarrollado como parte de la **Práctica 2** de la asignatura **Programación Orientada a Objetos** en la **Universidad Politécnica Salesiana**, período académico 66.

Su principal objetivo es demostrar la aplicación de principios de diseño y buenas prácticas de ingeniería de software a través del desarrollo de una aplicación educativa con interfaz gráfica utilizando **Java Swing**, arquitecturas desacopladas (**MVC**, **DAO**, **SOLID**), e internacionalización (**i18n**) con soporte dinámico multilenguaje.

---

## 🎯 Objetivo General

Construir una aplicación funcional que simule un sistema de **carrito de compras**, incluyendo el manejo de usuarios, productos y carritos con almacenamiento flexible, control de acceso por rol, validaciones y diseño modular.

---

## 🧩 Funcionalidades Clave

- 🔐 Autenticación segura de usuarios con validación de cédula, contraseña, correo y teléfono.
- 🔁 Recuperación de contraseña mediante preguntas de seguridad personalizadas.
- 👥 Gestión completa de usuarios (crear, listar, actualizar, eliminar).
- 📦 CRUD de productos con verificación de existencia y edición en tiempo real.
- 🛍️ Carritos con cálculo automático de subtotal, IVA (12%) y total.
- 🌍 Cambio dinámico de idioma: Español 🇪🇸, Inglés 🇬🇧 y Francés 🇫🇷.
- 👨‍💼 Control de acceso por rol: `ADMINISTRADOR` y `USUARIO`.
- 🖼️ Interfaz gráfica multiventana con `JDesktopPane` (`MDI`) e íconos personalizados.
- 📁 Almacenamiento configurable: en memoria, en archivos `.txt` o binarios `.dat`.
- 🌐 Internacionalización completa con `ResourceBundle`, `.properties`, `DateFormat` y `NumberFormat`.

---

## 🛠️ Tecnologías y Estructura

- **Java 21**
- **Java Swing** — GUI avanzada
- **IntelliJ IDEA** — IDE recomendado (con diseñador de formularios)
- **Arquitectura modular:**
  ```
  ├── modelo       → Clases de dominio (Usuario, Producto, Carrito, etc.)
  ├── dao          → Interfaces DAO + implementaciones (Memoria / Archivos)
  ├── controlador  → Lógica de negocio conectada a las vistas
  ├── vista        → Ventanas Swing organizadas por módulo
  ├── util         → Validadores, internacionalización, formateadores
  └── resources    → Archivos .properties (es, en, fr) e íconos personalizados
  ```

---

## 📐 Patrones y Principios Aplicados

| Principio / Patrón | Aplicación específica |
|--------------------|------------------------|
| **MVC**            | Separación entre lógica (Controller), datos (Model) e interfaz (View) |
| **DAO**            | Acceso a datos desacoplado, con opción de persistencia múltiple |
| **SOLID**          | SRP, OCP y DIP aplicados a modelos y DAOs |
| **MDI**            | Interfaz multiventana con `JInternalFrame` |
| **i18n**           | Soporte multilenguaje con carga dinámica de recursos |

---

## 🚀 Cómo ejecutar

1. Ejecuta la clase `Main.java`.
2. Selecciona el idioma y el tipo de almacenamiento:
   - **Memoria** (no persistente)
   - **Archivos** (elige carpeta de almacenamiento)
3. Prueba las funcionalidades desde el menú principal.
4. Cambia de idioma dinámicamente y verifica los textos, fechas y números.
5. Accede como `ADMINISTRADOR` para ver todas las opciones, o como `USUARIO` para funcionalidades limitadas.

---

## 📂 Repositorio del proyecto

🔗 [github.com/BrandonFRZ-cki/POO-DAO-CarritoDeCompras](https://github.com/BrandonFRZ-cki/POO-DAO-CarritoDeCompras)

Incluye:
- Código fuente completo y estructurado
- Archivos `.properties` traducidos (es, en, fr)
- Iconos personalizados
- Documentación y javadoc

---

## 👨‍🏫 Créditos

- **Autor:** Brandon Fernando Rivera Zambrano  
- **Carrera:** Ingeniería en Computación  
- **Materia:** Programación Orientada a Objetos  
- **Docente:** Gabriel León → GitHub: [gabusleon](https://github.com/gabusleon)

---

## 📜 Licencia

Este proyecto ha sido desarrollado con fines **académicos y demostrativos**. Todos los derechos reservados a su autor.

---

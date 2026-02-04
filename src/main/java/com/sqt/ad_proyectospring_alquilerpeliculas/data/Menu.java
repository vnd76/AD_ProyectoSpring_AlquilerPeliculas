package com.sqt.ad_proyectospring_alquilerpeliculas.data;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Menu implements CommandLineRunner {

    private final DatosDePrueba datos;
    private final CategoriaRepository categoriaRepository;
    private final IdiomaRepository idiomaRepository;
    private final SuscriptorRepository suscriptorRepository;
    private final ProductoRepository productoRepository;
    private final AlquilerRepository alquilerRepository;

    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void run(String... args) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                          ║");
        System.out.println("║      SISTEMA DE ALQUILER DE PELÍCULAS Y SERIES            ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();

        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> inicializarDatos();
                case 2 -> menuCategorias();
                case 3 -> menuIdiomas();
                case 4 -> menuSuscriptores();
                case 5 -> menuProductos();
                case 6 -> menuAlquileres();
                case 7 -> mostrarEstadisticas();
                case 8 -> mostrarReportes();
                case 9 -> {
                    System.out.println("Gracias por usar el sistema. Hasta pronto.");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("                  MENÚ PRINCIPAL                        ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  1. Inicializar datos de prueba");
        System.out.println("  2. Gestión de Categorías");
        System.out.println("  3. Gestión de Idiomas");
        System.out.println("  4. Gestión de Suscriptores");
        System.out.println("  5. Gestión de Productos");
        System.out.println("  6. Gestión de Alquileres");
        System.out.println("  7. Ver Estadísticas");
        System.out.println("  8. Ver Reportes");
        System.out.println("  9. Salir");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.print("Elige una opción: ");
    }

    // ==================== INICIALIZAR DATOS ====================
    private void inicializarDatos() {
        System.out.println("Cargando datos de prueba...");
        datos.cargarDatosIniciales();
    }

    // ==================== MENÚ CATEGORÍAS ====================
    private void menuCategorias() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("              GESTIÓN DE CATEGORÍAS                     ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  1. Ver todas las categorías");
        System.out.println("  2. Crear nueva categoría");
        System.out.println("  3. Actualizar categoría");
        System.out.println("  4. Eliminar categoría");
        System.out.println("  5. Volver al menú principal");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.print("Elige una opción: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> listarCategorias();
            case 2 -> crearCategoria();
            case 3 -> actualizarCategoria();
            case 4 -> eliminarCategoria();
        }
    }

    private void listarCategorias() {
        System.out.println("=== LISTA DE CATEGORÍAS ===");
        List<Categoria> categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            System.out.println("-------------------------------------------");
            for (Categoria cat : categorias) {
                System.out.printf("ID: %d - Nombre: %s%n", cat.getId(), cat.getNombre());
            }
            System.out.println("-------------------------------------------");
            System.out.println("Total: " + categorias.size() + " categorías");
        }
        System.out.println();
    }

    private void crearCategoria() {
        System.out.print("Ingresa el nombre de la categoría: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoriaRepository.save(categoria);

        System.out.println("Categoría creada exitosamente.");
    }

    private void actualizarCategoria() {
        System.out.print("Ingresa el ID de la categoría a actualizar: ");
        long id = leerOpcionLong();

        categoriaRepository.findById(id).ifPresentOrElse(
                categoria -> {
                    System.out.println("Nombre actual: " + categoria.getNombre());
                    System.out.print("Ingresa el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine().trim();

                    if (!nuevoNombre.isEmpty()) {
                        categoria.setNombre(nuevoNombre);
                        categoriaRepository.save(categoria);
                        System.out.println("Categoría actualizada exitosamente.");
                    } else {
                        System.out.println("El nombre no puede estar vacío.");
                    }
                },
                () -> System.out.println("Categoría no encontrada.")
        );
    }

    private void eliminarCategoria() {
        System.out.print("Ingresa el ID de la categoría a eliminar: ");
        long id = leerOpcionLong();

        categoriaRepository.findById(id).ifPresentOrElse(
                categoria -> {
                    try {
                        categoriaRepository.delete(categoria);
                        System.out.println("Categoría eliminada exitosamente.");
                    } catch (Exception e) {
                        System.out.println(" No se puede eliminar la categoría porque está siendo utilizada.");
                    }
                },
                () -> System.out.println("Categoría no encontrada.")
        );
    }

    // ==================== MENÚ IDIOMAS ====================
    private void menuIdiomas() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("               GESTIÓN DE IDIOMAS                       ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  1. Ver todos los idiomas");
        System.out.println("  2. Crear nuevo idioma");
        System.out.println("  3. Actualizar idioma");
        System.out.println("  4. Eliminar idioma");
        System.out.println("  5. Volver al menú principal");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.print("Elige una opción: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> listarIdiomas();
            case 2 -> crearIdioma();
            case 3 -> actualizarIdioma();
            case 4 -> eliminarIdioma();
        }
    }

    private void listarIdiomas() {
        System.out.println("=== LISTA DE IDIOMAS ===");
        List<Idioma> idiomas = idiomaRepository.findAll();

        if (idiomas.isEmpty()) {
            System.out.println("No hay idiomas registrados.");
        } else {
            System.out.println("-------------------------------------------");
            for (Idioma idioma : idiomas) {
                System.out.printf("ID: %d - Nombre: %s%n", idioma.getId(), idioma.getNombre());
            }
            System.out.println("-------------------------------------------");
            System.out.println("Total: " + idiomas.size() + " idiomas");
        }
        System.out.println();
    }

    private void crearIdioma() {
        System.out.print("Ingresa el nombre del idioma: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        Idioma idioma = new Idioma();
        idioma.setNombre(nombre);
        idiomaRepository.save(idioma);

        System.out.println("Idioma creado exitosamente.");
    }

    private void actualizarIdioma() {
        System.out.print("Ingresa el ID del idioma a actualizar: ");
        long id = leerOpcionLong();

        idiomaRepository.findById(id).ifPresentOrElse(
                idioma -> {
                    System.out.println("Nombre actual: " + idioma.getNombre());
                    System.out.print("Ingresa el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine().trim();

                    if (!nuevoNombre.isEmpty()) {
                        idioma.setNombre(nuevoNombre);
                        idiomaRepository.save(idioma);
                        System.out.println("Idioma actualizado exitosamente.");
                    } else {
                        System.out.println("El nombre no puede estar vacío.");
                    }
                },
                () -> System.out.println("Idioma no encontrado.")
        );
    }

    private void eliminarIdioma() {
        System.out.print("Ingresa el ID del idioma a eliminar: ");
        long id = leerOpcionLong();

        idiomaRepository.findById(id).ifPresentOrElse(
                idioma -> {
                    try {
                        idiomaRepository.delete(idioma);
                        System.out.println("Idioma eliminado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("No se puede eliminar el idioma porque está siendo utilizado.");
                    }
                },
                () -> System.out.println("Idioma no encontrado.")
        );
    }

    // ==================== MENÚ SUSCRIPTORES ====================
    private void menuSuscriptores() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("             GESTIÓN DE SUSCRIPTORES                    ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  1. Ver todos los suscriptores");
        System.out.println("  2. Ver suscriptores por plan");
        System.out.println("  3. Crear nuevo suscriptor");
        System.out.println("  4. Actualizar suscriptor");
        System.out.println("  5. Eliminar suscriptor");
        System.out.println("  6. Volver al menú principal");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.print("Elige una opción: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> listarSuscriptores();
            case 2 -> listarSuscriptoresPorPlan();
            case 3 -> crearSuscriptor();
            case 4 -> actualizarSuscriptor();
            case 5 -> eliminarSuscriptor();
        }
    }

    private void listarSuscriptores() {
        System.out.println("=== LISTA DE SUSCRIPTORES ===");
        List<Suscriptor> suscriptores = suscriptorRepository.findAll();

        if (suscriptores.isEmpty()) {
            System.out.println("No hay suscriptores registrados.");
        } else {
            System.out.println("-------------------------------------------");
            for (Suscriptor sus : suscriptores) {
                System.out.printf("ID: %d%n", sus.getId());
                System.out.printf("Email: %s%n", sus.getCorreoElectronico());
                System.out.printf("Plan: %s%n", sus.getPlanContratado());
                int dias = sus.getPlanContratado() == Suscriptor.PlanContratado.PREMIUM ? 28 : 14;
                System.out.printf("Periodo: %d días%n", dias);
                System.out.println("-------------------------------------------");
            }
            System.out.println("Total: " + suscriptores.size() + " suscriptores");
        }
        System.out.println();
    }

    private void listarSuscriptoresPorPlan() {
        System.out.println("=== SUSCRIPTORES POR PLAN ===");

        long basicos = suscriptorRepository.findAll().stream()
                .filter(s -> s.getPlanContratado() == Suscriptor.PlanContratado.BASICO)
                .count();

        long premium = suscriptorRepository.findAll().stream()
                .filter(s -> s.getPlanContratado() == Suscriptor.PlanContratado.PREMIUM)
                .count();

        System.out.println("-------------------------------------------");
        System.out.printf("Plan BÁSICO (14 días): %d suscriptores%n", basicos);
        System.out.printf("Plan PREMIUM (28 días): %d suscriptores%n", premium);
        System.out.println("-------------------------------------------");
        System.out.printf("Total: %d suscriptores%n", basicos + premium);
        System.out.println();
    }

    private void crearSuscriptor() {
        System.out.print("Ingresa el correo electrónico: ");
        String email = scanner.nextLine().trim();

        System.out.println("Ingresa una contraseña: ");
        String contrasenia = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("El correo no puede estar vacío.");
            return;
        }

        System.out.println("Selecciona el plan:");
        System.out.println("1. BASICO (14 días)");
        System.out.println("2. PREMIUM (28 días)");
        System.out.print("Opción: ");
        int planOpcion = leerOpcion();

        Suscriptor.PlanContratado plan = planOpcion == 2
                ? Suscriptor.PlanContratado.PREMIUM
                : Suscriptor.PlanContratado.BASICO;

        Suscriptor suscriptor = new Suscriptor();
        suscriptor.setCorreoElectronico(email);
        suscriptor.setContrasenia(contrasenia);
        suscriptor.setPlanContratado(plan);
        suscriptorRepository.save(suscriptor);

        System.out.println("Suscriptor creado exitosamente.");
    }

    private void actualizarSuscriptor() {
        System.out.print("Ingresa el ID del suscriptor a actualizar: ");
        long id = leerOpcionLong();

        suscriptorRepository.findById(id).ifPresentOrElse(
                suscriptor -> {
                    System.out.println("Email actual: " + suscriptor.getCorreoElectronico());
                    System.out.print("Ingresa el nuevo email (o presiona Enter para mantener): ");
                    String nuevoEmail = scanner.nextLine().trim();

                    if (!nuevoEmail.isEmpty()) {
                        suscriptor.setCorreoElectronico(nuevoEmail);
                    }

                    System.out.println("Plan actual: " + suscriptor.getPlanContratado());
                    System.out.println("Cambiar plan:");
                    System.out.println("1. BASICO (14 días)");
                    System.out.println("2. PREMIUM (28 días)");
                    System.out.println("3. Mantener actual");
                    System.out.print("Opción: ");
                    int planOpcion = leerOpcion();

                    if (planOpcion == 1) {
                        suscriptor.setPlanContratado(Suscriptor.PlanContratado.BASICO);
                    } else if (planOpcion == 2) {
                        suscriptor.setPlanContratado(Suscriptor.PlanContratado.PREMIUM);
                    }

                    suscriptorRepository.save(suscriptor);
                    System.out.println("Suscriptor actualizado exitosamente.");
                },
                () -> System.out.println("Suscriptor no encontrado.")
        );
    }

    private void eliminarSuscriptor() {
        System.out.print("Ingresa el ID del suscriptor a eliminar: ");
        long id = leerOpcionLong();

        suscriptorRepository.findById(id).ifPresentOrElse(
                suscriptor -> {
                    try {
                        suscriptorRepository.delete(suscriptor);
                        System.out.println("Suscriptor eliminado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("No se puede eliminar el suscriptor porque tiene alquileres asociados.");
                    }
                },
                () -> System.out.println("Suscriptor no encontrado.")
        );
    }

    // ==================== MENÚ PRODUCTOS ====================
    private void menuProductos() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("              GESTIÓN DE PRODUCTOS                      ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  1. Ver todos los productos");
        System.out.println("  2. Ver solo películas");
        System.out.println("  3. Ver solo series");
        System.out.println("  4. Ver detalles de un producto");
        System.out.println("  5. Actualizar producto");
        System.out.println("  6. Eliminar producto");
        System.out.println("  7. Volver al menú principal");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.print("Elige una opción: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> listarProductos(null);
            case 2 -> listarProductos(Producto.Tipo.PELICULA);
            case 3 -> listarProductos(Producto.Tipo.SERIE);
            case 4 -> verDetalleProducto();
            case 5 -> actualizarProducto();
            case 6 -> eliminarProducto();
        }
    }

    private void listarProductos(Producto.Tipo tipo) {
        String titulo = tipo == null ? "TODOS LOS PRODUCTOS"
                : tipo == Producto.Tipo.PELICULA ? "PELÍCULAS" : "SERIES";

        System.out.println("=== " + titulo + " ===");

        List<Producto> productos = productoRepository.findAll();

        if (tipo != null) {
            productos = productos.stream()
                    .filter(p -> p.getTipoProducto() == tipo)
                    .toList();
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("-------------------------------------------");
            for (Producto prod : productos) {
                System.out.printf("ID: %d - %s (%d)%n",
                        prod.getId(), prod.getTitulo(), prod.getAnioPublicacion());
            }
            System.out.println("-------------------------------------------");
            System.out.println("Total: " + productos.size() + " productos");
        }
        System.out.println();
    }

    private void verDetalleProducto() {
        System.out.print("Ingresa el ID del producto: ");
        long id = leerOpcionLong();

        productoRepository.findById(id).ifPresentOrElse(
                producto -> {
                    System.out.println("=== DETALLES DEL PRODUCTO ===");
                    System.out.println("-------------------------------------------");
                    System.out.printf("ID: %d%n", producto.getId());
                    System.out.printf("Título: %s%n", producto.getTitulo());
                    System.out.printf("Tipo: %s%n", producto.getTipoProducto());
                    System.out.printf("Año: %d%n", producto.getAnioPublicacion());
                    System.out.printf("Descripción: %s%n", producto.getDescripcion());
                    System.out.print("Categorías: ");
                    producto.getCategorias().forEach(c -> System.out.print(c.getNombre() + " "));
                    System.out.println();
                    System.out.print("Idiomas: ");
                    producto.getIdiomas().forEach(i -> System.out.print(i.getNombre() + " "));
                    System.out.println("-------------------------------------------");
                },
                () -> System.out.println("Producto no encontrado.")
        );
    }

    private void actualizarProducto() {
        System.out.print("Ingresa el ID del producto a actualizar: ");
        long id = leerOpcionLong();

        productoRepository.findById(id).ifPresentOrElse(
                producto -> {
                    System.out.println("Título actual: " + producto.getTitulo());
                    System.out.print("Ingresa el nuevo título (o presiona Enter para mantener): ");
                    String nuevoTitulo = scanner.nextLine().trim();

                    if (!nuevoTitulo.isEmpty()) {
                        producto.setTitulo(nuevoTitulo);
                    }

                    System.out.println("Año actual: " + producto.getAnioPublicacion());
                    System.out.print("Ingresa el nuevo año (o presiona Enter para mantener): ");
                    String anioStr = scanner.nextLine().trim();

                    if (!anioStr.isEmpty()) {
                        try {
                            int nuevoAnio = Integer.parseInt(anioStr);
                            producto.setAnioPublicacion(nuevoAnio);
                        } catch (NumberFormatException e) {
                            System.out.println("Año no válido, se mantiene el actual.");
                        }
                    }

                    System.out.println("Descripción actual: " + producto.getDescripcion());
                    System.out.print("Ingresa la nueva descripción (o presiona Enter para mantener): ");
                    String nuevaDesc = scanner.nextLine().trim();

                    if (!nuevaDesc.isEmpty()) {
                        producto.setDescripcion(nuevaDesc);
                    }

                    productoRepository.save(producto);
                    System.out.println("Producto actualizado exitosamente.");
                },
                () -> System.out.println("Producto no encontrado.")
        );
    }

    private void eliminarProducto() {
        System.out.print("Ingresa el ID del producto a eliminar: ");
        long id = leerOpcionLong();

        productoRepository.findById(id).ifPresentOrElse(
                producto -> {
                    try {
                        productoRepository.delete(producto);
                        System.out.println("Producto eliminado exitosamente.");
                    } catch (Exception e) {
                        System.out.println(" No se puede eliminar el producto porque está siendo utilizado.");
                    }
                },
                () -> System.out.println("Producto no encontrado.")
        );
    }

    // ==================== MENÚ ALQUILERES ====================
    private void menuAlquileres() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("              GESTIÓN DE ALQUILERES                     ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  1. Ver todos los alquileres");
        System.out.println("  2. Ver alquileres activos");
        System.out.println("  3. Ver alquileres por suscriptor");
        System.out.println("  4. Eliminar alquiler");
        System.out.println("  5. Volver al menú principal");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.print("Elige una opción: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> listarAlquileres();
            case 2 -> listarAlquileresActivos();
            case 3 -> listarAlquileresPorSuscriptor();
            case 4 -> eliminarAlquiler();
        }
    }

    private void listarAlquileres() {
        System.out.println("=== LISTA DE ALQUILERES ===");
        List<Alquiler> alquileres = alquilerRepository.findAll();

        if (alquileres.isEmpty()) {
            System.out.println("No hay alquileres registrados.");
        } else {
            System.out.println("-------------------------------------------");
            for (Alquiler alq : alquileres) {
                System.out.printf("ID: %d%n", alq.getId());
                System.out.printf("Suscriptor: %s%n", alq.getSuscriptor().getCorreoElectronico());
                System.out.printf("Plan: %s%n", alq.getSuscriptor().getPlanContratado());
                System.out.printf("Inicio: %s%n", alq.getFechaInicio().format(formatter));
                System.out.printf("Fin: %s%n", alq.getFechaFin().format(formatter));
                int dias = alq.getSuscriptor().getPlanContratado() == Suscriptor.PlanContratado.PREMIUM ? 28 : 14;
                System.out.printf("Duración: %d días%n", dias);
                boolean activo = !LocalDate.now().isAfter(alq.getFechaFin());
                System.out.printf("Estado: %s%n", activo ? "ACTIVO" : "VENCIDO");
                System.out.println("-------------------------------------------");
            }
            System.out.println("Total: " + alquileres.size() + " alquileres");
        }
        System.out.println();
    }

    private void listarAlquileresActivos() {
        System.out.println("=== ALQUILERES ACTIVOS ===");
        List<Alquiler> alquileres = alquilerRepository.findAll().stream()
                .filter(a -> !LocalDate.now().isAfter(a.getFechaFin()))
                .toList();

        if (alquileres.isEmpty()) {
            System.out.println("No hay alquileres activos.");
        } else {
            System.out.println("-------------------------------------------");
            for (Alquiler alq : alquileres) {
                System.out.printf("ID: %d - %s%n",
                        alq.getId(), alq.getSuscriptor().getCorreoElectronico());
                System.out.printf("Vence: %s%n", alq.getFechaFin().format(formatter));
                System.out.println("-------------------------------------------");
            }
            System.out.println("Total: " + alquileres.size() + " alquileres activos");
        }
        System.out.println();
    }

    private void listarAlquileresPorSuscriptor() {
        System.out.print("Ingresa el ID del suscriptor: ");
        long id = leerOpcionLong();

        suscriptorRepository.findById(id).ifPresentOrElse(
                suscriptor -> {
                    List<Alquiler> alquileres = alquilerRepository.findBySuscriptor(suscriptor);

                    System.out.println("=== ALQUILERES DE " + suscriptor.getCorreoElectronico() + " ===");

                    if (alquileres.isEmpty()) {
                        System.out.println("Este suscriptor no tiene alquileres.");
                    } else {
                        System.out.println("-------------------------------------------");
                        for (Alquiler alq : alquileres) {
                            System.out.printf("ID: %d%n", alq.getId());
                            System.out.printf("Inicio: %s - Fin: %s%n",
                                    alq.getFechaInicio().format(formatter),
                                    alq.getFechaFin().format(formatter));
                            boolean activo = !LocalDate.now().isAfter(alq.getFechaFin());
                            System.out.printf("Estado: %s%n", activo ? "ACTIVO" : "VENCIDO");
                            System.out.println("-------------------------------------------");
                        }
                        System.out.println("Total: " + alquileres.size() + " alquileres");
                    }
                },
                () -> System.out.println("Suscriptor no encontrado.")
        );
        System.out.println();
    }

    private void eliminarAlquiler() {
        System.out.print("Ingresa el ID del alquiler a eliminar: ");
        long id = leerOpcionLong();

        alquilerRepository.findById(id).ifPresentOrElse(
                alquiler -> {
                    alquilerRepository.delete(alquiler);
                    System.out.println(" Alquiler eliminado exitosamente.");
                },
                () -> System.out.println("Alquiler no encontrado.")
        );
    }

    // ==================== ESTADÍSTICAS ====================
    private void mostrarEstadisticas() {
        datos.mostrarEstadisticas();
    }

    // ==================== REPORTES ====================
    private void mostrarReportes() {
        System.out.println("=== REPORTES DEL SISTEMA ===");
        System.out.println("════════════════════════════════════════════════════════");

        long peliculas = productoRepository.findAll().stream()
                .filter(p -> p.getTipoProducto() == Producto.Tipo.PELICULA)
                .count();
        long series = productoRepository.findAll().stream()
                .filter(p -> p.getTipoProducto() == Producto.Tipo.SERIE)
                .count();

        System.out.println("PRODUCTOS POR TIPO:");
        System.out.println("Películas: " + peliculas);
        System.out.println("Series: " + series);

        List<Alquiler> alquileres = alquilerRepository.findAll();
        long alquileresBasico = alquileres.stream()
                .filter(a -> a.getSuscriptor().getPlanContratado() == Suscriptor.PlanContratado.BASICO)
                .count();
        long alquileresPremium = alquileres.stream()
                .filter(a -> a.getSuscriptor().getPlanContratado() == Suscriptor.PlanContratado.PREMIUM)
                .count();

        System.out.println("ALQUILERES POR PLAN:");
        System.out.println("Plan BÁSICO: " + alquileresBasico);
        System.out.println("Plan PREMIUM: " + alquileresPremium);

        long activos = alquileres.stream()
                .filter(a -> !LocalDate.now().isAfter(a.getFechaFin()))
                .count();
        long vencidos = alquileres.size() - activos;

        System.out.println("ESTADO DE ALQUILERES:");
        System.out.println("Activos: " + activos);
        System.out.println("Vencidos: " + vencidos);

        System.out.println("═══════════════════════════════════════════════════════");
    }

    // ==================== UTILIDADES ====================
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private long leerOpcionLong() {
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
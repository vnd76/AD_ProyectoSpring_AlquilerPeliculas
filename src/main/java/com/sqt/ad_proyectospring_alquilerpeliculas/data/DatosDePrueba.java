package com.sqt.ad_proyectospring_alquilerpeliculas.data;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatosDePrueba {
    private final CategoriaRepository categoriaRepository;
    private final IdiomaRepository idiomaRepository;
    private final SuscriptorRepository suscriptorRepository;
    private final ProductoRepository productoRepository;
    private final AlquilerRepository alquilerRepository;

    public void cargarDatosIniciales() {
        System.out.println("Iniciando carga de datos...");

        // Limpiar datos existentes
        alquilerRepository.deleteAll();
        productoRepository.deleteAll();
        suscriptorRepository.deleteAll();
        categoriaRepository.deleteAll();
        idiomaRepository.deleteAll();

        // 1. Crear Categorías
        System.out.println("Creando categorías...");
        Categoria accion = new Categoria();
        accion.setNombre("Acción");
        accion = categoriaRepository.save(accion);

        Categoria drama = new Categoria();
        drama.setNombre("Drama");
        drama = categoriaRepository.save(drama);

        Categoria comedia = new Categoria();
        comedia.setNombre("Comedia");
        comedia = categoriaRepository.save(comedia);

        Categoria cienciaFiccion = new Categoria();
        cienciaFiccion.setNombre("Ciencia Ficción");
        cienciaFiccion = categoriaRepository.save(cienciaFiccion);

        Categoria terror = new Categoria();
        terror.setNombre("Terror");
        terror = categoriaRepository.save(terror);

        System.out.println(categoriaRepository.count() + " categorías creadas");

        // 2. Crear Idiomas
        System.out.println("\n🌐 Creando idiomas...");
        Idioma espanol = new Idioma();
        espanol.setNombre("Español");
        espanol = idiomaRepository.save(espanol);

        Idioma ingles = new Idioma();
        ingles.setNombre("Inglés");
        ingles = idiomaRepository.save(ingles);

        Idioma frances = new Idioma();
        frances.setNombre("Francés");
        frances = idiomaRepository.save(frances);

        Idioma italiano = new Idioma();
        italiano.setNombre("Italiano");
        italiano = idiomaRepository.save(italiano);

        System.out.println(idiomaRepository.count() + " idiomas creados");

        // 3. Crear Suscriptores
        System.out.println("Creando suscriptores...");

        Suscriptor susBasico1 = Suscriptor.builder()
                .correoElectronico("juan.perez@email.com")
                .contrasenia("password123")
                .planContratado(Suscriptor.PlanContratado.BASICO)
                .build();
        susBasico1 = suscriptorRepository.save(susBasico1);

        Suscriptor susBasico2 = Suscriptor.builder()
                .correoElectronico("maria.garcia@email.com")
                .contrasenia("password456")
                .planContratado(Suscriptor.PlanContratado.BASICO)
                .build();
        susBasico2 = suscriptorRepository.save(susBasico2);

        Suscriptor susPremium1 = Suscriptor.builder()
                .correoElectronico("carlos.rodriguez@email.com")
                .contrasenia("password789")
                .planContratado(Suscriptor.PlanContratado.PREMIUM)
                .build();
        susPremium1 = suscriptorRepository.save(susPremium1);

        Suscriptor susPremium2 = Suscriptor.builder()
                .correoElectronico("ana.martinez@email.com")
                .contrasenia("password012")
                .planContratado(Suscriptor.PlanContratado.PREMIUM)
                .build();
        susPremium2 = suscriptorRepository.save(susPremium2);

        System.out.println(suscriptorRepository.count() + " suscriptores creados");
        System.out.println("   - plan BÁSICO (14 días de alquiler)");
        System.out.println("   - plan PREMIUM (28 días de alquiler)");

        // 4. Crear Productos (Películas y Series)
        System.out.println(" Creando productos...");

        // Película 1
        Producto pelicula1 = new Producto();
        pelicula1.setTipoProducto(Producto.Tipo.PELICULA);
        pelicula1.setTitulo("El Señor de los Anillos: La Comunidad del Anillo");
        pelicula1.setDescripcion("Una épica aventura en la Tierra Media");
        pelicula1.setAnioPublicacion(2001);
        Set<Categoria> cats1 = new HashSet<>();
        cats1.add(accion);
        cats1.add(drama);
        pelicula1.setCategorias(cats1);
        Set<Idioma> idms1 = new HashSet<>();
        idms1.add(espanol);
        idms1.add(ingles);
        pelicula1.setIdiomas(idms1);
        pelicula1 = productoRepository.save(pelicula1);

        // Película 2
        Producto pelicula2 = new Producto();
        pelicula2.setTipoProducto(Producto.Tipo.PELICULA);
        pelicula2.setTitulo("Matrix");
        pelicula2.setDescripcion("Un hacker descubre la verdad sobre su realidad");
        pelicula2.setAnioPublicacion(1999);
        Set<Categoria> cats2 = new HashSet<>();
        cats2.add(accion);
        cats2.add(cienciaFiccion);
        pelicula2.setCategorias(cats2);
        Set<Idioma> idms2 = new HashSet<>();
        idms2.add(espanol);
        idms2.add(ingles);
        idms2.add(frances);
        pelicula2.setIdiomas(idms2);
        pelicula2 = productoRepository.save(pelicula2);

        // Película 3
        Producto pelicula3 = new Producto();
        pelicula3.setTipoProducto(Producto.Tipo.PELICULA);
        pelicula3.setTitulo("El Padrino");
        pelicula3.setDescripcion("La saga de una familia mafiosa italoamericana");
        pelicula3.setAnioPublicacion(1972);
        Set<Categoria> cats3 = new HashSet<>();
        cats3.add(drama);
        pelicula3.setCategorias(cats3);
        Set<Idioma> idms3 = new HashSet<>();
        idms3.add(espanol);
        idms3.add(ingles);
        idms3.add(italiano);
        pelicula3.setIdiomas(idms3);
        pelicula3 = productoRepository.save(pelicula3);

        // Serie 1
        Producto serie1 = new Producto();
        serie1.setTipoProducto(Producto.Tipo.SERIE);
        serie1.setTitulo("Breaking Bad");
        serie1.setDescripcion("Un profesor de química se convierte en fabricante de metanfetaminas");
        serie1.setAnioPublicacion(2008);
        Set<Categoria> cats4 = new HashSet<>();
        cats4.add(drama);
        serie1.setCategorias(cats4);
        Set<Idioma> idms4 = new HashSet<>();
        idms4.add(espanol);
        idms4.add(ingles);
        serie1.setIdiomas(idms4);
        serie1 = productoRepository.save(serie1);

        // Serie 2
        Producto serie2 = new Producto();
        serie2.setTipoProducto(Producto.Tipo.SERIE);
        serie2.setTitulo("Stranger Things");
        serie2.setDescripcion("Un grupo de niños enfrenta sucesos paranormales en su pueblo");
        serie2.setAnioPublicacion(2016);
        Set<Categoria> cats5 = new HashSet<>();
        cats5.add(terror);
        cats5.add(cienciaFiccion);
        serie2.setCategorias(cats5);
        Set<Idioma> idms5 = new HashSet<>();
        idms5.add(espanol);
        idms5.add(ingles);
        serie2.setIdiomas(idms5);
        serie2 = productoRepository.save(serie2);

        // Película 4
        Producto pelicula4 = new Producto();
        pelicula4.setTipoProducto(Producto.Tipo.PELICULA);
        pelicula4.setTitulo("Toy Story");
        pelicula4.setDescripcion("La vida secreta de los juguetes cuando no hay humanos");
        pelicula4.setAnioPublicacion(1995);
        Set<Categoria> cats6 = new HashSet<>();
        cats6.add(comedia);
        pelicula4.setCategorias(cats6);
        Set<Idioma> idms6 = new HashSet<>();
        idms6.add(espanol);
        idms6.add(ingles);
        idms6.add(frances);
        pelicula4.setIdiomas(idms6);
        pelicula4 = productoRepository.save(pelicula4);

        System.out.println(productoRepository.count() + " productos creados");

        // 5. Crear Alquileres
        System.out.println("Creando alquileres...");

        // Alquiler 1 - Suscriptor BÁSICO (14 días)
        Alquiler alquiler1 = Alquiler.builder()
                .fechaInicio(LocalDate.now())
                .suscriptor(susBasico1)
                .build();
        alquiler1.calcularFechaFin(); // Calcula automáticamente: hoy + 14 días
        alquiler1 = alquilerRepository.save(alquiler1);

        // Alquiler 2 - Suscriptor PREMIUM (28 días)
        Alquiler alquiler2 = Alquiler.builder()
                .fechaInicio(LocalDate.now())
                .suscriptor(susPremium1)
                .build();
        alquiler2.calcularFechaFin(); // Calcula automáticamente: hoy + 28 días
        alquiler2 = alquilerRepository.save(alquiler2);

        // Alquiler 3 - Suscriptor BÁSICO (14 días) - Fecha futura
        Alquiler alquiler3 = Alquiler.builder()
                .fechaInicio(LocalDate.now().plusDays(5))
                .suscriptor(susBasico2)
                .build();
        alquiler3.calcularFechaFin();
        alquiler3 = alquilerRepository.save(alquiler3);

        // Alquiler 4 - Suscriptor PREMIUM (28 días) - Fecha futura
        Alquiler alquiler4 = Alquiler.builder()
                .fechaInicio(LocalDate.now().plusDays(7))
                .suscriptor(susPremium2)
                .build();
        alquiler4.calcularFechaFin();
        alquiler4 = alquilerRepository.save(alquiler4);

        System.out.println(alquilerRepository.count() + " alquileres creados");
        System.out.println("   - con plan BÁSICO (fecha fin: +14 días)");
        System.out.println("   - con plan PREMIUM (fecha fin: +28 días)");

        System.out.println("Carga de datos completada");
    }

    public void mostrarEstadisticas() {
        System.out.println("\n === ESTADÍSTICAS DEL SISTEMA ===");
        System.out.println("-------------------------------------------");
        System.out.println("Categorías: " + categoriaRepository.count());
        System.out.println("Idiomas: " + idiomaRepository.count());
        System.out.println("Suscriptores: " + suscriptorRepository.count());
        System.out.println("   └─ BÁSICO: " + suscriptorRepository.count() / 2);
        System.out.println("   └─ PREMIUM: " + suscriptorRepository.count() / 2);
        System.out.println("Productos: " + productoRepository.count());
        System.out.println("Alquileres: " + alquilerRepository.count());
        System.out.println("-------------------------------------------\n");
    }
}


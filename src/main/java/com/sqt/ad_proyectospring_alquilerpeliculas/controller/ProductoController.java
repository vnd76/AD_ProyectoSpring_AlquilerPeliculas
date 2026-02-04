package com.sqt.ad_proyectospring_alquilerpeliculas.controller;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.ProductoDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.ProductoDetalleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.service.ProductoService;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/productos") // Todas las rutas comienzan por /api/productos
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    // ========== GET /api/productos ==========
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos(Pageable pageable) {
        List<ProductoDTO> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    // ========== GET /api/productos/{id} ==========
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDetalleDTO> obtenerPorId(@PathVariable Long id) {
        ProductoDetalleDTO productoDTO = productoService.buscarPorId(id);
        return ResponseEntity.ok(productoDTO);
    }

    // ========== POST /api/productos ==========
    @PostMapping
    public ResponseEntity<ProductoDetalleDTO> crear(@RequestBody @Valid ProductoDTO dto) {
        ProductoDetalleDTO creado = productoService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // ========== PUT /api/productos/{id} ==========
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDetalleDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProductoDTO dto) {
        ProductoDetalleDTO actualizado = productoService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // ========== DELETE /api/productos/{id} ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
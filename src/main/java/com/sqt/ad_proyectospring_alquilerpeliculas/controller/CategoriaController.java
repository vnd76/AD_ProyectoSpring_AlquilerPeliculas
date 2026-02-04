package com.sqt.ad_proyectospring_alquilerpeliculas.controller;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.CategoriaDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.CategoriaDetalleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.service.CategoriaService;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // ========== GET /api/categorias ==========
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodos(Pageable pageable) {
        List<CategoriaDTO> categorias = categoriaService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    // ========== GET /api/categorias/{id} ==========
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDetalleDTO> obtenerPorId(@PathVariable Long id) {
        CategoriaDetalleDTO categoriaDTO = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoriaDTO);
    }

    // ========== POST /api/categorias ==========
    @PostMapping
    public ResponseEntity<CategoriaDetalleDTO> crear(@RequestBody @Valid CategoriaDTO dto) {
        CategoriaDetalleDTO creado = categoriaService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // ========== DELETE /api/categorias/{id} ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.sqt.ad_proyectospring_alquilerpeliculas.controller;


import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.IdiomaDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.IdiomaDetalleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.service.IdiomaService;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/idiomas")
@RequiredArgsConstructor
public class IdiomaController {

    private final IdiomaService idiomaService;

    // ========== GET /api/idiomas ==========
    @GetMapping
    public ResponseEntity<List<IdiomaDTO>> listarTodos(Pageable pageable) {
        List<IdiomaDTO> idiomas = idiomaService.listarTodos();
        return ResponseEntity.ok(idiomas);
    }

    // ========== GET /api/idiomas/{id} ==========
    @GetMapping("/{id}")
    public ResponseEntity<IdiomaDetalleDTO> obtenerPorId(@PathVariable Long id) {
        IdiomaDetalleDTO idiomaDTO = idiomaService.buscarPorId(id);
        return ResponseEntity.ok(idiomaDTO);
    }

    // ========== POST /api/idiomas ==========
    @PostMapping
    public ResponseEntity<IdiomaDetalleDTO> crear(@RequestBody @Valid IdiomaDTO dto) {
        IdiomaDetalleDTO creado = idiomaService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // ========== DELETE /api/idiomas/{id} ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        idiomaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.sqt.ad_proyectospring_alquilerpeliculas.controller;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.SuscriptorDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.SuscriptorDetalleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.service.SuscriptorService;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/suscriptores") // Todas las rutas comienzan por /api/suscriptores
@RequiredArgsConstructor
public class SuscriptorController {

   private final SuscriptorService suscriptorService;

    // ========== GET /api/suscriptores ==========
    @GetMapping
    public ResponseEntity<List<SuscriptorDTO>> listarTodos(Pageable pageable) {
        List<SuscriptorDTO> suscriptores = suscriptorService.listarTodos();
        return ResponseEntity.ok(suscriptores);
    }

    // ========== GET /api/suscriptores/{id} ==========
    @GetMapping("/{id}")
    public ResponseEntity<SuscriptorDetalleDTO> obtenerPorId(@PathVariable Long id) {
        SuscriptorDetalleDTO suscriptorDTO = suscriptorService.buscarPorId(id);
        return ResponseEntity.ok(suscriptorDTO);
    }

    // ========== POST /api/suscriptores ==========
    @PostMapping
    public ResponseEntity<SuscriptorDetalleDTO> crear(@RequestBody @Valid SuscriptorDTO dto) {
        SuscriptorDetalleDTO creado = suscriptorService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // ========== PUT /api/suscriptores/{id} ==========
    @PutMapping("/{id}")
    public ResponseEntity<SuscriptorDetalleDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid SuscriptorDTO dto) {
        SuscriptorDetalleDTO actualizado = suscriptorService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // ========== DELETE /api/suscriptores/{id} ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        suscriptorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
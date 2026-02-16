package com.sqt.ad_proyectospring_alquilerpeliculas.controller;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.AlquilerDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.AlquilerDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sqt.ad_proyectospring_alquilerpeliculas.service.AlquilerService;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class AlquilerController {

   private final AlquilerService alquilerService;

   // ========== GET QUERY /api/alquileres/activos/plan/{plan} ==========
   @GetMapping("/activos/plan/{plan}")
   public ResponseEntity<Page<AlquilerDetalleDTO>> obtenerAlquileresActivosPorPlan(
           @PathVariable Suscriptor.PlanContratado plan,
           @PageableDefault(size = 10, sort = "fechaInicio") Pageable pageable) {

       Page<AlquilerDetalleDTO> alquileres = alquilerService.buscarAlquileresActivosPorPlan(plan, pageable);
       return ResponseEntity.ok(alquileres);
   }

    // ========== GET /api/alquileres ==========
    @GetMapping
    public ResponseEntity<List<AlquilerDetalleDTO>> listarTodos(Pageable pageable) {
        List<AlquilerDetalleDTO> alquileres = alquilerService.listarTodos();
        return ResponseEntity.ok(alquileres);
    }

    // ========== GET /api/alquileres/{id} ==========
    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDetalleDTO> obtenerPorId(@PathVariable Long id) {
        AlquilerDetalleDTO alquilerDTO = alquilerService.buscarPorId(id);
        return ResponseEntity.ok(alquilerDTO);
    }

    // ========== POST /api/alquileres ==========
    @PostMapping
    public ResponseEntity<AlquilerDetalleDTO> crear(@RequestBody @Valid AlquilerDTO dto) {
        AlquilerDetalleDTO creado = alquilerService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // ========== PUT /api/alquileres/{id} ==========
    @PutMapping("/{id}")
    public ResponseEntity<AlquilerDetalleDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlquilerDTO dto) {
        AlquilerDetalleDTO actualizado = alquilerService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // ========== DELETE /api/alquileres/{id} ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        alquilerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
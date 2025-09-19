package com.example.empleados.controller;

import com.example.empleados.dto.EmpleadoDTO;
import com.example.empleados.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "API para gestión de empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    @Operation(summary = "Obtener todos los empleados", description = "Retorna una lista de todos los empleados")
    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = EmpleadoDTO.class)))
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        List<EmpleadoDTO> empleados = empleadoService.getAllEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID", description = "Retorna un empleado específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado encontrado",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = EmpleadoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(
            @Parameter(description = "ID del empleado a buscar", example = "1", required = true)
            @PathVariable Long id) {
        EmpleadoDTO empleado = empleadoService.getEmpleadoById(id);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo empleado", description = "Crea un nuevo empleado en el sistema")
    @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = EmpleadoDTO.class)))
    public ResponseEntity<EmpleadoDTO> createEmpleado(
            @Parameter(description = "Datos del empleado a crear", required = true)
            @RequestBody EmpleadoDTO empleadoDto) {
        EmpleadoDTO nuevoEmpleado = empleadoService.createEmpleado(empleadoDto);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado", description = "Actualiza los datos de un empleado existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = EmpleadoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EmpleadoDTO> updateEmpleado(
            @Parameter(description = "ID del empleado a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados del empleado", required = true)
            @RequestBody EmpleadoDTO empleadoDto) {
        EmpleadoDTO empleadoActualizado = empleadoService.updateEmpleado(id, empleadoDto);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado", description = "Elimina un empleado del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<Void> deleteEmpleado(
            @Parameter(description = "ID del empleado a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/departamento/{departamento}")
    @Operation(summary = "Obtener empleados por departamento", description = "Retorna empleados filtrados por departamento")
    @ApiResponse(responseCode = "200", description = "Lista de empleados del departamento",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = EmpleadoDTO.class)))
    public ResponseEntity<List<EmpleadoDTO>> getEmpleadosByDepartamento(
            @Parameter(description = "Nombre del departamento", example = "IT", required = true)
            @PathVariable String departamento) {
        List<EmpleadoDTO> empleados = empleadoService.getEmpleadosByDepartamento(departamento);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/departamento/{departamento}/total")
    @Operation(summary = "Contar empleados por departamento", description = "Retorna el total de empleados en un departamento")
    @ApiResponse(responseCode = "200", description = "Total de empleados en el departamento")
    public ResponseEntity<Integer> getTotalEmpleadosByDepartamento(
            @Parameter(description = "Nombre del departamento", example = "IT", required = true)
            @PathVariable String departamento) {
        Integer total = empleadoService.getTotalEmpleadosByDepartamento(departamento);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
    
    @GetMapping("/external-api")
    @Operation(summary = "Ejemplo de API externa", description = "Consume una API externa como ejemplo")
    @ApiResponse(responseCode = "200", description = "Respuesta de la API externa")
    public ResponseEntity<String> getExternalApiExample() {
        String result = empleadoService.getExternalApiExample();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
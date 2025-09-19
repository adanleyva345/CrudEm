package com.example.empleados.service;

import com.example.empleados.dto.EmpleadoDTO;
import java.util.List;

public interface EmpleadoService {
    List<EmpleadoDTO> getAllEmpleados();
    EmpleadoDTO getEmpleadoById(Long id);
    EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDto);
    EmpleadoDTO updateEmpleado(Long id, EmpleadoDTO empleadoDto);
    void deleteEmpleado(Long id);
    List<EmpleadoDTO> getEmpleadosByDepartamento(String departamento);
    Integer getTotalEmpleadosByDepartamento(String departamento);
    String getExternalApiExample();
}
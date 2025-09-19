package com.example.empleados.service;

import com.example.empleados.dto.EmpleadoDTO;
import com.example.empleados.exception.ResourceNotFoundException;
import com.example.empleados.model.Empleado;
import com.example.empleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<EmpleadoDTO> getAllEmpleados() {
        // LAMBDA FUNCTION 
        return empleadoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO getEmpleadoById(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        return convertToDto(empleado);
    }

    @Override
    public EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDto) {
        Empleado empleado = convertToEntity(empleadoDto);
        Empleado nuevoEmpleado = empleadoRepository.save(empleado);
        return convertToDto(nuevoEmpleado);
    }

    @Override
    public EmpleadoDTO updateEmpleado(Long id, EmpleadoDTO empleadoDto) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setEmail(empleadoDto.getEmail());
        empleado.setDepartamento(empleadoDto.getDepartamento());
        empleado.setSalario(empleadoDto.getSalario());
        
        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return convertToDto(empleadoActualizado);
    }

    @Override
    public void deleteEmpleado(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        empleadoRepository.delete(empleado);
    }

    @Override
    public List<EmpleadoDTO> getEmpleadosByDepartamento(String departamento) {
        // LAMBDA FUNCTION
        return empleadoRepository.findByDepartamento(departamento).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getTotalEmpleadosByDepartamento(String departamento) {
        return empleadoRepository.countByDepartamento(departamento);
    }
    @Override
    public String getExternalApiExample() {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Error al consumir API externa: " + e.getMessage();
        }
    }

    private EmpleadoDTO convertToDto(Empleado empleado) {
        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getEmail(),
                empleado.getDepartamento(),
                empleado.getSalario()
        );
    }

    private Empleado convertToEntity(EmpleadoDTO empleadoDto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setEmail(empleadoDto.getEmail());
        empleado.setDepartamento(empleadoDto.getDepartamento());
        empleado.setSalario(empleadoDto.getSalario());
        return empleado;
    }
}
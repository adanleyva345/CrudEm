package com.example.empleados;

import com.example.empleados.dto.EmpleadoDTO;
import com.example.empleados.exception.ResourceNotFoundException;
import com.example.empleados.model.Empleado;
import com.example.empleados.repository.EmpleadoRepository;
import com.example.empleados.service.EmpleadoServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    @Test
    public void testGetAllEmpleados() {
        Empleado empleado1 = new Empleado("Juan", "juan@email.com", "IT", 50000.0);
        empleado1.setId(1L);
        Empleado empleado2 = new Empleado("Maria", "maria@email.com", "HR", 45000.0);
        empleado2.setId(2L);
        
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado1, empleado2));
        
        List<EmpleadoDTO> empleados = empleadoService.getAllEmpleados();
        
        assertEquals(2, empleados.size());
        assertEquals("Juan", empleados.get(0).getNombre());
        assertEquals("Maria", empleados.get(1).getNombre());
    }
    @Test
    public void testGetTotalEmpleadosByDepartamento() {
        String departamento = "IT";
        when(empleadoRepository.countByDepartamento(departamento)).thenReturn(2);
        
        Integer total = empleadoService.getTotalEmpleadosByDepartamento(departamento);
        
        assertEquals(2, total);
        verify(empleadoRepository, times(1)).countByDepartamento(departamento);
    }

    @Test
    public void testGetEmpleadoByIdNotFound() {
        Long id = 1L;
        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            empleadoService.getEmpleadoById(id);
        });
    }
}
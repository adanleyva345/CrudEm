package com.example.empleados;

import com.example.empleados.controller.EmpleadoController;
import com.example.empleados.dto.EmpleadoDTO;
import com.example.empleados.service.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "USER")
    public void testGetAllEmpleados() throws Exception {
        // Given
        EmpleadoDTO empleado1 = new EmpleadoDTO(1L, "Juan", "juan@email.com", "IT", 50000.0);
        EmpleadoDTO empleado2 = new EmpleadoDTO(2L, "Maria", "maria@email.com", "HR", 45000.0);
        List<EmpleadoDTO> empleados = Arrays.asList(empleado1, empleado2);
        
        when(empleadoService.getAllEmpleados()).thenReturn(empleados);
        
        // When & Then
        mockMvc.perform(get("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("Maria"));
    }
}
package com.example.empleados.repository;

import com.example.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByDepartamento(String departamento);
    
    @Query(value = "SELECT COUNT(*) FROM empleados WHERE departamento = :departamento", 
            nativeQuery = true)
     Integer countByDepartamento(@Param("departamento") String departamento);
 }
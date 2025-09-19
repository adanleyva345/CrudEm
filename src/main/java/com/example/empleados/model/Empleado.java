package com.example.empleados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
@NamedStoredProcedureQuery(
    name = "Empleado.getTotalEmpleadosByDepartamento",
    procedureName = "GET_TOTAL_EMPLEADOS_BY_DEPARTAMENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "departamento_param", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "total", type = Integer.class)
    }
)
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String departamento;
    
    private Double salario;
    
    public Empleado() {}
    
    public Empleado(String nombre, String email, String departamento, Double salario) {
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
        this.salario = salario;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
    
  }
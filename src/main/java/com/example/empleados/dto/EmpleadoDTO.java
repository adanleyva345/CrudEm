package com.example.empleados.dto;

public class EmpleadoDTO{
    private Long id;
    private String nombre;
    private String email;
    private String departamento;
    private Double salario;
    
    public EmpleadoDTO() {}
    
    public EmpleadoDTO(Long id, String nombre, String email, String departamento, Double salario) {
        this.id = id;
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
package com.example.empleados.repository;

public class H2StoredProcedures {
    public static int getTotalEmpleadosByDepartamento(String departamento) {
        if ("IT".equalsIgnoreCase(departamento)) {
            return 2;
        } else if ("HR".equalsIgnoreCase(departamento)) {
            return 1;
        } else if ("Finance".equalsIgnoreCase(departamento)) {
            return 1;
        }
        return 0;
    }
}
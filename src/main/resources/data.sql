-- Crear stored procedure para H2
CREATE ALIAS IF NOT EXISTS GET_TOTAL_EMPLEADOS_BY_DEPARTAMENTO FOR "com.example.empleados.repository.H2StoredProcedures.getTotalEmpleadosByDepartamento";

-- data.sql - Solo datos, no crear tablas
INSERT INTO empleados (nombre, email, departamento, salario) VALUES 
('Juan Pérez', 'juan@email.com', 'IT', 50000.0),
('María García', 'maria@email.com', 'HR', 45000.0),
('Carlos López', 'carlos@email.com', 'IT', 55000.0),
('Ana Martínez', 'ana@email.com', 'Finance', 48000.0);
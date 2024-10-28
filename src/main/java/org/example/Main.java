package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/practica";
        String user = "practica";
        String password = "practica";

        // Obtener apellido, oficio y salario de empleados del departamento 10
        String queryEmpleados = "SELECT apellido, oficio, salario FROM empleados WHERE dept_no = 10";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(queryEmpleados)) {

            while (resultSet.next()) {
                String apellido = resultSet.getString("apellido");
                String oficio = resultSet.getString("oficio");
                double salario = resultSet.getDouble("salario");
                System.out.println("Apellido: " + apellido + ", Oficio: " + oficio + ", Salario: " + salario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Obtener empleado con máximo salario
        String queryMaxSalario = "SELECT E.APELLIDO, E.SALARIO, D.dnombre FROM EMPLEADOS E " +
                "JOIN DEPARTAMENTOS D ON E.DEPT_NO = D.DEPT_NO " +
                "WHERE E.SALARIO = (SELECT MAX(SALARIO) FROM EMPLEADOS)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(queryMaxSalario);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                String apellido = resultSet.getString("APELLIDO");
                double salario = resultSet.getDouble("SALARIO");
                String nombreDepartamento = resultSet.getString("dnombre"); // Ajustado aquí
                System.out.println("Empleado con máximo salario:");
                System.out.println("Apellido: " + apellido + ", Salario: " + salario + ", Departamento: " + nombreDepartamento);
            } else {
                System.out.println("No se encontraron resultados.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

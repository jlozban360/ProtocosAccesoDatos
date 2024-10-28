package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/practica";
        String user = "practica";
        String password = "practica";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String query = "SELECT apellido, oficio, salario FROM empleados WHERE dept_no = 5";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String apellido = resultSet.getString("apellido");
                String oficio = resultSet.getString("oficio");
                double salario = resultSet.getDouble("salario");
                System.out.println("Apellido: " + apellido + ", Oficio: " + oficio + ", Salario: " + salario);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
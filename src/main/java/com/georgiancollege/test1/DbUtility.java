package com.georgiancollege.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbUtility {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3308/assign1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Param111@";
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM midTermEmployee LIMIT 500;";

        try (
                Connection conn= DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String province = resultSet.getString("province");
                String phoneNo = resultSet.getString("phone");

                Employee employee = new Employee(employeeId, firstName, lastName, address, city, province, phoneNo);

                employees.add(employee);
                System.out.println(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
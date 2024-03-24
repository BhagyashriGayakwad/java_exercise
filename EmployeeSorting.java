/* 1. Your company employs 200 people. They keep records of all the employee information in the *Employees.csv*
  file, which contains the following columns: first name, last name, department, position, and salary. However,
  they would like to sort the data; first, they would like to group the rows by department lexicographically,
  and then they would like to sort the rows by salary. As a Java developer, you have been assigned to create
  a Java application that is capable of carrying out this task.
*/
package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private int salary;

    public Employee(String firstName, String lastName, String department, String email, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public String toString() {
        return firstName + "," + lastName + "," + department + "," + email + "," + salary;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = readEmployees("F:\\java project\\untitled\\src\\Employees.csv");
        sortEmployees(employees);

        // Printing employees data
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private static void sortEmployees(List<Employee> employees) {
        employees.sort(Comparator.comparing(Employee::getDepartment).thenComparingInt(Employee::getSalary));
    }

    private static List<Employee> readEmployees(String filename) {
        List<Employee> employees = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            boolean firstLine = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 5) {
                    String firstName = data[0];
                    String lastName = data[1];
                    String department = data[2];
                    String email = data[3];
                    int salary;
                    try {
                        salary = Integer.parseInt(data[4]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary format for employee: " + line);
                        continue;
                    }
                    employees.add(new Employee(firstName, lastName, department, email, salary));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
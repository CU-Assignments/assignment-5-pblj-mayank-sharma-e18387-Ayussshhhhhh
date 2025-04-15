import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {

    // Employee class implements Serializable interface
    public static class Employee implements Serializable {
        private static final long serialVersionUID = 1L;

        private int employeeId;
        private String name;
        private String designation;
        private double salary;

        // Constructor
        public Employee(int employeeId, String name, String designation, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.designation = designation;
            this.salary = salary;
        }

        // Getters
        public int getEmployeeId() { return employeeId; }
        public String getName() { return name; }
        public String getDesignation() { return designation; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return "Employee ID: " + employeeId + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
        }
    }

    private static final String FILE_NAME = "employees.ser";

    // Method to add a new employee
    public static void addEmployee(ArrayList<Employee> employees) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();  // consume the newline character

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        // Create employee object
        Employee newEmployee = new Employee(employeeId, name, designation, salary);
        employees.add(newEmployee);

        // Save to file
        saveEmployeesToFile(employees);

        System.out.println("Employee added successfully!");
    }

    // Method to display all employees
    public static void displayEmployees(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("\nEmployee List:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    // Method to save employees to file
    public static void saveEmployeesToFile(ArrayList<Employee> employees) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    // Method to load employees from file
    public static ArrayList<Employee> loadEmployeesFromFile() {
        ArrayList<Employee> employees = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            employees = (ArrayList<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            // If file not found, return empty list (no employees)
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
        return employees;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Employee> employees = loadEmployeesFromFile();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(employees);
                    break;
                case 2:
                    displayEmployees(employees);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

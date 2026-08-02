import java.util.HashMap;
import java.util.Scanner;

public class EmployeeSalaryManager {
    public static void main(String[] args) {

        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfEmployees;
        int employeeId;
        int searchEmployeeId;
        int menuChoice;
        double salary;
        double newSalary;
        double highestSalary;
        double lowestSalary;
        double totalPayroll;
        double averageSalary;

        // Create HashMap to store employee IDs and salaries
        HashMap<Integer, Double> employeeSalaries = new HashMap<>();

        // Prompt user to enter number of employees
        System.out.print("Enter the number of employees: ");
        numberOfEmployees = scanner.nextInt();

        // Validate number of employees
        if (numberOfEmployees <= 0) {
            System.out.println("Invalid number of employees.");
        } else {

            // Add initial employee records
            for (int i = 0; i < numberOfEmployees; i++) {

                System.out.print("Enter employee ID: ");
                employeeId = scanner.nextInt();

                System.out.print("Enter employee salary: ");
                salary = scanner.nextDouble();

                // Check if employee ID already exists
                if (employeeSalaries.containsKey(employeeId)) {
                    System.out.println("Employee ID already exists. Record not added.");
                } else {
                    employeeSalaries.put(employeeId, salary);
                }
            }

            // Display menu until user exits
            do {

                System.out.println("\n===== Employee Salary Management Menu =====");
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee");
                System.out.println("3. Update Salary");
                System.out.println("4. Remove Employee");
                System.out.println("5. Display All Employees");
                System.out.println("6. Display Salary Statistics");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                menuChoice = scanner.nextInt();

                // Process menu using switch-case
                switch (menuChoice) {

                    case 1:
                        // Add new employee
                        System.out.print("Enter employee ID: ");
                        employeeId = scanner.nextInt();

                        if (employeeSalaries.containsKey(employeeId)) {
                            System.out.println("Employee ID already exists. Record not added.");
                        } else {
                            System.out.print("Enter employee salary: ");
                            salary = scanner.nextDouble();

                            employeeSalaries.put(employeeId, salary);
                            System.out.println("Employee added successfully.");
                        }
                        break;

                    case 2:
                        // Search employee
                        System.out.print("Enter employee ID to search: ");
                        searchEmployeeId = scanner.nextInt();

                        if (employeeSalaries.containsKey(searchEmployeeId)) {
                            System.out.println("Employee ID: " + searchEmployeeId);
                            System.out.println("Salary: "
                                    + employeeSalaries.get(searchEmployeeId));
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 3:
                        // Update employee salary
                        System.out.print("Enter employee ID to update: ");
                        searchEmployeeId = scanner.nextInt();

                        if (employeeSalaries.containsKey(searchEmployeeId)) {

                            System.out.print("Enter new salary: ");
                            newSalary = scanner.nextDouble();

                            employeeSalaries.replace(searchEmployeeId, newSalary);

                            System.out.println("Salary updated successfully.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 4:
                        // Remove employee
                        System.out.print("Enter employee ID to remove: ");
                        searchEmployeeId = scanner.nextInt();

                        if (employeeSalaries.remove(searchEmployeeId) != null) {
                            System.out.println("Employee removed successfully.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 5:
                        // Display all employees
                        System.out.println("\nAll Employees:");

                        for (Integer id : employeeSalaries.keySet()) {
                            System.out.println("Employee ID: " + id
                                    + " Salary: " + employeeSalaries.get(id));
                        }
                        break;

                    case 6:
                        // Display salary statistics
                        if (employeeSalaries.size() > 0) {

                            highestSalary = Double.MIN_VALUE;
                            lowestSalary = Double.MAX_VALUE;
                            totalPayroll = 0;

                            for (Double employeeSalary : employeeSalaries.values()) {

                                if (employeeSalary > highestSalary) {
                                    highestSalary = employeeSalary;
                                }

                                if (employeeSalary < lowestSalary) {
                                    lowestSalary = employeeSalary;
                                }

                                totalPayroll += employeeSalary;
                            }

                            averageSalary =
                                    totalPayroll / employeeSalaries.size();

                            System.out.println("\nSalary Statistics:");
                            System.out.println("Highest Salary: " + highestSalary);
                            System.out.println("Lowest Salary: " + lowestSalary);
                            System.out.println("Average Salary: " + averageSalary);
                            System.out.println("Total Payroll: " + totalPayroll);

                        } else {
                            System.out.println("No employee records available.");
                        }
                        break;

                    case 7:
                        // Exit program
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid menu choice.");
                }

            } while (menuChoice != 7);
        }

        // Close Scanner before program ends
        scanner.close();
    }
}

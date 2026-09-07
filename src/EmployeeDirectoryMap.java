import java.util.HashMap;
import java.util.Scanner;

public class EmployeeDirectoryMap {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfEmployees;
        int totalRecordsEntered = 0;
        int duplicateEmployeeIds = 0;
        int employeeId;
        int searchEmployeeId;
        String employeeName;
        String searchResult;
        String companyClassification;

        // Create HashMap to store employee IDs and names
        HashMap<Integer, String> employeeDirectory = new HashMap<>();

        // Prompt user to enter the number of employees
        System.out.print("Enter the number of employees: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of employees.");
            input.next();
            System.out.print("Enter the number of employees: ");
        }

        numberOfEmployees = input.nextInt();
        input.nextLine();

        // Validate the entered number of employees
        if (numberOfEmployees <= 0) {
            System.out.println("Invalid number of employees.");
        } else {

            // Use a loop to read employee IDs and names
            for (int i = 0; i < numberOfEmployees; i++) {
                System.out.print("Enter employee ID: ");
                while (!input.hasNextInt()) {
                    System.out.println("Employee ID must be a number.");
                    input.next();
                    System.out.print("Enter employee ID: ");
                }

                employeeId = input.nextInt();
                input.nextLine();

                System.out.print("Enter employee name: ");
                employeeName = input.nextLine().trim();

                totalRecordsEntered++;

                if (employeeName.isEmpty()) {
                    System.out.println("Employee name cannot be empty.");
                    continue;
                }

                // Check if employee ID already exists before adding
                if (employeeDirectory.containsKey(employeeId)) {
                    duplicateEmployeeIds++;
                    System.out.println("Employee ID already exists. Please enter a unique ID.");
                } else {
                    employeeDirectory.put(employeeId, employeeName);
                }
            }

            // Prompt user to search for an employee ID
            System.out.print("Enter employee ID to search: ");
            while (!input.hasNextInt()) {
                System.out.println("Employee ID not found.");
                input.next();
                System.out.print("Enter employee ID to search: ");
            }

            searchEmployeeId = input.nextInt();

            // Search employee using containsKey() method
            if (employeeDirectory.containsKey(searchEmployeeId)) {
                searchResult = "Employee Found: " + employeeDirectory.get(searchEmployeeId);
            } else {
                searchResult = "Employee ID not found.";
            }

            int uniqueEmployeeCount = employeeDirectory.size();

            // Classify company size based on unique employees
            if (uniqueEmployeeCount < 5) {
                companyClassification = "Small Company";
            } else if (uniqueEmployeeCount <= 10) {
                companyClassification = "Medium Company";
            } else {
                companyClassification = "Large Company";
            }

            // Display employee directory information
            System.out.println("\n===== Employee Directory Summary =====");
            System.out.println("Total employee records entered: " + totalRecordsEntered);
            System.out.println("Total unique employees: " + uniqueEmployeeCount);
            System.out.println("Duplicate employee IDs: " + duplicateEmployeeIds);
            System.out.println("All employee IDs and names:");
            for (int id : employeeDirectory.keySet()) {
                System.out.println(id + " - " + employeeDirectory.get(id));
            }
            System.out.println("Search result: " + searchResult);
            System.out.println("Company classification: " + companyClassification);
            System.out.println("Employee directory check completed.");
        }

        input.close();
    }
}

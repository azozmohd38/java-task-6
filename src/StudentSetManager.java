import java.util.HashSet;
import java.util.Scanner;

public class StudentSetManager {

    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables needed for the program
        int numberOfStudents;
        int totalIdsEntered = 0;
        int duplicateCount = 0;
        String studentId;
        String registrationClassification;

        // Create HashSet to store unique student IDs
        HashSet<String> studentIds = new HashSet<>();

        // Prompt user to enter the number of student IDs
        System.out.print("Enter the number of student IDs to register: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of students.");
            input.next();
            System.out.print("Enter the number of student IDs to register: ");
        }

        numberOfStudents = input.nextInt();

        // Validate the entered number of students
        if (numberOfStudents <= 0) {
            System.out.println("Invalid number of students.");
        } else {

            // Use a loop to read and store student IDs
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.print("Enter student ID: ");
                studentId = input.next().trim();

                totalIdsEntered++;

                if (studentId.isEmpty()) {
                    System.out.println("Student ID cannot be empty.");
                    continue;
                }

                // Add ID to HashSet and check for duplicates
                if (!studentIds.add(studentId)) {
                    duplicateCount++;
                    System.out.println("Duplicate ID detected. ID was not added.");
                }
            }

            int uniqueStudentCount = studentIds.size();

            // Classify registration based on number of unique IDs
            if (uniqueStudentCount < 5) {
                registrationClassification = "Small Registration";
            } else if (uniqueStudentCount <= 10) {
                registrationClassification = "Medium Registration";
            } else {
                registrationClassification = "Large Registration";
            }

            // Display registration results
            System.out.println("\n===== Student Registration Summary =====");
            System.out.println("Total IDs entered: " + totalIdsEntered);
            System.out.println("Total unique student IDs: " + uniqueStudentCount);
            System.out.println("Duplicate IDs: " + duplicateCount);
            System.out.println("All unique student IDs:");
            for (String id : studentIds) {
                System.out.println(id);
            }
            System.out.println("Registration classification: " + registrationClassification);
            System.out.println("Registration completed.");
        }

        input.close();
    }
}
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentAttendanceManager {
    public static void main(String[] args) {

        // Create Scanner object for user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfStudents;
        int studentId;
        int attendedDays;
        int searchStudentId;
        int menuChoice;
        int newAttendanceDays;
        int totalAttendanceDays;
        int highestAttendance;
        int lowestAttendance;
        int highestAttendanceStudentId;
        int lowestAttendanceStudentId;
        double averageAttendance;

        // Create HashMap to store student IDs and attendance days
        HashMap<Integer, Integer> attendanceRecords = new HashMap<>();

        // Prompt user to enter the number of student records
        System.out.print("Enter the number of student records: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of students.");
            input.next();
            System.out.print("Enter the number of student records: ");
        }

        numberOfStudents = input.nextInt();

        // Check if number of students is valid
        if (numberOfStudents <= 0) {
            System.out.println("Invalid number of students.");
        } else {

            // Read and store initial student records
            for (int i = 0; i < numberOfStudents; i++) {

                System.out.print("Enter student ID: ");
                while (!input.hasNextInt()) {
                    System.out.println("Student ID must be a number.");
                    input.next();
                    System.out.print("Enter student ID: ");
                }

                studentId = input.nextInt();

                System.out.print("Enter number of attended days: ");
                while (!input.hasNextInt()) {
                    System.out.println("Attendance days must be a number.");
                    input.next();
                    System.out.print("Enter number of attended days: ");
                }

                attendedDays = input.nextInt();

                if (attendedDays < 0) {
                    System.out.println("Attendance days cannot be negative.");
                    continue;
                }

                // Check for duplicate student IDs
                if (attendanceRecords.containsKey(studentId)) {
                    System.out.println("Student ID already exists. Record not added.");
                } else {
                    attendanceRecords.put(studentId, attendedDays);
                }
            }

            // Display menu repeatedly until user exits
            do {

                System.out.println("\n===== Student Attendance Menu =====");
                System.out.println("1. Add Student Record");
                System.out.println("2. Search Student Attendance");
                System.out.println("3. Update Attendance");
                System.out.println("4. Remove Student Record");
                System.out.println("5. Display All Attendance Records");
                System.out.println("6. Display Attendance Statistics");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                while (!input.hasNextInt()) {
                    System.out.println("Invalid choice.");
                    input.next();
                    System.out.print("Enter your choice: ");
                }

                menuChoice = input.nextInt();

                // Process menu options using switch-case
                switch (menuChoice) {

                    case 1:
                        // Add new student record
                        System.out.print("Enter student ID: ");
                        studentId = input.nextInt();

                        if (attendanceRecords.containsKey(studentId)) {
                            System.out.println("Student ID already exists. Record not added.");
                        } else {
                            System.out.print("Enter attended days: ");
                            while (!input.hasNextInt()) {
                                System.out.println("Attendance days must be a number.");
                                input.next();
                                System.out.print("Enter attended days: ");
                            }

                            attendedDays = input.nextInt();

                            if (attendedDays < 0) {
                                System.out.println("Attendance days cannot be negative.");
                            } else {
                                attendanceRecords.put(studentId, attendedDays);
                                System.out.println("Student record added successfully.");
                            }
                        }
                        break;

                    case 2:
                        // Search student attendance
                        System.out.print("Enter student ID to search: ");
                        searchStudentId = input.nextInt();

                        if (attendanceRecords.containsKey(searchStudentId)) {
                            System.out.println("Student ID: " + searchStudentId);
                            System.out.println("Attendance Days: "
                                    + attendanceRecords.get(searchStudentId));
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 3:
                        // Update attendance using replace()
                        System.out.print("Enter student ID to update: ");
                        searchStudentId = input.nextInt();

                        if (attendanceRecords.containsKey(searchStudentId)) {

                            System.out.print("Enter new attendance days: ");
                            while (!input.hasNextInt()) {
                                System.out.println("Attendance days must be a number.");
                                input.next();
                                System.out.print("Enter new attendance days: ");
                            }

                            newAttendanceDays = input.nextInt();

                            if (newAttendanceDays < 0) {
                                System.out.println("Attendance days cannot be negative.");
                            } else {
                                attendanceRecords.replace(searchStudentId, newAttendanceDays);
                                System.out.println("Attendance updated successfully.");
                            }

                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 4:
                        // Remove student record using remove()
                        System.out.print("Enter student ID to remove: ");
                        searchStudentId = input.nextInt();

                        if (attendanceRecords.remove(searchStudentId) != null) {
                            System.out.println("Student record removed successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 5:
                        // Display all attendance records using entrySet()
                        System.out.println("\nAll Attendance Records:");

                        for (Map.Entry<Integer, Integer> entry : attendanceRecords.entrySet()) {
                            System.out.println("Student ID: " + entry.getKey()
                                    + ", Attendance Days: " + entry.getValue());
                        }
                        break;

                    case 6:
                        // Calculate attendance statistics
                        if (attendanceRecords.size() > 0) {

                            totalAttendanceDays = 0;
                            highestAttendance = Integer.MIN_VALUE;
                            lowestAttendance = Integer.MAX_VALUE;
                            highestAttendanceStudentId = 0;
                            lowestAttendanceStudentId = 0;

                            // Use entrySet() to calculate statistics
                            for (Map.Entry<Integer, Integer> entry : attendanceRecords.entrySet()) {

                                studentId = entry.getKey();
                                attendedDays = entry.getValue();

                                totalAttendanceDays += attendedDays;

                                if (attendedDays > highestAttendance) {
                                    highestAttendance = attendedDays;
                                    highestAttendanceStudentId = studentId;
                                }

                                if (attendedDays < lowestAttendance) {
                                    lowestAttendance = attendedDays;
                                    lowestAttendanceStudentId = studentId;
                                }
                            }

                            averageAttendance =
                                    (double) totalAttendanceDays / attendanceRecords.size();

                            // Display statistics
                            System.out.println("\n===== Attendance Statistics =====");
                            System.out.println("Total number of students: "
                                    + attendanceRecords.size());
                            System.out.println("Total attendance days: "
                                    + totalAttendanceDays);
                            System.out.println("Average attendance: "
                                    + averageAttendance);
                            System.out.println("Student with highest attendance: "
                                    + highestAttendanceStudentId
                                    + " (" + highestAttendance + " days)");
                            System.out.println("Student with lowest attendance: "
                                    + lowestAttendanceStudentId
                                    + " (" + lowestAttendance + " days)");

                        } else {
                            System.out.println("No attendance records available.");
                        }
                        break;

                    case 7:
                        // Exit system
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } while (menuChoice != 7);
        }

        // Close Scanner before program ends
        input.close();
    }
}
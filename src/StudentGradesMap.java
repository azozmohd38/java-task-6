import java.util.HashMap;
import java.util.Scanner;
public class StudentGradesMap {
        public static void main(String[] args) {

            // Create Scanner object to read user input
            Scanner input = new Scanner(System.in);

            // Declare variables required for the program
            int numberOfStudents;
            int totalStudentRecords = 0;
            int studentId;
            int updateStudentId;
            double studentGrade;
            double newGrade;
            double totalGrades = 0;
            double averageGrade;
            String performanceClassification;

            // Create HashMap to store student IDs and grades
            HashMap<Integer, Double> studentGrades = new HashMap<>();

            // Prompt user to enter the number of students
            System.out.print("Enter the number of students: ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid number of students.");
                input.next();
                System.out.print("Enter the number of students: ");
            }

            numberOfStudents = input.nextInt();

            // Validate the entered number of students
            if (numberOfStudents <= 0) {
                System.out.println("Invalid number of students.");
            } else {

                // Use a loop to read student IDs and grades
                for (int i = 0; i < numberOfStudents; i++) {
                    System.out.print("Enter student ID: ");
                    while (!input.hasNextInt()) {
                        System.out.println("Student ID must be a number.");
                        input.next();
                        System.out.print("Enter student ID: ");
                    }

                    studentId = input.nextInt();

                    System.out.print("Enter student grade: ");
                    studentGrade = input.nextDouble();

                    // Check if student ID already exists before adding
                    if (studentGrades.containsKey(studentId)) {
                        System.out.println("Student ID already exists. Record not added.");
                    } else {
                        studentGrades.put(studentId, studentGrade);
                        totalStudentRecords++;
                    }
                }

                // Prompt user to enter student ID for grade update
                System.out.print("Enter student ID to update grade: ");
                updateStudentId = input.nextInt();

                // Check if student ID exists and update grade using replace()
                if (studentGrades.containsKey(updateStudentId)) {
                    System.out.print("Enter new grade: ");
                    newGrade = input.nextDouble();

                    studentGrades.replace(updateStudentId, newGrade);
                    System.out.println("Grade updated successfully.");
                } else {
                    System.out.println("Student ID not found.");
                }

                // Calculate average grade of all students
                totalGrades = 0;

                for (double grade : studentGrades.values()) {
                    totalGrades += grade;
                }

                averageGrade = totalGrades / studentGrades.size();

                // Classify class performance based on average grade
                if (averageGrade < 60) {
                    performanceClassification = "Needs Improvement";
                } else if (averageGrade <= 84) {
                    performanceClassification = "Good Performance";
                } else {
                    performanceClassification = "Excellent Performance";
                }

                // Display student grade information
                System.out.println("Total student records: " + studentGrades.size());
                System.out.println("All student IDs and grades: " + studentGrades);
                System.out.println("Average grade: " + averageGrade);
                System.out.println("Class performance classification: " + performanceClassification);
            }

            // Close Scanner before program ends
            input.close();
        }
}
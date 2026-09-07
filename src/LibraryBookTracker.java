import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LibraryBookTracker {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfBooks;
        int totalBookRecordsEntered = 0;
        int duplicateBookIds = 0;
        int bookId;
        int searchBookId;
        String bookTitle;
        String searchResult;
        String libraryClassification;

        // Create HashSet to store unique book IDs
        HashSet<Integer> bookIds = new HashSet<>();

        // Create HashMap to store book IDs and titles
        HashMap<Integer, String> bookCatalog = new HashMap<>();

        // Prompt user to enter the number of books
        System.out.print("Enter the number of books: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of books.");
            input.next();
            System.out.print("Enter the number of books: ");
        }

        numberOfBooks = input.nextInt();
        input.nextLine();

        // Validate the entered number of books
        if (numberOfBooks <= 0) {
            System.out.println("Invalid number of books.");
        } else {

            // Use a loop to read book IDs and titles
            for (int i = 0; i < numberOfBooks; i++) {

                System.out.print("Enter book ID: ");
                while (!input.hasNextInt()) {
                    System.out.println("Book ID must be a number.");
                    input.next();
                    System.out.print("Enter book ID: ");
                }

                bookId = input.nextInt();
                input.nextLine();

                System.out.print("Enter book title: ");
                bookTitle = input.nextLine().trim();

                totalBookRecordsEntered++;

                if (bookTitle.isEmpty()) {
                    System.out.println("Book title cannot be empty.");
                    continue;
                }

                // Check if book ID already exists in the HashSet
                if (bookIds.contains(bookId)) {
                    duplicateBookIds++;
                    System.out.println("Duplicate Book ID. Record not added.");
                } else {

                    // Add unique book ID and title to collections
                    bookIds.add(bookId);
                    bookCatalog.put(bookId, bookTitle);
                }
            }

            // Prompt user to search for a book ID
            System.out.print("Enter book ID to search: ");
            while (!input.hasNextInt()) {
                System.out.println("Book not found.");
                input.next();
                System.out.print("Enter book ID to search: ");
            }

            searchBookId = input.nextInt();

            // Search for book using HashMap
            if (bookCatalog.containsKey(searchBookId)) {
                searchResult = "Book ID: " + searchBookId +
                        "\nBook Title: " + bookCatalog.get(searchBookId);
            } else {
                searchResult = "Book not found.";
            }

            // Classify library size based on number of unique books
            if (bookCatalog.size() < 5) {
                libraryClassification = "Small Library";
            } else if (bookCatalog.size() <= 10) {
                libraryClassification = "Medium Library";
            } else {
                libraryClassification = "Large Library";
            }

            // Display library information
            System.out.println("Total book records entered: " + totalBookRecordsEntered);
            System.out.println("Total unique books: " + bookIds.size());
            System.out.println("All book IDs: " + bookIds);
            System.out.println("All book IDs with their corresponding titles: " + bookCatalog);
            System.out.println("Search result:\n" + searchResult);
            System.out.println("Library classification: " + libraryClassification);
        }

        // Close Scanner before program ends
        input.close();
    }
}

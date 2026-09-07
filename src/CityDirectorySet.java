import java.util.Scanner;
import java.util.TreeSet;

public class CityDirectorySet {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfCities;
        int totalCitiesEntered = 0;
        String cityName;
        String searchCity;
        String searchResult;
        String directoryClassification;

        // Create TreeSet to store unique city names in alphabetical order
        TreeSet<String> cityDirectory = new TreeSet<>();

        // Prompt user to enter the number of cities
        System.out.print("Enter the number of cities: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of cities.");
            input.next();
            System.out.print("Enter the number of cities: ");
        }

        numberOfCities = input.nextInt();
        input.nextLine();

        // Validate the entered number of cities
        if (numberOfCities <= 0) {
            System.out.println("Invalid number of cities.");
        } else {

            // Use a loop to read and store city names
            for (int i = 0; i < numberOfCities; i++) {
                System.out.print("Enter city name: ");
                cityName = input.next();

                totalCitiesEntered++;

                // Add city to TreeSet and check for duplicates
                if (!cityDirectory.add(cityName)) {
                    System.out.println("City already exists. Duplicate entries are not allowed.");
                }
            }

            // Prompt user to search for a city
            System.out.print("Enter city name to search: ");
            searchCity = input.next();

            // Check whether the city exists using contains()
            if (cityDirectory.contains(searchCity)) {
                searchResult = "City found in the directory.";
            } else {
                searchResult = "City not found in the directory.";
            }

            // Classify directory size based on unique cities
            if (cityDirectory.size() < 5) {
                directoryClassification = "Small Directory";
            } else if (cityDirectory.size() <= 10) {
                directoryClassification = "Medium Directory";
            } else {
                directoryClassification = "Large Directory";
            }

            // Display city directory information
            System.out.println("Total cities entered: " + totalCitiesEntered);
            System.out.println("Total unique cities: " + cityDirectory.size());
            System.out.println("All cities in alphabetical order: " + cityDirectory);
            System.out.println("Search result: " + searchResult);
            System.out.println("Directory classification: " + directoryClassification);
        }

        // Close Scanner before program ends
        input.close();
    }
}
package ro.IOwithEnums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser { // Class to parse CSV files and create Athlete objects
    public static List<Athlete> parseCSV(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        List<Athlete> athletes = new ArrayList<>();
        String line;
        int lineNumber = 0;

        while ((line = reader.readLine()) != null) { //Reading each line from the CSV
            lineNumber++;
            try {
                String[] parts = line.split(",");
                if (parts.length < 7) { //Checking if the line has enough parts
                    System.err.println("Invalid line format at line " + lineNumber + ": " + line);
                    continue;
                }

                int number = Integer.parseInt(parts[0]);//Parsing athlete number
                String name = parts[1];
                String country = parts[2];
                String skiTime = parts[3];
                String[] ranges = Arrays.copyOfRange(parts, 4, 7);

                Athlete athlete = new Athlete(number, name, country, skiTime, ranges);
                athlete.calculatePenalties();
                athletes.add(athlete);

            } catch (NumberFormatException e) { //Catching NumberFormatException for invalid number parsing
                System.err.println("Number parsing error at line " + lineNumber + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error at line " + lineNumber + ": " + e.getMessage());
            }
        }

        return athletes;
    }
}

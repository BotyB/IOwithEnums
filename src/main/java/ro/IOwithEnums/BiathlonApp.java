package ro.IOwithEnums;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.List;
/**
 * BiathlonApp.java
 * This class is the main entry point for the Biathlon application.
 * It reads a CSV file containing athlete data, sorts the athletes by their time,
 * and serializes the top 3 athletes to a file.
 */
public class BiathlonApp {
    public static void main(String[] args) {
        // Load Biathlon.csv from resources
        InputStream inputStream = BiathlonApp.class.getClassLoader().getResourceAsStream("Biathlon.csv");
        if (inputStream == null) {
            System.err.println("Could not find Biathlon.csv in resources folder.");
            return;
        }

        String outputSerializedFile = "top3.ser"; //Serialized output file

        try (InputStream fis = inputStream) {
            List<Athlete> athletes = CSVParser.parseCSV(fis);
            athletes.sort(null); //Uses Comparable from Athlete

            List<Athlete> top3 = athletes.stream().limit(3).toList();

            for (int i = 0; i < top3.size(); i++) { //Print top 3 athletes
                Athlete a = top3.get(i);
                //If the shooting results are ugly (Delete below String! + last 2 obj from sout below)
                String shootingResults = String.join(" | ", a.getShootings());
                System.out.println(Position.values()[i] + " - " + "Athlete No. " + a.getNumber() + " " + a.getName() + " (" + a.getCountry() + ") " + a.getFormattedTime() + " | Shooting: [" + shootingResults + "]");
            }

            writeObjectToFile(top3, outputSerializedFile);

        } catch (IOException e) {
            System.err.println("Error reading or parsing CSV: " + e.getMessage());
        }
    }

    //Generic object serialization
    private static <T> void writeObjectToFile(T object, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
            System.out.println("Top 3 serialized to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }
}

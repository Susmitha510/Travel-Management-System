package travelmanagementsystem;

import java.io.*;
import java.util.*;

public class FileHandler {
    public static final String ADMIN_FILE = "admin.csv";
    public static final String USER_FILE = "users.csv";
    public static final String TRIP_FILE = "trips.csv";
    public static final String BOOKING_FILE = "bookings.csv";

    public static void ensureFileExists(String fileName, String header) {
        File file = new File(fileName);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(header);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Error creating file: " + fileName);
            }
        }
    }

    public static List<String[]> readFile(String fileName) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                data.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
        return data;
    }

    public static void writeFile(String fileName, List<String[]> data, String header) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(header);
            bw.newLine();
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}
